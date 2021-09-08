package com.example.vehicle_networking.mapper;

import com.example.vehicle_networking.entity.AlarmRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRecordMapper {
    int deleteByPrimaryKey(Integer alarmId);

    int insert(AlarmRecord record);

    AlarmRecord selectByPrimaryKey(Integer alarmId);

    List<AlarmRecord> selectAll();

    int updateByPrimaryKey(AlarmRecord record);

    int batchInsert(List<AlarmRecord> alarmRecordList);
}