package com.example.vehicle_networking.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.vehicle_networking.config.BaseConfig;
import com.example.vehicle_networking.config.CollectDataThreadConfig;
import com.example.vehicle_networking.entity.OilConsumptionRecord;
import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.enums.OperatingStatusEnum;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.AddVehicleForm;
import com.example.vehicle_networking.form.ChangeLockedState;
import com.example.vehicle_networking.form.ChangeRunningState;
import com.example.vehicle_networking.form.HistoricalPositionFrom;
import com.example.vehicle_networking.form.UpdateVehicleForm;
import com.example.vehicle_networking.mapper.OilConsumptionRecordMapper;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.service.VehicleService;
import com.example.vehicle_networking.thread.ReadDataThread;
import com.example.vehicle_networking.utils.FileUtil;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.OilConsumptionVO;
import com.example.vehicle_networking.vo.ResultVO;
import com.sun.media.jfxmedia.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/8 20:59
 */
@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {
	@Autowired
	private VehicleMapper vehicleMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private OilConsumptionRecordMapper oilConsumptionRecordMapper;
	@Autowired
	private BaseConfig baseConfig;
	@Autowired
	private CollectDataThreadConfig collectDataThreadConfig;
	@Value("${excel.filePath}")
	private String excelFilePath;


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
		if(vehicle.getLockedState() == 1){
			return ResultVOUtil.error(ResultEnum.LOCKED_CAR_RUN_ERROR);
		}
		vehicle.setRunningState(form.getRunningState());

		if (form.getRunningState().equals(OperatingStatusEnum.NOT_RUNNING.getValue())
				|| form.getRunningState().equals(OperatingStatusEnum.FLAMEOUT.getValue())){
			OilConsumptionRecord latestConsumption = oilConsumptionRecordMapper.getLatestConsumption(form.getVehicleId());
			log.info(" ----->>>>>>>>>{} ", latestConsumption);


			// 停止收集线程
			Map<Integer, ReadDataThread> readDataThreadMap = collectDataThreadConfig.getReadDataThreadMap();
			ReadDataThread readDataThread = readDataThreadMap.get(vehicle.getVehicleId());
			if (readDataThread != null){
				readDataThread.setFlag(false);
			}
			Vehicle latestVehicle = vehicleMapper.selectByPrimaryKey(vehicle.getVehicleId());

			Double meal =  latestVehicle.getMileage() - latestConsumption.getOilConsumption();
			// 每公里耗油
			Double oilConsume = meal * baseConfig.getPerMealOilConsume();

			latestConsumption.setOilConsumption(oilConsume);
			double l = (System.currentTimeMillis() - latestConsumption.getCreateTime().getTime())/ ( 1000.0 * 60 * 60 );

			log.info(" 结束时间： {}",System.currentTimeMillis() );
			log.info(" 耗时 ： {}",l );
			log.info(" 初始时间 ： {}",latestConsumption.getCreateTime().getTime() );
			latestConsumption.setWorkTime(l);
			oilConsumptionRecordMapper.updateByPrimaryKey(latestConsumption);
		}

		// 如果是启动车辆，需要添加油耗的初始记录
		if (form.getRunningState().equals(OperatingStatusEnum.RUNNING.getValue())){
			OilConsumptionRecord oilConsumptionRecord = new OilConsumptionRecord();
			oilConsumptionRecord.setVehicleId(form.getVehicleId());
			oilConsumptionRecord.setDate(new Date());
			oilConsumptionRecord.setOilConsumption(vehicle.getMileage());
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
	@Override
	public ResultVO updateLockedState(ChangeLockedState form) {
		Vehicle vehicle = vehicleMapper.selectByPrimaryKey(form.getVehicleId());
		if(vehicle.getRunningState() == 2){
			return ResultVOUtil.error(ResultEnum.RUNNING_CAR_LOCK_ERROR);
		}
		vehicle.setLockedState(form.getLockedState());
		int update = vehicleMapper.updateByPrimaryKey(vehicle);
		if(update != 1){
			return ResultVOUtil.error(ResultEnum.DATABASE_OPTION_ERROR);
		}
		return ResultVOUtil.success("更新成功");
	}

	@Override
	public ResultVO selectVehicles(Integer categoryId,String licenseNumber,Integer vehicleId) {
		User currentUser = userService.getCurrentUser();
		if(currentUser.getRole() == 0){
			List<Vehicle> vehicleList = vehicleMapper.fuzzyQuery(categoryId,licenseNumber,currentUser.getUserId(),vehicleId);
			return ResultVOUtil.success(vehicleList);
		}
		else {
			return ResultVOUtil.success(vehicleMapper.fuzzyQuery(categoryId,licenseNumber,null,vehicleId));
		}
	}

	@Override
	public void oilConsumptionExport(HttpServletResponse response,Integer vehicleId) {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = simpleDateFormat.format(date);
		String filepath = excelFilePath + format + "油耗记录" + ".xlsx";
		List<OilConsumptionVO> oilConsumptionVOList = new ArrayList<>();
		List<OilConsumptionRecord> oilConsumptionRecordList = oilConsumptionRecordMapper.selectByVehicleId(vehicleId);
		for (OilConsumptionRecord oilConsumptionRecord : oilConsumptionRecordList){
			OilConsumptionVO oilConsumptionVO = new OilConsumptionVO();
			BeanUtils.copyProperties(oilConsumptionRecord,oilConsumptionVO);
			oilConsumptionVO.setDate(simpleDateFormat.format(oilConsumptionRecord.getDate()));
			oilConsumptionVO.setLicenseNumber(vehicleMapper.selectByPrimaryKey(oilConsumptionRecord.getVehicleId()).getLicensePlateNumber());
			oilConsumptionVOList.add(oilConsumptionVO);
		}
		EasyExcel.write(filepath, OilConsumptionVO.class).sheet("库存列表").doWrite(oilConsumptionVOList);
		FileUtil.downloadFile(response,filepath);
	}

}
