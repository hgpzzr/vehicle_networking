package com.example.vehicle_networking.service;

import com.example.vehicle_networking.entity.Position;
import com.example.vehicle_networking.entity.RealTimeData;
import com.example.vehicle_networking.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 19:25
 */
public interface AlarmService {
	/**
	 * 自动报警
	 * @return
	 */
	ResultVO alarm();

	/**
	 * 查询报警记录，自动根据权限查询
	 * @param vehicleId
	 * @return
	 */
	ResultVO selectAlarmRecords(Integer vehicleId);

	/**
	 * 出入记录
	 * @return
	 */
	ResultVO accessRecord();


	void alarmInfo(RealTimeData realTimeData);

	/**
	 * 判断是否进出电子围栏
	 * @param latestPosition
	 * @param secondPosition
	 */
	void accessRecordInfo(Position latestPosition, Position secondPosition);

	void exportAlarm(HttpServletResponse response,Integer vehicleId);
}
