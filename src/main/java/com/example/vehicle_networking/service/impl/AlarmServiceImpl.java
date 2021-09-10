package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.AlarmRecord;
import com.example.vehicle_networking.entity.RealTimeData;
import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.mapper.AlarmRecordMapper;
import com.example.vehicle_networking.mapper.RealTimeDataMapper;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.AlarmService;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.AlarmRecordVO;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 19:25
 */
@Service
@EnableScheduling
@Slf4j
public class AlarmServiceImpl implements AlarmService {
	@Autowired
	private RealTimeDataMapper realTimeDataMapper;
	@Autowired
	private VehicleMapper vehicleMapper;
	@Autowired
	private AlarmRecordMapper alarmRecordMapper;
	@Autowired
	private UserService userService;

	@Override
	@Scheduled(cron = "* */1 * * * ?")
	public ResultVO alarm() {
		List<Vehicle> vehicleList = vehicleMapper.selectAll();
		List<AlarmRecord> alarmRecordList = new ArrayList<>();
		String temperatureReason = "发动机温度过高";
		String speedReason = "车速过快";
		String inclinationReason = "车辆过于倾斜";
		for (Vehicle vehicle : vehicleList) {
			if (vehicle.getRunningState() != 2 || vehicle.getLockedState() == 1) {
				continue;
			}
			RealTimeData realTimeData = realTimeDataMapper.getRealTimeDataOneByVehicleId(vehicle.getVehicleId());
			if (realTimeData == null) {
				continue;
			}
			// 高温报警
			if (realTimeData.getEngineTemperature() > 100) {
				AlarmRecord alarmRecord = new AlarmRecord();
				alarmRecord.setAlarmReason(temperatureReason);
				alarmRecord.setCreateTime(new Date());
				alarmRecord.setType(0);
				alarmRecord.setNumericalValue(realTimeData.getEngineTemperature());
				alarmRecord.setVehicleId(realTimeData.getVehicleId());
				alarmRecordList.add(alarmRecord);
			}
			// 超速报警
			if (realTimeData.getEngineTemperature() > 95) {
				AlarmRecord alarmRecord = new AlarmRecord();
				alarmRecord.setAlarmReason(speedReason);
				alarmRecord.setCreateTime(new Date());
				alarmRecord.setType(0);
				alarmRecord.setNumericalValue(realTimeData.getSpeed());
				alarmRecord.setVehicleId(realTimeData.getVehicleId());
				alarmRecordList.add(alarmRecord);
			}
			// 倾斜度报警
			if (realTimeData.getInclination() > 15) {
				AlarmRecord alarmRecord = new AlarmRecord();
				alarmRecord.setAlarmReason(inclinationReason);
				alarmRecord.setCreateTime(new Date());
				alarmRecord.setType(0);
				alarmRecord.setNumericalValue(realTimeData.getInclination());
				alarmRecord.setVehicleId(realTimeData.getVehicleId());
				alarmRecordList.add(alarmRecord);
			}
		}
		log.info("alarmRecordList:{}", alarmRecordList.toString());
		if (alarmRecordList.size() != 0) {
			alarmRecordMapper.batchInsert(alarmRecordList);
		}
		return ResultVOUtil.success();
	}

	@Override
	public ResultVO selectAlarmRecords(Integer vehicleId) {
		User currentUser = userService.getCurrentUser();
		List<AlarmRecordVO> alarmRecordVOList = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (currentUser.getRole() == 0) {
			Vehicle vehicle = vehicleMapper.selectByPrimaryKey(vehicleId);
			if (vehicle != null) {
				if(vehicle.getUserId() != currentUser.getUserId()){
					return ResultVOUtil.success();
				}
				List<AlarmRecord> alarmRecordList = alarmRecordMapper.selectByVehicleId(vehicleId);
				for (AlarmRecord alarmRecord : alarmRecordList) {
					AlarmRecordVO alarmRecordVO = new AlarmRecordVO();
					BeanUtils.copyProperties(alarmRecord, alarmRecordVO);
					alarmRecordVO.setCreateTime(simpleDateFormat.format(alarmRecord.getCreateTime()));
					alarmRecordList.add(alarmRecord);
				}
			} else {
				List<Vehicle> vehicleList = vehicleMapper.selectByUserId(currentUser.getUserId());
				for (Vehicle vehicle1 : vehicleList) {
					List<AlarmRecord> alarmRecordList = alarmRecordMapper.selectByVehicleId(vehicle1.getVehicleId());
					for (AlarmRecord alarmRecord : alarmRecordList) {
						AlarmRecordVO alarmRecordVO = new AlarmRecordVO();
						BeanUtils.copyProperties(alarmRecord,alarmRecordVO);
						alarmRecordVO.setCreateTime(simpleDateFormat.format(alarmRecord.getCreateTime()));
						alarmRecordVOList.add(alarmRecordVO);
					}
				}
			}
		}
		else {
			List<AlarmRecord> alarmRecordList = alarmRecordMapper.selectByVehicleId(vehicleId);
			for(AlarmRecord alarmRecord : alarmRecordList){
				AlarmRecordVO alarmRecordVO = new AlarmRecordVO();
				BeanUtils.copyProperties(alarmRecord,alarmRecordVO);
				alarmRecordVO.setCreateTime(simpleDateFormat.format(alarmRecord.getCreateTime()));
				alarmRecordVOList.add(alarmRecordVO);
			}
		}
		// 按时间排序
		Collections.sort(alarmRecordVOList, new Comparator<AlarmRecordVO>() {
			@Override
			public int compare(AlarmRecordVO o1, AlarmRecordVO o2) {
				return o2.getCreateTime().compareTo(o1.getCreateTime());
			}
		});
		return ResultVOUtil.success(alarmRecordVOList);
	}

}
