package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.VehicleCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(VehicleCategory record);

    VehicleCategory selectByPrimaryKey(Integer categoryId);

    List<VehicleCategory> selectAll();

    int updateByPrimaryKey(VehicleCategory record);
}