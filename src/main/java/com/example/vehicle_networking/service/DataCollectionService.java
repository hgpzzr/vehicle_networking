package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.vo.ResultVO;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 8:23
 */
public interface DataCollectionService {
    /**
     * 获取速度信息
     * @return
     */
    ResultVO getSpeedFromURL();

    /**
     * 获取实时速度数据
     * @return
     */
    ResultVO getRealSpeedData(Integer vehicleId);

    /**
     * 获取车辆历史速度数据
     * @param historicalPositionFrom
     * @return
     */
    ResultVO getHistoricalPosition(HistoricalPositionFrom historicalPositionFrom);
}
