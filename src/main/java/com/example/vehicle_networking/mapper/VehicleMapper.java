package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.Vehicle;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleMapper {
    int deleteByPrimaryKey(Integer vehicleId);

    int insert(Vehicle record);

    Vehicle selectByPrimaryKey(Integer vehicleId);

    List<Vehicle> selectAll();

    List<Vehicle> selectByUserId(Integer userId);


    List<Vehicle> fuzzyQuery(Integer categoryId,String licenseNumber,Integer userId,Integer vehicleId);

    int updateByPrimaryKey(Vehicle record);
}