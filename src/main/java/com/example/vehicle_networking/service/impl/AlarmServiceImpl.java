package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.RealTimeData;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.mapper.RealTimeDataMapper;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.AlarmService;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	@Override
	public ResultVO temperatureAlarm() {
		List<Vehicle> vehicleList = vehicleMapper.selectAll();
		List<RealTimeData> realTimeDataList = new ArrayList<>();
		for (Vehicle vehicle : vehicleList) {
			RealTimeData realTimeData = realTimeDataMapper.getRealTimeDataOneByVehicleId(vehicle.getVehicleId());
			if(realTimeData.getEngineTemperature() > 100){

			}
		}
		return null;
	}
}
