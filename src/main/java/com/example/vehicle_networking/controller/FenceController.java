package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.form.AddFenceForm;
import com.example.vehicle_networking.form.UpdateFenceForm;
import com.example.vehicle_networking.service.FenceService;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 19:58
 */
@RestController
@CrossOrigin
@RequestMapping("/fence")
@Api(tags = "电子围栏接口")
public class FenceController {
	@Autowired
	private FenceService fenceService;

	@ApiOperation("添加电子围栏")
	@PostMapping("/insert")
	public ResultVO addFence(@Valid AddFenceForm addFenceForm){
		return fenceService.addFence(addFenceForm);
	}

	@ApiOperation("删除电子围栏")
	@DeleteMapping("/delete")
	public ResultVO deleteFence(Integer fenceId){
		return fenceService.deleteFence(fenceId);
	}

	@ApiOperation("修改电子围栏")
	@PutMapping("/update")
	public ResultVO updateFence(@Valid UpdateFenceForm form){
		return fenceService.updateFence(form);
	}
}
