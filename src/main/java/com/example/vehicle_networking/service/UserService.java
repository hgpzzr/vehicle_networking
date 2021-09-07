package com.example.vehicle_networking.service;

import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.form.LoginForm;
import com.example.vehicle_networking.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 11:27
 */
public interface UserService {
	/**
	 * 获取当前用户
	 * @return
	 */
	User getCurrentUser();

	/**
	 * 根据用户名获得用户
	 * @param userName
	 * @return
	 */
	User getUserByUserName(String userName);

	ResultVO login(LoginForm loginForm,HttpServletResponse response);
}
