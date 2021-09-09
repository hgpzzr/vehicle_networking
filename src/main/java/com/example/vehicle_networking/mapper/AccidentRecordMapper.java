package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.AccidentRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccidentRecordMapper {
    int deleteByPrimaryKey(Integer accidentId);

    int insert(AccidentRecord record);

    AccidentRecord selectByPrimaryKey(Integer accidentId);

    List<AccidentRecord> selectAll();

    int updateByPrimaryKey(AccidentRecord record);
}