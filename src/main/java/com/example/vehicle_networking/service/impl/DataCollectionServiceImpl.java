package com.example.vehicle_networking.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.vehicle_networking.config.BaseConfig;
import com.example.vehicle_networking.config.CollectDataThreadConfig;
import com.example.vehicle_networking.entity.OilConsumptionRecord;
import com.example.vehicle_networking.entity.Position;
import com.example.vehicle_networking.entity.RealTimeData;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.enums.LockStatusEnum;
import com.example.vehicle_networking.enums.OperatingStatusEnum;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.form.ReadDataParaForm;
import com.example.vehicle_networking.mapper.PositionMapper;
import com.example.vehicle_networking.mapper.RealTimeDataMapper;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.AlarmService;
import com.example.vehicle_networking.service.DataCollectionService;
import com.example.vehicle_networking.service.VehicleService;
import com.example.vehicle_networking.thread.ReadDataThread;
import com.example.vehicle_networking.utils.GetDistanceUtil;
import com.example.vehicle_networking.utils.ReadDataAPI;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import com.example.vehicle_networking.vo.readData.DataInfoDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;

/**
 * @author ：GO FOR IT
 * @description：cddc
 * @date ：2021/9/8 8:23
 */
@Service
@Slf4j
public class DataCollectionServiceImpl implements DataCollectionService {

    @Autowired
    private RestTemplateTo restTemplateTo;
    @Autowired
    private RealTimeDataMapper realTimeDataMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private AlarmService alarmService;
    @Autowired
    private CollectDataThreadConfig collectDataThreadConfig;

    @Override
    public ResultVO getSpeedFromURL(String url, String cookie, Integer vehicleId, String vehicleName) {
        Vehicle vehicle = vehicleMapper.selectByPrimaryKey(vehicleId);
        if (!checkDataThreadStatus(vehicleId)) {
            return ResultVOUtil.error(ResultEnum.LOCKED_NOT_RUNNING);
        }
        // 保存速度信息
        JSONObject jsonObject = restTemplateTo.doGetWith(url, cookie);
        JSONObject data = jsonObject.getJSONObject("data");
        DataInfoDetail engineSpeed = data.getObject(vehicleName + "@转速-" + vehicleId, DataInfoDetail.class);
        DataInfoDetail fuelMargin = data.getObject(vehicleName + "@燃油余量-" + vehicleId, DataInfoDetail.class);
        DataInfoDetail temp = data.getObject(vehicleName + "@温度-" + vehicleId, DataInfoDetail.class);
        DataInfoDetail speed = data.getObject(vehicleName + "@速度-" + vehicleId, DataInfoDetail.class);
        DataInfoDetail inclination = data.getObject(vehicleName + "@倾斜度-" + vehicleId, DataInfoDetail.class);

        RealTimeData realTimeData = new RealTimeData();
        try {
            realTimeData.setEngineSpeed(Double.valueOf(engineSpeed.getValue()));
            realTimeData.setFuelMargin(Double.valueOf(fuelMargin.getValue()));
            realTimeData.setEngineTemperature(Double.valueOf(temp.getValue()));
            realTimeData.setSpeed(Double.valueOf(speed.getValue()));
            realTimeData.setCreateTime(engineSpeed.getTimestamp());
            realTimeData.setVehicleId(vehicleId);
            realTimeData.setInclination(Double.valueOf(inclination.getValue()));
        }catch (NullPointerException exception){
            log.info(" 获取数据失败 {}", exception);
            return ResultVOUtil.error(ResultEnum.OPEN_THREAD_FAIL);
        }



        int insert = realTimeDataMapper.insert(realTimeData);
        if (insert != 1) {
            return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
        }


        // 采集位置信息
        DataInfoDetail longitude = data.getObject(vehicleName + "@经度-" + vehicleId, DataInfoDetail.class);
        DataInfoDetail latitude = data.getObject(vehicleName + "@纬度-" + vehicleId, DataInfoDetail.class);

        Position position = new Position();
        position.setLatitude(String.valueOf(latitude.getValue()));
        position.setLongitude(String.valueOf(longitude.getValue()));
        position.setCreateTime(engineSpeed.getTimestamp());
        position.setVehicleId(vehicleId);
        Position latestPosition = positionMapper.getLatestPosition(vehicleId);
        if (vehicle.getRunningState().equals(OperatingStatusEnum.RUNNING.getValue()) && latestPosition != null){
            Optional.ofNullable(latestPosition);
            double distance = GetDistanceUtil.getDistance(
                    Double.valueOf(latitude.getValue()),
                    Double.valueOf(longitude.getValue()),
                    Double.valueOf(latestPosition.getLatitude()),
                    Double.valueOf(latestPosition.getLongitude())
            );
            vehicle.setMileage(vehicle.getMileage() + distance);
            vehicleMapper.updateByPrimaryKey(vehicle);
        }
        int positionInsert = positionMapper.insert(position);
        if (positionInsert != 1) {
            return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
        }

        // 报警
        alarmService.alarmInfo(realTimeData);

        // 是否进入电子围栏
        alarmService.accessRecordInfo(latestPosition, position);

        return ResultVOUtil.success();
    }

    @Override
    public ResultVO getStatusDataRead(long vehicleId) {
        ReadDataThread readDataThread = collectDataThreadConfig.getReadDataThreadMap().get(Integer.valueOf((int) vehicleId));
        log.info(" 车辆 数据采集线程 {} ",readDataThread);
        return ResultVOUtil.success(readDataThread == null ? false : readDataThread.getStatus());
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

        // 首先检查状态，是否在运行或被锁
        if (!checkDataThreadStatus(readDataParaForm.getVehicleIdList())) {
            return ResultVOUtil.error(ResultEnum.LOCKED_NOT_RUNNING);
        }

        ExecutorService threadPool = collectDataThreadConfig.getThreadPool();
        List<Integer> vehicleIdList = readDataParaForm.getVehicleIdList();

//        Vehicle vehicle = vehicleMapper.selectByPrimaryKey(readDataParaForm.getVehicleId());
        Map<Integer, ReadDataThread> readDataThreadMap = collectDataThreadConfig.getReadDataThreadMap();
        ReadDataThread collectDataThread = readDataThreadMap.get(vehicleIdList.get(0));
        if (collectDataThread == null){
            collectDataThread = new ReadDataThread("collectDataThread = carID -> " + vehicleIdList, readDataParaForm);
            collectDataThread.setFlag(true);
            threadPool.execute(collectDataThread);
            for (Integer vehicleId : vehicleIdList) {
                readDataThreadMap.put(vehicleId, collectDataThread);
            }
            return ResultVOUtil.success(ResultEnum.DATA_READ_OPENED);
        }else{
            if (collectDataThreadConfig.getReadDataThreadMap().size() == 0){
                collectDataThread.interrupt();
                collectDataThread.setFlag(false);
            }else {
                for (Integer vehicleId : vehicleIdList) {
                    readDataThreadMap.remove(vehicleId);
                }
            }
            return ResultVOUtil.success(ResultEnum.DATA_READ_SHUT_DOWNED);
        }
    }

    @Override
    public ResultVO getLatestPosition(Integer vehicleId) {
        if (!checkDataThreadStatus(vehicleId)){
            return ResultVOUtil.error(ResultEnum.LOCKED_NOT_RUNNING);
        }
        Position latestPosition = positionMapper.getLatestPosition(vehicleId);
        return ResultVOUtil.success(latestPosition);
    }

    public boolean checkDataThreadStatus(List<Integer> vehicleIdList){
        boolean flag = true;
        for (Integer vehicleId : vehicleIdList) {
            Vehicle vehicle = vehicleMapper.selectByPrimaryKey(vehicleId);
            if (vehicle.getRunningState().equals(OperatingStatusEnum.NOT_RUNNING.getValue())
                    || vehicle.getLockedState().equals(LockStatusEnum.LOCKED.getRole())
            ){
                log.info("车辆 {} 启动失败", vehicle);
                flag = false;break;
            }
        }
        return flag;
    }
    public boolean checkDataThreadStatus(Integer vehicleId){
        Vehicle vehicle = vehicleMapper.selectByPrimaryKey(vehicleId);
        if (vehicle.getRunningState().equals(OperatingStatusEnum.NOT_RUNNING.getValue())
                || vehicle.getLockedState().equals(LockStatusEnum.LOCKED.getRole())
        ){
            log.info("车辆 {} 启动失败", vehicle);
            return false;
        }
        return true;
    }
}
