package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.RealTimeData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealTimeDataMapper {
    int deleteByPrimaryKey(Integer realTimeId);

    int insert(RealTimeData record);

    RealTimeData selectByPrimaryKey(Integer realTimeId);

    List<RealTimeData> selectAll();

    int updateByPrimaryKey(RealTimeData record);

    /**
     * 获取最新数据
     * @return
     */
    RealTimeData getRealTimeDataOneByVehicleId(Integer vehicleId);


}