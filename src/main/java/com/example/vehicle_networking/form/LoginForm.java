package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 18:51
 */
@Data
public class LoginForm {
	@ApiModelProperty("用户名")
	@NotBlank(message = "用户名不能为空")
	private String userName;

	@ApiModelProperty("密码")
	@NotBlank(message = "密码不能为空")
	private String password;
}
