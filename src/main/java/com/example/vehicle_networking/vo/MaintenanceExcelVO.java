package com.example.vehicle_networking.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/13 20:32
 */
@Data
public class MaintenanceExcelVO {
	@ExcelProperty("维修编号")
	private Integer maintenanceId;

	@ExcelProperty("车辆编号")
	private Integer vehicleId;

	@ExcelProperty("车牌号")
	private String licenseNumber;

	@ExcelProperty("描述")
	private String describe;

	@ExcelProperty("维修费用")
	private Double maintenanceCosts;

	@ExcelProperty("维修部分")
	private String maintenancePart;

	@ExcelProperty("维修开始时间")
	private String maintenanceBeginTime;

	@ExcelProperty("维修结束时间")
	private String maintenanceEndTime;

	@ExcelProperty("记录创建时间")
	private String createTime;

}
