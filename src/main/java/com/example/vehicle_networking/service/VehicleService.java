package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.AddVehicleForm;
import com.example.vehicle_networking.form.ChangeRunningState;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.form.UpdateVehicleForm;
import com.example.vehicle_networking.vo.ResultVO;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 20:58
 */
public interface VehicleService {
	/**
	 * 添加车辆
	 * @param form
	 * @return
	 */
	ResultVO addVehicle(AddVehicleForm form);

	/**
	 * 删除车辆
	 * @param vehicleId
	 * @return
	 */
	ResultVO deleteVehicle(Integer vehicleId);

	/**
	 * 更新汽车基本信息
	 * @param form
	 * @return
	 */
	ResultVO updateVehicle(UpdateVehicleForm form);

	/**
	 * 更新汽车状态，同时更新汽车的油耗和里程数
	 * @param form
	 * @return
	 */
	ResultVO updateRunningState(ChangeRunningState form);

	/**
	 * 可以根据时间范围获取油耗记录
	 * @param historicalFrom
	 * @return
	 */
	ResultVO getVehicleHisOilUsed(HistoricalPositionFrom historicalFrom);
}
