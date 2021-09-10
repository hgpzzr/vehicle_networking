package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.config.BaseConfig;
import com.example.vehicle_networking.entity.OilConsumptionRecord;
import com.example.vehicle_networking.entity.Position;
import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.enums.OperatingStatusEnum;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.AddVehicleForm;
import com.example.vehicle_networking.form.ChangeRunningState;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.form.UpdateVehicleForm;
import com.example.vehicle_networking.mapper.OilConsumptionRecordMapper;
import com.example.vehicle_networking.mapper.PositionMapper;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.service.VehicleService;
import com.example.vehicle_networking.thread.ReadDataThread;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.acl.LastOwnerException;
import java.util.Date;
import java.util.List;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 20:59
 */
@Service
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleMapper vehicleMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private OilConsumptionRecordMapper oilConsumptionRecordMapper;
	@Autowired
	private PositionMapper positionMapper;
	@Autowired
	private BaseConfig baseConfig;


	@Override
	public ResultVO addVehicle(AddVehicleForm form) {
		Vehicle vehicle = new Vehicle();
		BeanUtils.copyProperties(form,vehicle);
		vehicle.setCreateTime(new Date());
		vehicle.setRunningState(0);
		vehicle.setLockedState(0);
		User currentUser = userService.getCurrentUser();
		vehicle.setUserId(currentUser.getUserId());
		int insert = vehicleMapper.insert(vehicle);
		if(insert != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("添加成功");
	}

	@Override
	public ResultVO deleteVehicle(Integer vehicleId) {
		if(vehicleId == null){
			return ResultVOUtil.error(ResultEnum.PARAM_NULL_ERROR);
		}
		int delete = vehicleMapper.deleteByPrimaryKey(vehicleId);
		if(delete != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("删除成功");
	}

	@Override
	public ResultVO updateVehicle(UpdateVehicleForm form) {
		Vehicle vehicle = vehicleMapper.selectByPrimaryKey(form.getVehicleId());
		BeanUtils.copyProperties(form,vehicle);
		int insert = vehicleMapper.insert(vehicle);
		if(insert != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("更新成功");
	}

	@Override
	public ResultVO updateRunningState(ChangeRunningState form) {
		Vehicle vehicle = vehicleMapper.selectByPrimaryKey(form.getVehicleId());
		vehicle.setRunningState(form.getRunningState());
		if (form.getRunningState().equals(OperatingStatusEnum.NOT_RUNNING.getValue())
				|| form.getRunningState().equals(OperatingStatusEnum.FLAMEOUT.getValue())){
			OilConsumptionRecord latestConsumption = oilConsumptionRecordMapper.getLatestConsumption(form.getVehicleId());

			// 停止收集线程
			ReadDataThread.stopTask();
			Vehicle latestVehicle = vehicleMapper.selectByPrimaryKey(vehicle.getVehicleId());

			Double meal =  latestVehicle.getMileage() - vehicle.getMileage();
			// 每公里耗油 10
			Double oilConsume = meal * baseConfig.getPerMealOilConsume();

			latestConsumption.setOilConsumption(oilConsume);
			long l = (System.currentTimeMillis() - latestConsumption.getCreateTime().getTime())/ 1000 * 60 * 60;
			latestConsumption.setWorkTime(Double.valueOf(l));
			oilConsumptionRecordMapper.updateByPrimaryKey(latestConsumption);
		}

		// 如果是启动车辆，需要添加油耗的初始记录
		if (form.getRunningState().equals(OperatingStatusEnum.RUNNING.getValue())){
			OilConsumptionRecord oilConsumptionRecord = new OilConsumptionRecord();
			oilConsumptionRecord.setVehicleId(form.getVehicleId());
			oilConsumptionRecord.setDate(new Date());
			oilConsumptionRecord.setOilConsumption(0.0);
			oilConsumptionRecord.setWorkTime(0.0);
			oilConsumptionRecord.setCreateTime(new Date());
			oilConsumptionRecordMapper.insert(oilConsumptionRecord);
		}

		int update = vehicleMapper.updateByPrimaryKey(vehicle);
		if(update != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("更新成功");
	}

	@Override
	public ResultVO getVehicleHisOilUsed(HistoricalPositionFrom historicalFrom) {
		List<OilConsumptionRecord> historicalOilConsumptionRecord = oilConsumptionRecordMapper.getHistoricalOilConsumptionRecord(historicalFrom);
		return ResultVOUtil.success(historicalOilConsumptionRecord);
	}
}
