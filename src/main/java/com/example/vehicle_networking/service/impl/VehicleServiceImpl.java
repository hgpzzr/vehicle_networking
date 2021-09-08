package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.AddVehicleForm;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.service.VehicleService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 20:59
 */
@Service
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleMapper vehicleMapper;
	@Autowired
	private UserService userService;


	@Override
	public ResultVO addVehicle(AddVehicleForm form) {
		Vehicle vehicle = new Vehicle();
		BeanUtils.copyProperties(form,vehicle);
		vehicle.setCreateTime(new Date());
		vehicle.setRunningState(0);
		vehicle.setLockedState(0);
		User currentUser = userService.getCurrentUser();
		vehicle.setUserId(currentUser.getUserId());
		int insert = vehicleMapper.insert(vehicle);
		if(insert != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("添加成功");
	}
}
