package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 20:27
 */
@Data
public class UpdateFenceForm {
	@ApiModelProperty("电子围栏编号")
	@NotNull(message = "电子围栏编号不能为空")
	private Integer fenceId;

	@ApiModelProperty("中心经度")
	@NotBlank(message = "中心经度不能为空")
	private String longitude;

	@ApiModelProperty("中心纬度")
	@NotBlank(message = "中心纬度不能为空")
	private String latitude;

	@ApiModelProperty("半径")
	@NotNull(message = "半径不能为空")
	private Double radius;
}
