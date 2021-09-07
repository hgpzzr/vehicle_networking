package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.form.LoginForm;
import com.example.vehicle_networking.form.RegisterForm;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 18:58
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	@ApiOperation("登录")
	public ResultVO login(@Valid LoginForm loginForm, HttpServletResponse response) {
		return userService.login(loginForm, response);
	}

	@PostMapping("/register")
	@ApiOperation("注册")
	public ResultVO register(@Valid RegisterForm registerForm) {
		return userService.register(registerForm);
	}
}
