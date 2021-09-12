package com.example.vehicle_networking.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/12 15:07
 */
@Data
public class AlarmExcelVO {
	@ExcelProperty("报警编号")
	private Integer alarmId;

	@ExcelProperty("汽车编号")
	private Integer vehicleId;

	@ExcelProperty("车牌号")
	private String licenseNumber;

	@ExcelProperty("报警原因")
	private String alarmReason;

	@ExcelProperty("异常数值")
	private Double numericalValue;

	@ExcelProperty("报警时间")
	private Date createTime;

}
