package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.OilConsumptionRecord;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OilConsumptionRecordMapper {
    int deleteByPrimaryKey(Integer oilConsumptionId);

    int insert(OilConsumptionRecord record);

    OilConsumptionRecord selectByPrimaryKey(Integer oilConsumptionId);

    List<OilConsumptionRecord> selectAll();

    int updateByPrimaryKey(OilConsumptionRecord record);

    OilConsumptionRecord getLatestConsumption(Integer vehicleId);

    List<OilConsumptionRecord> getHistoricalOilConsumptionRecord(HistoricalPositionFrom historicalPositionFrom);
}