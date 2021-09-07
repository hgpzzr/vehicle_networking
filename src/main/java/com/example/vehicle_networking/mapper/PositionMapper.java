package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionMapper {
    int deleteByPrimaryKey(Integer positionId);

    int insert(Position record);

    Position selectByPrimaryKey(Integer positionId);

    List<Position> selectAll();

    int updateByPrimaryKey(Position record);
}