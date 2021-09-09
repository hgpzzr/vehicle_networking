package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 21:00
 */
@Data
public class AddVehicleForm {
	@ApiModelProperty("车牌号")
	@NotBlank(message = "车牌号不能为空")
	private String licensePlateNumber;

	@ApiModelProperty("总里程数")
	@NotNull(message = "总里程数不能为空")
	private Double mileage;

	@ApiModelProperty("汽车分类编号")
	@NotNull(message = "汽车分类编号不能为空")
	private Integer categoryId;
}
