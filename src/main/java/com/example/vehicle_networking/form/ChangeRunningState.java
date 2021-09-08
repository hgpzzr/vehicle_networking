package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 21:29
 */
@Data
public class ChangeRunningState {
	@ApiModelProperty("汽车编号")
	@NotNull(message = "汽车编号不能为空")
	private Integer vehicleId;

	@ApiModelProperty("运行状态（0为未运行，1熄火，2为运行中）")
	@NotNull(message = "运行状态不能为空")
	private Integer runningState;
}
