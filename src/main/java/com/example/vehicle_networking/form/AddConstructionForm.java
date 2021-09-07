package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 21:12
 */
@Data
public class AddConstructionForm {
	@ApiModelProperty("经度")
	@NotBlank(message = "经度不能为空")
	private String longitude;

	@ApiModelProperty("纬度")
	@NotBlank(message = "纬度不能为空")
	private String latitude;

	@ApiModelProperty("工地名称")
	@NotBlank(message = "工地名称不能为空")
	private String constructionSiteName;
}
