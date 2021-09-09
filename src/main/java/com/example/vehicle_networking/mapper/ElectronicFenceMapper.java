package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.ElectronicFence;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectronicFenceMapper {
    int deleteByPrimaryKey(Integer fenceId);

    int insert(ElectronicFence record);

    ElectronicFence selectByPrimaryKey(Integer fenceId);

    ElectronicFence selectByConstructionId(Integer constructionId);

    List<ElectronicFence> selectAll();

    int updateByPrimaryKey(ElectronicFence record);
}