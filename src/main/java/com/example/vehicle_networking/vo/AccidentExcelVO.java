package com.example.vehicle_networking.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/13 9:53
 */
@Data
public class AccidentExcelVO {
	@ExcelProperty("事故编号")
	private Integer accidentId;

	@ExcelProperty("汽车编号")
	private Integer vehicleId;

	@ExcelProperty("车牌号")
	private String licenseNumber;

	@ExcelProperty("事故原因")
	private String accidentReason;

	@ExcelProperty("发生地点")
	private String occurrenceSite;

	@ExcelProperty("损坏情况")
	private String damageCondition;

	@ExcelProperty("损坏时间")
	private Date createTime;
}
