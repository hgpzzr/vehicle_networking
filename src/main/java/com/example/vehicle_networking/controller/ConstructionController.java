package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.form.AddConstructionForm;
import com.example.vehicle_networking.service.ConstructionService;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 21:23
 */
@RestController
@CrossOrigin
@RequestMapping("/construction")
@Api(tags = "工地接口")
public class ConstructionController {
	@Autowired
	private ConstructionService constructionService;

	@ApiOperation("添加工地")
	@PostMapping("/insert")
	public ResultVO addConstruction(AddConstructionForm addConstructionForm){
		return constructionService.addConstruction(addConstructionForm);
	}

	@ApiOperation("删除工地")
	@DeleteMapping("/delete")
	public ResultVO deleteConstruction(Integer constructionId){
		return constructionService.deleteConstruction(constructionId);
	}
}
