package com.example.vehicle_networking.service;

import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.models.auth.In;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/10 12:54
 */
public interface VehicleCategoryService {
	/**
	 * 添加分类
	 * @param categoryName
	 * @return
	 */
	ResultVO insert(String categoryName);

	/**
	 * 删除分类
	 * @param categoryId
	 * @return
	 */
	ResultVO delete(Integer categoryId);

	/**
	 * 更新分类
	 * @param categoryId
	 * @param categoryName
	 * @return
	 */
	ResultVO update(Integer categoryId,String categoryName);

	/**
	 * 查询所有分类
	 * @return
	 */
	ResultVO selectAll();
}
