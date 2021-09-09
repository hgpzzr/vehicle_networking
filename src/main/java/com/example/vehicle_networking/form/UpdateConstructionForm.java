package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 11:49
 */
@Data
public class UpdateConstructionForm {
	@ApiModelProperty("工地编号")
	@NotNull(message = "工地编号不能为空")
	private Integer constructionId;

	@ApiModelProperty("经度")
	@NotNull(message = "经度不能为空")
	private Double longitude;

	@ApiModelProperty("纬度")
	@NotNull(message = "纬度不能为空")
	private Double latitude;

	@ApiModelProperty("工地名称")
	@NotBlank(message = "工地名称不能为空")
	private String constructionSiteName;
}
