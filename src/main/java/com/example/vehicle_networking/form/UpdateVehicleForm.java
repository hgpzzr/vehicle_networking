package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 21:20
 */
@Data
public class UpdateVehicleForm {
	@ApiModelProperty("汽车编号")
	@NotNull(message = "汽车编号不能为空")
	private Integer vehicleId;

	@ApiModelProperty("车牌号")
	@NotBlank(message = "车牌号不能为空")
	private String licensePlateNumber;

	@ApiModelProperty("汽车分类编号")
	@NotNull(message = "汽车分类编号不能为空")
	private Integer categoryId;
}
