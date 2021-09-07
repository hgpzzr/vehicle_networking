package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.AddFenceForm;
import com.example.vehicle_networking.form.UpdateFenceForm;
import com.example.vehicle_networking.vo.ResultVO;
import org.apache.ibatis.annotations.Update;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 19:15
 */
public interface FenceService {
	/**
	 * 添加电子围栏
	 * @param addFenceForm
	 * @return
	 */
	ResultVO addFence(AddFenceForm addFenceForm);

	/**
	 * 删除电子围栏
	 * @param fenceId
	 * @return
	 */
	ResultVO deleteFence(Integer fenceId);

	ResultVO updateFence(UpdateFenceForm form);
}
