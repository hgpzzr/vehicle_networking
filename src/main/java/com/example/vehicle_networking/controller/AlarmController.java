package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.service.AlarmService;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/10 8:40
 */
@RestController
@CrossOrigin
@RequestMapping("/alarm")
@Api(tags = "报警接口")
public class AlarmController {
	@Autowired
	private AlarmService alarmService;

	@GetMapping("/select")
	@ApiOperation("根据vehicleId查询（可不传），自动根据用户权限查询")
	public ResultVO selectAlarmRecords(Integer vehicleId){
		return alarmService.selectAlarmRecords(vehicleId);
	}
}
