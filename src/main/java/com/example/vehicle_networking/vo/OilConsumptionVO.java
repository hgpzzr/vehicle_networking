package com.example.vehicle_networking.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/12 14:09
 */
@Data
public class OilConsumptionVO {
	@ExcelProperty("油耗编号")
	private Integer oilConsumptionId;

	@ExcelProperty("汽车编号")
	private Integer vehicleId;

	@ExcelProperty("车牌号")
	private String licenseNumber;

	@ExcelProperty("油耗（L）")
	private Double oilConsumption;

	@ExcelProperty("工作时长（h）")
	private Double workTime;

	@ExcelProperty("日期")
	private String date;
}
