package com.example.vehicle_networking.service;

import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.form.AddVehicleForm;
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
}
