package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.AddConstructionForm;
import com.example.vehicle_networking.vo.ResultVO;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 21:09
 */
public interface ConstructionService {
	ResultVO addConstruction(AddConstructionForm form);
}
