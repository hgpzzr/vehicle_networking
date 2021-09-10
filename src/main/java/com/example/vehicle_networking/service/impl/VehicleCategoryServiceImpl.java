package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.VehicleCategory;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.mapper.VehicleCategoryMapper;
import com.example.vehicle_networking.service.VehicleCategoryService;
import com.example.vehicle_networking.service.VehicleService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/10 12:54
 */
@Service
public class VehicleCategoryServiceImpl implements VehicleCategoryService {
	@Autowired
	private VehicleCategoryMapper vehicleCategoryMapper;

	@Override
	public ResultVO insert(String categoryName) {
		if(categoryName == null){
			return ResultVOUtil.error(ResultEnum.PARAM_NULL_ERROR);
		}
		VehicleCategory vehicleCategory = new VehicleCategory();
		vehicleCategory.setCategoryName(categoryName);
		int insert = vehicleCategoryMapper.insert(vehicleCategory);
		if(insert != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("插入成功");
	}

	@Override
	public ResultVO delete(Integer categoryId) {
		int delete = vehicleCategoryMapper.deleteByPrimaryKey(categoryId);
		if(delete != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("删除成功");
	}
}
