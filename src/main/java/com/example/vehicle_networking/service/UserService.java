package com.example.vehicle_networking.service;

import com.example.vehicle_networking.entity.User;

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

	User getUserByUserName(String userName);
}
