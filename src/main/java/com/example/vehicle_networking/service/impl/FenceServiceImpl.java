package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.ElectronicFence;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.AddFenceForm;
import com.example.vehicle_networking.mapper.ElectronicFenceMapper;
import com.example.vehicle_networking.service.FenceService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 19:15
 */
@Service
public class FenceServiceImpl implements FenceService {

	@Autowired
	private ElectronicFenceMapper electronicFenceMapper;

	@Override
	public ResultVO addFence(AddFenceForm addFenceForm) {
		ElectronicFence electronicFence1 = electronicFenceMapper.selectByConstructionId(addFenceForm.getConstructionSiteId());
		if(electronicFence1 != null){
			return ResultVOUtil.error(ResultEnum.ELECTRONIC_FENCE_EXIT_ERROR);
		}
		ElectronicFence electronicFence = new ElectronicFence();
		BeanUtils.copyProperties(addFenceForm,electronicFence);
		electronicFence.setCreateTime(new Date());
		electronicFence.setUpdateTime(new Date());
		int insert = electronicFenceMapper.insert(electronicFence);
		if(insert != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("添加成功");
	}

	@Override
	public ResultVO deleteFence(Integer fenceId) {
		int delete = electronicFenceMapper.deleteByPrimaryKey(fenceId);
		if(delete != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("删除成功");
	}
}
