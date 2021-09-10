package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.*;
import com.example.vehicle_networking.enums.LockStatusEnum;
import com.example.vehicle_networking.enums.OperatingStatusEnum;
import com.example.vehicle_networking.mapper.*;
import com.example.vehicle_networking.service.AlarmService;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.utils.GetDistanceUtil;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.AlarmRecordVO;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Autowired
	private ElectronicFenceMapper electronicFenceMapper;
	@Autowired
	private ConstructionSiteMapper constructionSiteMapper;
	@Autowired
	private PositionMapper positionMapper;

	@Value("${alarm.reason.temperature}")
	private String temperatureReason;
	@Value("${alarm.reason.speed}")
	private String speedReason;
	@Value("${alarm.reason.inclination}")
	private String inclinationReason;
	@Value("${alarm.reason.out}")
	private String outFence;
	@Value("${alarm.reason.in}")
	private String inFence;

	@Override
	@Scheduled(cron = "*/5 * * * * ?")
	public ResultVO alarm() {
		List<Vehicle> vehicleList = vehicleMapper.selectAll();
		List<AlarmRecord> alarmRecordList = new ArrayList<>();
		for (Vehicle vehicle : vehicleList) {
			if (vehicle.getRunningState() != OperatingStatusEnum.RUNNING.getValue() || vehicle.getLockedState() == LockStatusEnum.LOCKED.getValue()) {
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
			if (realTimeData.getSpeed() > 95) {
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
				if (vehicle.getUserId() != currentUser.getUserId()) {
					return ResultVOUtil.success();
				}
				List<AlarmRecord> alarmRecordList = alarmRecordMapper.selectByVehicleId(vehicleId);
				log.info("alarmRecordList:{}",alarmRecordList);
				for (AlarmRecord alarmRecord : alarmRecordList) {
					AlarmRecordVO alarmRecordVO = new AlarmRecordVO();
					BeanUtils.copyProperties(alarmRecord, alarmRecordVO);
					alarmRecordVO.setCreateTime(simpleDateFormat.format(alarmRecord.getCreateTime()));
					alarmRecordVOList.add(alarmRecordVO);
				}
			} else {
				List<Vehicle> vehicleList = vehicleMapper.selectByUserId(currentUser.getUserId());
				for (Vehicle vehicle1 : vehicleList) {
					List<AlarmRecord> alarmRecordList = alarmRecordMapper.selectByVehicleId(vehicle1.getVehicleId());
					for (AlarmRecord alarmRecord : alarmRecordList) {
						AlarmRecordVO alarmRecordVO = new AlarmRecordVO();
						BeanUtils.copyProperties(alarmRecord, alarmRecordVO);
						alarmRecordVO.setCreateTime(simpleDateFormat.format(alarmRecord.getCreateTime()));
						alarmRecordVOList.add(alarmRecordVO);
					}
				}
			}
		} else {
			List<AlarmRecord> alarmRecordList = alarmRecordMapper.selectByVehicleId(vehicleId);
			for (AlarmRecord alarmRecord : alarmRecordList) {
				AlarmRecordVO alarmRecordVO = new AlarmRecordVO();
				BeanUtils.copyProperties(alarmRecord, alarmRecordVO);
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

	@Scheduled(cron = "0 */1 * * * ?")
	@Override
	public ResultVO accessRecord() {
		// 查出所有车辆
		List<Vehicle> vehicleList = vehicleMapper.selectAll();
		List<AlarmRecord> alarmRecordList = new ArrayList<>();
		for (Vehicle vehicle : vehicleList) {
			// 查出最新数据
			Position latestPosition = positionMapper.getLatestPosition(vehicle.getVehicleId());
			// 判断车辆运行状态
			if (vehicle.getRunningState() != 2 || vehicle.getLockedState() == 1) {
				continue;
			}
			// 查出第二新数据
			Position secondPosition = positionMapper.getSecondPosition(vehicle.getVehicleId());
			// 查出工地
			List<ConstructionSite> constructionSiteList = constructionSiteMapper.selectByUserId(vehicle.getUserId());
			// 查出对应用户的所有电子围栏
			for (ConstructionSite constructionSite : constructionSiteList) {
				ElectronicFence electronicFence = electronicFenceMapper.selectByConstructionId(constructionSite.getConstructionSiteId());
				if(electronicFence == null){
					continue;
				}
				// 开始对比数据，记录进出围栏数据
				log.info("latestPosition:{}",latestPosition.toString());
				log.info("electronicFence:{}",electronicFence.toString());
				double newDistance = GetDistanceUtil.getDistance(new Double(latestPosition.getLatitude()),new Double(latestPosition.getLongitude()),new Double(electronicFence.getLatitude()),new Double(electronicFence.getLongitude()));
				double oldDistance = GetDistanceUtil.getDistance(new Double(secondPosition.getLatitude()),new Double(secondPosition.getLongitude()),new Double(electronicFence.getLatitude()),new Double(electronicFence.getLongitude()));
				if((newDistance - electronicFence.getRadius()) > 0 && (oldDistance-electronicFence.getRadius()) < 0){
					AlarmRecord alarmRecord = new AlarmRecord();
					alarmRecord.setVehicleId(vehicle.getVehicleId());
					alarmRecord.setType(1);
					alarmRecord.setCreateTime(new Date());
					alarmRecord.setAlarmReason(outFence);
					alarmRecordList.add(alarmRecord);
				}
				if((newDistance - electronicFence.getRadius()) < 0 && (oldDistance-electronicFence.getRadius()) > 0){
					AlarmRecord alarmRecord = new AlarmRecord();
					alarmRecord.setVehicleId(vehicle.getVehicleId());
					alarmRecord.setType(2);
					alarmRecord.setCreateTime(new Date());
					alarmRecord.setAlarmReason(inFence);
					alarmRecordList.add(alarmRecord);
				}
			}
		}
		log.info("alarmRecordList:{}",alarmRecordList.toString());
		if(alarmRecordList.size() != 0){
			alarmRecordMapper.batchInsert(alarmRecordList);
		}
		return ResultVOUtil.success();
	}

}
