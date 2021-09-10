package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.service.VehicleCategoryService;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/10 13:03
 */
@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/category")
@Api(tags = "车辆分类接口")
public class VehicleCategoryController {
	@Autowired
	private VehicleCategoryService vehicleCategoryService;

	@PostMapping("/insert")
	@ApiOperation("添加分类")
	public ResultVO insert(String categoryName){
		return vehicleCategoryService.insert(categoryName);
	}
}
