package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.mapper.UserMapper;
import com.example.vehicle_networking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 11:27
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;


	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		String key = "anonymousUser";
		if (!userName.equals(key)) {
			return getUserByUserName(userName);
		}
		return null;
	}

	@Override
	public User getUserByUserName(String userName) {
		User user = userMapper.getUserByUserName(userName);
		return user;
	}
}
