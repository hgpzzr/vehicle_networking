package com.example.vehicle_networking.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.vehicle_networking.entity.Position;
import com.example.vehicle_networking.entity.RealTimeData;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.form.ReadDataParaForm;
import com.example.vehicle_networking.mapper.PositionMapper;
import com.example.vehicle_networking.mapper.RealTimeDataMapper;
import com.example.vehicle_networking.service.DataCollectionService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import com.example.vehicle_networking.vo.readData.DataInfoDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

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

    @Autowired
    private RestTemplateTo restTemplateTo;
    @Autowired
    private RealTimeDataMapper realTimeDataMapper;
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public ResultVO getSpeedFromURL(String url, String cookie) {
        // 保存速度信息
        JSONObject jsonObject = restTemplateTo.doGetWith(speedUrl, cookie);
        JSONObject data = jsonObject.getJSONObject("data");
        DataInfoDetail engineSpeed = data.getObject("test@转速", DataInfoDetail.class);
        DataInfoDetail fuelMargin = data.getObject("test@燃油余量", DataInfoDetail.class);
        DataInfoDetail temp = data.getObject("test@温度", DataInfoDetail.class);
        DataInfoDetail speed = data.getObject("test@速度", DataInfoDetail.class);
        DataInfoDetail humidity = data.getObject("test@湿度", DataInfoDetail.class);



        RealTimeData realTimeData = new RealTimeData();

        realTimeData.setEngineSpeed(Double.valueOf(engineSpeed.getValue()));
        realTimeData.setFuelMargin(Double.valueOf(fuelMargin.getValue()));
        realTimeData.setEngineTemperature(Double.valueOf(temp.getValue()));
        realTimeData.setSpeed(Double.valueOf(speed.getValue()));
        realTimeData.setCreateTime(engineSpeed.getTimestamp());
        int insert = realTimeDataMapper.insert(realTimeData);
        if (insert != 1) {
            return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO getStatusDataRead() {
        boolean shutdown = threadPool.isShutdown();
        if (shutdown){
            return ResultVOUtil.success(ResultEnum.DATA_READ_OPENED);
        }else {
            return ResultVOUtil.success(ResultEnum.DATA_READ_SHUT_DOWNED);
        }
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
//        log.info(" isShutdown : {}", threadPool.isShutdown());
        if (threadPool != null && !threadPool.isShutdown()){
            threadPool.shutdownNow();
            return ResultVOUtil.success(ResultEnum.DATA_READ_SHUT_DOWNED);
        }

        threadPool = new ThreadPoolExecutor(
                1,
                2,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );


        log.info("open");
        threadPool.execute( () -> {
            while (true){
                getSpeedFromURL(readDataParaForm.getUrl(), readDataParaForm.getCookie());
                log.info(" 线程 {} 保存数据成功",Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        return ResultVOUtil.success(ResultEnum.DATA_READ_OPENED);
    }
}
