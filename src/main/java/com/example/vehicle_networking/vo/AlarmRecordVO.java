package com.example.vehicle_networking.vo;

import lombok.Data;

import java.util.Comparator;
import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/9 18:16
 */
@Data
public class AlarmRecordVO implements Comparable<AlarmRecordVO> {
	private Integer alarmId;

	private Integer vehicleId;

	private String alarmReason;

	private String createTime;

	private Integer type;

	private Double numericalValue;


	@Override
	public int compareTo(AlarmRecordVO o) {
		return this.createTime.compareTo(o.createTime);
	}
}
