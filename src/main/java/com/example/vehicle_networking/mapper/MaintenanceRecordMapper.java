package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.MaintenanceRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRecordMapper {
    int deleteByPrimaryKey(Integer maintenanceId);

    int insert(MaintenanceRecord record);

    MaintenanceRecord selectByPrimaryKey(Integer maintenanceId);

    List<MaintenanceRecord> selectAll();

    List<MaintenanceRecord> selectByVehicleId(Integer vehicleId);

    int updateByPrimaryKey(MaintenanceRecord record);
}