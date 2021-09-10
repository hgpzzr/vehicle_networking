package com.example.vehicle_networking.service;

import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.form.AddVehicleForm;
import com.example.vehicle_networking.form.ChangeLockedState;
import com.example.vehicle_networking.form.ChangeRunningState;
import com.example.vehicle_networking.form.UpdateVehicleForm;
import com.example.vehicle_networking.vo.ResultVO;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 20:58
 */
public interface VehicleService {
	/**
	 * 添加车辆
	 * @param form
	 * @return
	 */
	ResultVO addVehicle(AddVehicleForm form);

	/**
	 * 删除车辆
	 * @param vehicleId
	 * @return
	 */
	ResultVO deleteVehicle(Integer vehicleId);

	/**
	 * 更新汽车基本信息
	 * @param form
	 * @return
	 */
	ResultVO updateVehicle(UpdateVehicleForm form);

	ResultVO updateRunningState(ChangeRunningState form);


	/**
	 * 改变车辆锁机状态
	 * @param form
	 * @return
	 */
	ResultVO updateLockedState(ChangeLockedState form);

	/**
	 * 根据权限查询车辆，普通用户查询自己的车辆，管理员查询所有车辆
	 * @return
	 */
	ResultVO selectVehicles(Integer categoryId,String licenseNumber);
}
