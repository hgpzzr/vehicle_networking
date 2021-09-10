package com.example.vehicle_networking.service;

import com.example.vehicle_networking.vo.ResultVO;
import org.springframework.stereotype.Service;

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
}
