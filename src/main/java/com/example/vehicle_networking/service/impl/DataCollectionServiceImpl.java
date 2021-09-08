package com.example.vehicle_networking.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.vehicle_networking.entity.Position;
import com.example.vehicle_networking.entity.RealTimeData;
import com.example.vehicle_networking.enums.LockStatusEnum;
import com.example.vehicle_networking.enums.OperatingStatusEnum;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.form.ReadDataParaForm;
import com.example.vehicle_networking.mapper.PositionMapper;
import com.example.vehicle_networking.mapper.RealTimeDataMapper;
import com.example.vehicle_networking.service.DataCollectionService;
import com.example.vehicle_networking.thread.ReadDataThread;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import com.example.vehicle_networking.vo.readData.DataInfoDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 8:23
 */
@Service
@Slf4j
public class DataCollectionServiceImpl implements DataCollectionService {

//    static final String speedUrl = "http://localhost:8888/user/getUserByName?name=Eic";
    static final String speedUrl = "https://iot-pre.diwork.com/reader/api/v1/realTimeDataByTag?tags=test@%E6%B8%A9%E5%BA%A6,test@%E6%B9%BF%E5%BA%A6";

    static ExecutorService threadPool;
    private volatile ReadDataThread collectDataThread;

    @Autowired
    private RestTemplateTo restTemplateTo;
    @Autowired
    private RealTimeDataMapper realTimeDataMapper;
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public ResultVO getSpeedFromURL(String url, String cookie, Integer vehicleId) {
        // 保存速度信息
        JSONObject jsonObject = restTemplateTo.doGetWith(url, cookie);
        JSONObject data = jsonObject.getJSONObject("data");
        DataInfoDetail engineSpeed = data.getObject("car@转速", DataInfoDetail.class);
        DataInfoDetail fuelMargin = data.getObject("car@燃油余量", DataInfoDetail.class);
        DataInfoDetail temp = data.getObject("car@温度", DataInfoDetail.class);
        DataInfoDetail speed = data.getObject("car@速度", DataInfoDetail.class);
//        DataInfoDetail humidity = data.getObject("test@湿度", DataInfoDetail.class);
//        DataInfoDetail longitude = data.getObject("test@经度", DataInfoDetail.class);
//        DataInfoDetail latitude = data.getObject("test@纬度", DataInfoDetail.class);

        RealTimeData realTimeData = new RealTimeData();
        realTimeData.setEngineSpeed(Double.valueOf(engineSpeed.getValue()));
        realTimeData.setFuelMargin(Double.valueOf(fuelMargin.getValue()));
        realTimeData.setEngineTemperature(Double.valueOf(temp.getValue()));
        realTimeData.setSpeed(Double.valueOf(speed.getValue()));
        realTimeData.setCreateTime(engineSpeed.getTimestamp());
        realTimeData.setVehicleId(vehicleId);


        int insert = realTimeDataMapper.insert(realTimeData);
        if (insert != 1) {
            return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
        }

//        // 采集位置信息
//        Position position = new Position();
//        position.setLatitude(String.valueOf(latitude.getValue()));
//        position.setLongitude(String.valueOf(longitude.getValue()));
//        position.setCreateTime(engineSpeed.getTimestamp());
//        position.setVehicleId(vehicleId);
//        int positionInsert = positionMapper.insert(position);
//        if (positionInsert != 1) {
//            return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
//        }

        return ResultVOUtil.success();
    }

    @Override
    public ResultVO getStatusDataRead() {
        return ResultVOUtil.success(collectDataThread.getStatus());
    }

    @Override
    public ResultVO getRealSpeedData(Integer vehicleId) {
        return ResultVOUtil.success(realTimeDataMapper.getRealTimeDataOneByVehicleId(vehicleId));
    }

    @Override
    public ResultVO getHistoricalPosition(HistoricalPositionFrom historicalPositionFrom) {
        List<Position> historicalDataByVehicleIdList = positionMapper.getHistoricalDataByDate(historicalPositionFrom);
        return ResultVOUtil.success(historicalDataByVehicleIdList);
    }

    @Override
    public ResultVO openOrDownRealDataCollect(ReadDataParaForm readDataParaForm) {
        if (collectDataThread == null){
            synchronized (this){
                if (collectDataThread == null){
                    collectDataThread = new ReadDataThread("collectDataThread", readDataParaForm);
                }
            }
        }
        boolean collectDataThreadStatus = collectDataThread.getStatus();
        if (collectDataThreadStatus){
            log.info("关闭线程：{}",collectDataThread.getName());
            collectDataThread.stopTask();
            return ResultVOUtil.success(ResultEnum.DATA_READ_SHUT_DOWNED);
        }
        collectDataThread.startTask();
        collectDataThread.start();
        log.info("开启线程：{}",collectDataThread.getName());
        return ResultVOUtil.success(ResultEnum.DATA_READ_OPENED);
    }
}
