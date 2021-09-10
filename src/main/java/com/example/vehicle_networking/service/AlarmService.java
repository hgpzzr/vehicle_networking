package com.example.vehicle_networking.service;

import com.example.vehicle_networking.vo.ResultVO;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 19:25
 */
public interface AlarmService {
	ResultVO alarm();

	ResultVO selectAlarmRecords(Integer vehicleId);
}
