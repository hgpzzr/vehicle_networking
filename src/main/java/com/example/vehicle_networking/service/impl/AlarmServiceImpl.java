package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.AlarmRecord;
import com.example.vehicle_networking.entity.RealTimeData;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.mapper.AlarmRecordMapper;
import com.example.vehicle_networking.mapper.RealTimeDataMapper;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.AlarmService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 19:25
 */
@Service
public class AlarmServiceImpl implements AlarmService {
	@Autowired
	private RealTimeDataMapper realTimeDataMapper;
	@Autowired
	private VehicleMapper vehicleMapper;
	@Autowired
	private AlarmRecordMapper alarmRecordMapper;

	@Override
	public ResultVO alarm() {
		List<Vehicle> vehicleList = vehicleMapper.selectAll();
		List<AlarmRecord> alarmRecordList = new ArrayList<>();
		String temperatureReason = "发动机温度过高";
		String speedReason = "车速过快";
		String inclinationReason = "车辆过于倾斜";
		for (Vehicle vehicle : vehicleList) {
			RealTimeData realTimeData = realTimeDataMapper.getRealTimeDataOneByVehicleId(vehicle.getVehicleId());
			// 高温报警
			if(realTimeData.getEngineTemperature() > 100){
				AlarmRecord alarmRecord = new AlarmRecord();
				alarmRecord.setAlarmReason(temperatureReason);
				alarmRecord.setCreateTime(new Date());
				alarmRecord.setType(0);
				alarmRecord.setNumericalValue(realTimeData.getEngineTemperature());
				alarmRecord.setVehicleId(realTimeData.getVehicleId());
				alarmRecordList.add(alarmRecord);
			}
			// 超速报警
			if(realTimeData.getEngineTemperature() > 95){
				AlarmRecord alarmRecord = new AlarmRecord();
				alarmRecord.setAlarmReason(speedReason);
				alarmRecord.setCreateTime(new Date());
				alarmRecord.setType(0);
				alarmRecord.setNumericalValue(realTimeData.getSpeed());
				alarmRecord.setVehicleId(realTimeData.getVehicleId());
				alarmRecordList.add(alarmRecord);
			}
			// 倾斜度报警
			if(realTimeData.getInclination() > 15){
				AlarmRecord alarmRecord = new AlarmRecord();
				alarmRecord.setAlarmReason(inclinationReason);
				alarmRecord.setCreateTime(new Date());
				alarmRecord.setType(0);
				alarmRecord.setNumericalValue(realTimeData.getInclination());
				alarmRecord.setVehicleId(realTimeData.getVehicleId());
				alarmRecordList.add(alarmRecord);
			}
		}
		alarmRecordMapper.batchInsert(alarmRecordList);
		return ResultVOUtil.success();
	}

	@Override
	public ResultVO temperatureAlarm() {
		return null;
	}
}
