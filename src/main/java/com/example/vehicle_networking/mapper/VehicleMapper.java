package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMapper {
    int deleteByPrimaryKey(Integer vehicleId);

    int insert(Vehicle record);

    Vehicle selectByPrimaryKey(Integer vehicleId);

    List<Vehicle> selectAll();

    int updateByPrimaryKey(Vehicle record);
}