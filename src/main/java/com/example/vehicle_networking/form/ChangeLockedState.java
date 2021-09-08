package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 21:39
 */
@Data
public class ChangeLockedState {
	@ApiModelProperty("汽车编号")
	@NotNull(message = "汽车编号不能为空")
	private Integer vehicleId;

	@ApiModelProperty("锁机状态（0为未锁机，1 为锁机）")
	@NotNull(message = "锁机状态不能为空")
	private Integer lockedState;
}
