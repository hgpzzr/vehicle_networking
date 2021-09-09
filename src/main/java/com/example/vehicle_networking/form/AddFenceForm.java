package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 19:18
 */
@Data
public class AddFenceForm {
	@ApiModelProperty("工地编号")
	@NotNull(message = "工地编号不能为空")
	private Integer constructionSiteId;

	@ApiModelProperty("中心经度")
	@NotNull(message = "中心经度不能为空")
	private Double longitude;

	@ApiModelProperty("中心纬度")
	@NotNull(message = "中心纬度不能为空")
	private Double latitude;

	@ApiModelProperty("半径")
	@NotNull(message = "半径不能为空")
	private Double radius;
}
