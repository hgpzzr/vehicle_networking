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

	/**
	 * 修改电子围栏
	 * @param form
	 * @return
	 */
	ResultVO updateFence(UpdateFenceForm form);

	/**
	 * 查询当前用户的电子围栏
	 * @return
	 */
	ResultVO selectByUserId();

	/**
	 * 查询所有人的电子围栏
	 * @return
	 */
	ResultVO selectAll();
}
