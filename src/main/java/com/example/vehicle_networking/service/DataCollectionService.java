package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.form.ReadDataParaForm;
import com.example.vehicle_networking.vo.ResultVO;
import org.springframework.stereotype.Service;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 8:23
 */
@Service
public interface DataCollectionService {
    /**
     * 获取速度信息
     * @return
     */
    ResultVO getSpeedFromURL(String url, String cookie, Integer vehicleId);

    /**
     * 获取读取数据的状态
     * @return
     */
    ResultVO getStatusDataRead();

    /**
     * 开启或关闭实时数据读取
     * @return
     */
    ResultVO openOrDownRealDataCollect(ReadDataParaForm readDataParaForm);

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
