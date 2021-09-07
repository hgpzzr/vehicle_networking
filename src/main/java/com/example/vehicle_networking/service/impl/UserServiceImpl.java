package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.LoginForm;
import com.example.vehicle_networking.mapper.UserMapper;
import com.example.vehicle_networking.security.JwtProperties;
import com.example.vehicle_networking.security.JwtUserDetailServiceImpl;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.utils.JwtTokenUtil;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 11:27
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JwtUserDetailServiceImpl jwtUserDetailService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtProperties jwtProperties;


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

	@Override
	public ResultVO login(LoginForm loginForm, HttpServletResponse response) {
		User user = userMapper.getUserByUserName(loginForm.getUserName());
		if (user == null) {
			log.error("【登录】：用户不存在");
			return ResultVOUtil.error(ResultEnum.USER_NOT_EXIST_ERROR);
		}
		UserDetails userDetails = jwtUserDetailService.loadUserByUsername(loginForm.getUserName());
		if (!(new BCryptPasswordEncoder().matches(loginForm.getPassword(), userDetails.getPassword()))) {
			log.error("【登录】：密码错误");
			return ResultVOUtil.error(ResultEnum.PASSWORD_ERROR);
		}
		Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getUserName(), loginForm.getPassword(), userDetails.getAuthorities());
		Authentication authentication = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		final String realToken = jwtTokenUtil.generateToken(userDetails);
		response.addHeader(jwtProperties.getTokenName(), realToken);
		Map<String, Serializable> map = new HashMap<>();
		map.put("userName", user.getUserName());
		map.put("userId", user.getUserId());
		map.put("role", user.getRole());
		map.put("realName", user.getRealName());
		map.put("token",realToken);
		return ResultVOUtil.success(map);
	}
}
