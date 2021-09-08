package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.Position;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PositionMapper {
    int deleteByPrimaryKey(Integer positionId);

    int insert(Position record);

    Position selectByPrimaryKey(Integer positionId);

    List<Position> selectAll();

    int updateByPrimaryKey(Position record);

    List<Position> getHistoricalDataByVehicleId(Integer vehicleId);

    List<Position> getHistoricalDataByDate(HistoricalPositionFrom historicalPositionFrom);
}