package com.example.vehicle_networking.service;

import com.example.vehicle_networking.vo.ResultVO;

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
}
