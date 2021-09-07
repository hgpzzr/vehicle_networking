package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.AddFenceForm;
import com.example.vehicle_networking.vo.ResultVO;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 19:15
 */
public interface FenceService {
	ResultVO addFence(AddFenceForm addFenceForm);
}
