package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.service.DataCollectionService;
import com.example.vehicle_networking.service.impl.DataCollectionServiceImpl;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 8:41
 */
@RestController("/dataCollect")
@Slf4j
@CrossOrigin
@Api(tags = "实时数据采集")
public class DataCollectionController {
    @Autowired
    private DataCollectionService dataCollectionService;

    /**
     * 获取实时速度数据
     */
    @ApiOperation("获取速度的实时信息")
    @GetMapping("/getRealSpeedData")
    public ResultVO getRealSpeedData(@RequestParam Integer vehicleId){
        return dataCollectionService.getRealSpeedData(vehicleId);
    }

    /**
     * 获取车辆的历史位置数据
     * @return
     */
    @PostMapping("/getHistoricalPositionByDate")
    @ApiOperation("获取车辆的历史位置数据")
    public ResultVO getHistoricalPositionByDate(@RequestBody HistoricalPositionFrom historicalPositionFrom){
        return dataCollectionService.getHistoricalPosition(historicalPositionFrom);
    }

}
