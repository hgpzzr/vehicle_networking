package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.MaintenanceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceInfoMapper {
    int deleteByPrimaryKey(Integer maintenanceInfoId);

    int insert(MaintenanceInfo record);

    MaintenanceInfo selectByPrimaryKey(Integer maintenanceInfoId);

    List<MaintenanceInfo> selectAll();

    List<MaintenanceInfo> selectByMaintenanceId(Integer maintenanceId);

    int updateByPrimaryKey(MaintenanceInfo record);

    int deleteByMaintenanceRecordId(Integer recordId);
}