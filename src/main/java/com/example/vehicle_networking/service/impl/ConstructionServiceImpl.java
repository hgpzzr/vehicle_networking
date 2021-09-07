package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.ConstructionSite;
import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.AddConstructionForm;
import com.example.vehicle_networking.mapper.ConstructionSiteMapper;
import com.example.vehicle_networking.service.ConstructionService;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 21:10
 */
@Service
public class ConstructionServiceImpl implements ConstructionService {
	@Autowired
	private ConstructionSiteMapper constructionSiteMapper;
	@Autowired
	private UserService userService;

	@Override
	public ResultVO addConstruction(AddConstructionForm form) {
		User currentUser = userService.getCurrentUser();
		ConstructionSite constructionSite = new ConstructionSite();
		BeanUtils.copyProperties(form,constructionSite);
		constructionSite.setUserId(currentUser.getUserId());
		constructionSite.setCreateTime(new Date());
		constructionSite.setUpdateTime(new Date());
		int insert = constructionSiteMapper.insert(constructionSite);
		if(insert != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("添加成功");
	}
}
