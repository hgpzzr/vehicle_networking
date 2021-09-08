package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.AddConstructionForm;
import com.example.vehicle_networking.vo.ResultVO;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 21:09
 */
public interface ConstructionService {
	/**
	 * 添加工地
	 * @param form
	 * @return
	 */
	ResultVO addConstruction(AddConstructionForm form);

	/**
	 * 删除工地
	 * @param constructionId
	 * @return
	 */
	ResultVO deleteConstruction(Integer constructionId);

}
