package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.form.AddVehicleForm;
import com.example.vehicle_networking.service.VehicleService;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 21:08
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/vehicle")
@Api(tags = "车辆接口")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;


	@PostMapping("/insert")
	@ApiOperation("添加车辆")
	public ResultVO addVehicle(AddVehicleForm form){
		return vehicleService.addVehicle(form);
	}

	@DeleteMapping("/delete")
	@ApiOperation("删除车辆")
	public ResultVO deleteVehicle(Integer vehicleId){
		return vehicleService.deleteVehicle(vehicleId);
	}

}
