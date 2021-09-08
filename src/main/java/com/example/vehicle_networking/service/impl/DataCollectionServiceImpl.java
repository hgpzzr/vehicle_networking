package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.Position;
import com.example.vehicle_networking.entity.RealTimeData;
import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.mapper.PositionMapper;
import com.example.vehicle_networking.mapper.RealTimeDataMapper;
import com.example.vehicle_networking.service.DataCollectionService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 8:23
 */
@Service
@Slf4j
public class DataCollectionServiceImpl implements DataCollectionService {

    static final String speedUrl = "http://localhost:8888/user/getUserByName?name=Eic";
    @Autowired
    private RestTemplateTo restTemplateTo;
    @Autowired
    private RealTimeDataMapper realTimeDataMapper;
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public ResultVO getSpeedFromURL() {
        // 保存速度信息
        RealTimeData realTimeData = restTemplateTo.doGetWith1(speedUrl);
        log.info("data success : {}",realTimeData);
        int insert = realTimeDataMapper.insert(realTimeData);
        if (insert != 1) {
            return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
        }
        return ResultVOUtil.success();
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
}
