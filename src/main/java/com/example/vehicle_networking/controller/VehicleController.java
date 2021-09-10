package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.form.AddVehicleForm;
import com.example.vehicle_networking.form.ChangeLockedState;
import com.example.vehicle_networking.form.ChangeRunningState;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.form.UpdateVehicleForm;
import com.example.vehicle_networking.service.VehicleService;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
	public ResultVO addVehicle(@Valid AddVehicleForm form){
		return vehicleService.addVehicle(form);
	}

	@DeleteMapping("/delete")
	@ApiOperation("删除车辆")
	public ResultVO deleteVehicle(Integer vehicleId){
		return vehicleService.deleteVehicle(vehicleId);
	}

	@PutMapping("/update")
	@ApiOperation("更新车辆基本信息")
	public ResultVO updateVehicle(@Valid UpdateVehicleForm form){
		return vehicleService.updateVehicle(form);
	}

	@PutMapping("/update_runningState")
	@ApiOperation("改变汽车运行状态")
	public ResultVO updateRunningState(@Valid ChangeRunningState form){
		return vehicleService.updateRunningState(form);
	}

	@PostMapping("/getOilConsume")
	@ApiOperation("获取车辆的油耗")
	public ResultVO getOilConsume(@RequestBody @Valid HistoricalPositionFrom historicalPositionFrom){
		return vehicleService.getVehicleHisOilUsed(historicalPositionFrom);
	}

	@GetMapping("/select")
	@ApiOperation("根据分类编号和车牌号的模糊查询进行车辆查询，不传值则查询全部，并根据权限查询车辆，普通用户查询自己的车辆，管理员查询所有车辆")
	public ResultVO selectVehicles(Integer categoryId,String licenseNumber){
		return vehicleService.selectVehicles(categoryId,licenseNumber);
	}


	@PutMapping("/update_lockedState")
	@ApiOperation("改变车辆锁机状态")
	public ResultVO updateLockedState(@Valid ChangeLockedState form){
		return vehicleService.updateLockedState(form);
	}

}
