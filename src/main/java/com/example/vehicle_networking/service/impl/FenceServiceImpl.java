package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.ConstructionSite;
import com.example.vehicle_networking.entity.ElectronicFence;
import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.AddFenceForm;
import com.example.vehicle_networking.form.UpdateFenceForm;
import com.example.vehicle_networking.mapper.ConstructionSiteMapper;
import com.example.vehicle_networking.mapper.ElectronicFenceMapper;
import com.example.vehicle_networking.service.FenceService;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.FenceVO;
import com.example.vehicle_networking.vo.ResultVO;
import javafx.collections.transformation.FilteredList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 19:15
 */
@Service
public class FenceServiceImpl implements FenceService {

	@Autowired
	private ElectronicFenceMapper electronicFenceMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private ConstructionSiteMapper constructionSiteMapper;

	@Override
	public ResultVO addFence(AddFenceForm addFenceForm) {
		ElectronicFence electronicFence1 = electronicFenceMapper.selectByConstructionId(addFenceForm.getConstructionSiteId());
		if (electronicFence1 != null) {
			return ResultVOUtil.error(ResultEnum.ELECTRONIC_FENCE_EXIT_ERROR);
		}
		ElectronicFence electronicFence = new ElectronicFence();
		BeanUtils.copyProperties(addFenceForm, electronicFence);
		electronicFence.setCreateTime(new Date());
		electronicFence.setUpdateTime(new Date());
		int insert = electronicFenceMapper.insert(electronicFence);
		if (insert != 1) {
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("添加成功");
	}

	@Override
	public ResultVO deleteFence(Integer fenceId) {
		if(fenceId == null){
			return ResultVOUtil.error(ResultEnum.PARAM_NULL_ERROR);
		}
		int delete = electronicFenceMapper.deleteByPrimaryKey(fenceId);
		if (delete != 1) {
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("删除成功");
	}

	@Override
	public ResultVO updateFence(UpdateFenceForm form) {
		ElectronicFence electronicFence = electronicFenceMapper.selectByPrimaryKey(form.getFenceId());
		BeanUtils.copyProperties(form, electronicFence);
		electronicFence.setUpdateTime(new Date());
		electronicFenceMapper.updateByPrimaryKey(electronicFence);
		return ResultVOUtil.success("更新成功");
	}

	@Override
	public ResultVO selectByUserId() {
		User currentUser = userService.getCurrentUser();
		List<ConstructionSite> constructionSiteList = constructionSiteMapper.selectByUserId(currentUser.getUserId());
		List<FenceVO> fenceVOList = new ArrayList<>();
		List<ElectronicFence> electronicFenceList = new ArrayList<>();
		for (ConstructionSite constructionSite : constructionSiteList) {
			ElectronicFence electronicFence = electronicFenceMapper.selectByConstructionId(constructionSite.getConstructionSiteId());
			electronicFenceList.add(electronicFence);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (ElectronicFence electronicFence : electronicFenceList) {
			FenceVO fenceVO = new FenceVO();
			BeanUtils.copyProperties(electronicFence, fenceVO);
			fenceVO.setCreateTime(simpleDateFormat.format(electronicFence.getCreateTime()));
			fenceVO.setUpdateTime(simpleDateFormat.format(electronicFence.getUpdateTime()));
			fenceVOList.add(fenceVO);
		}
		return ResultVOUtil.success(fenceVOList);
	}

	@Override
	public ResultVO selectAll() {
		List<ElectronicFence> electronicFenceList = electronicFenceMapper.selectAll();
		List<FenceVO> fenceVOList = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		for (ElectronicFence electronicFence : electronicFenceList) {
			FenceVO fenceVO = new FenceVO();
			BeanUtils.copyProperties(electronicFence,fenceVO);
			fenceVO.setCreateTime(simpleDateFormat.format(electronicFence.getCreateTime()));
			fenceVO.setUpdateTime(simpleDateFormat.format(electronicFence.getUpdateTime()));
			fenceVOList.add(fenceVO);
		}
		return ResultVOUtil.success(fenceVOList);
	}
}
