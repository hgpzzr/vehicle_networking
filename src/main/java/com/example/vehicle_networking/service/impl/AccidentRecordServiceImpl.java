package com.example.vehicle_networking.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.vehicle_networking.entity.AccidentRecord;
import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.AccidentRecordForm;
import com.example.vehicle_networking.form.UpdateAccidentRecordForm;
import com.example.vehicle_networking.mapper.AccidentRecordMapper;
import com.example.vehicle_networking.mapper.MaintenanceRecordMapper;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.AccidentRecordService;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.utils.FileUtil;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.AccidentExcelVO;
import com.example.vehicle_networking.vo.AccidentRecordVo;
import com.example.vehicle_networking.vo.OilConsumptionVO;
import com.example.vehicle_networking.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-07 19:31
 **/
@Service
@Slf4j
public class AccidentRecordServiceImpl implements AccidentRecordService {

	@Autowired
	private AccidentRecordMapper accidentRecordMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private VehicleMapper vehicleMapper;
	@Value("${excel.filePath}")
	private String excelFilePath;


	@Override
	public ResultVO addRecord(AccidentRecordForm accidentRecordForm) {
		AccidentRecord accidentRecord = new AccidentRecord();
		BeanUtils.copyProperties(accidentRecordForm, accidentRecord);
		accidentRecord.setCreateTime(getCurrentTime());
		int result = accidentRecordMapper.insert(accidentRecord);
		if (result == 0) {
			return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_ADD_ERROR);
		}
		return ResultVOUtil.success();
	}


	@Override
	public ResultVO deleteRecord(Integer accidentId) {
		int result = accidentRecordMapper.deleteByPrimaryKey(accidentId);
		if (result == 0) {
			ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_DELETE_ERROR);
		}
		return ResultVOUtil.success();
	}


	@Override
	public ResultVO updateRecord(UpdateAccidentRecordForm updateAccidentRecordForm) {
		AccidentRecord accidentRecord = accidentRecordMapper.selectByPrimaryKey(updateAccidentRecordForm.getAccidentId());
		BeanUtils.copyProperties(updateAccidentRecordForm, accidentRecord);
		accidentRecord.setCreateTime(getCurrentTime());
		int result = accidentRecordMapper.updateByPrimaryKey(accidentRecord);
		if (result == 0) {
			return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_UPDATE_ERROR);
		}
		return ResultVOUtil.success();
	}


	@Override
	public ResultVO getOneRecord(Integer accidentId) {
		List<Integer> currentUserVehicleIds = getCurrentUserVehicleIds();
		AccidentRecord accidentRecord = accidentRecordMapper.selectByPrimaryKey(accidentId);
		AccidentRecordVo recordVo = null;
		if (currentUserVehicleIds.contains(accidentRecord.getVehicleId())) {
			recordVo = new AccidentRecordVo();
			BeanUtils.copyProperties(accidentRecord, recordVo);
			recordVo.setCreateTime(dateFormat(accidentRecord.getCreateTime()));
			recordVo.setLicenseNumber(vehicleMapper.selectByPrimaryKey(accidentRecord.getVehicleId()).getLicensePlateNumber());
			return ResultVOUtil.success(recordVo);
		}
		return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_IS_EMPTY);

	}


	@Override
	public ResultVO getAllRecords() {
		List<AccidentRecord> records = accidentRecordMapper.selectAll();
		List<AccidentRecordVo> recordVoList = new ArrayList<>();
		List<Integer> currentUserVehicleIds = getCurrentUserVehicleIds();
		if (!records.isEmpty()) {
			for (AccidentRecord record : records) {
				if (currentUserVehicleIds.contains(record.getVehicleId())) {
					AccidentRecordVo vo = new AccidentRecordVo();
					BeanUtils.copyProperties(record, vo);
					vo.setCreateTime(dateFormat(record.getCreateTime()));
					vo.setLicenseNumber(vehicleMapper.selectByPrimaryKey(record.getVehicleId()).getLicensePlateNumber());
					recordVoList.add(vo);
				}
			}
		}
		if (!recordVoList.isEmpty()) {
			return ResultVOUtil.success(recordVoList);
		}
		return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_IS_EMPTY);
	}


	@Override
	public ResultVO getRecordsByVehicleId(Integer vehicleId) {
		List<AccidentRecord> accidentRecords = accidentRecordMapper.selectAll();
		List<Integer> currentUserVehicleIds = getCurrentUserVehicleIds();
		List<AccidentRecordVo> voList = new ArrayList<>();
		if (currentUserVehicleIds.contains(vehicleId)) {
			for (AccidentRecord record : accidentRecords) {
				if (record.getVehicleId().equals(vehicleId)) {
					AccidentRecordVo vo = new AccidentRecordVo();
					BeanUtils.copyProperties(record, vo);
					vo.setCreateTime(dateFormat(record.getCreateTime()));
					voList.add(vo);
				}
			}
		}
		if (!voList.isEmpty()) {
			return ResultVOUtil.success(voList);
		}
		return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_IS_EMPTY);
	}

	@Override
	public void exportAccidentRecords(HttpServletResponse response, Integer vehicleId) {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String format = simpleDateFormat.format(date);
		String filepath = excelFilePath + format + " 故障记录" + ".xlsx";
		List<AccidentExcelVO> accidentExcelVOList = new ArrayList<>();
		List<AccidentRecord> accidentRecordList = accidentRecordMapper.selectByVehicleId(vehicleId);
		for (AccidentRecord accidentRecord : accidentRecordList) {
			AccidentExcelVO accidentExcelVO = new AccidentExcelVO();
			BeanUtils.copyProperties(accidentRecord,accidentExcelVO);
			accidentExcelVO.setLicenseNumber(vehicleMapper.selectByPrimaryKey(accidentRecord.getVehicleId()).getLicensePlateNumber());
			accidentExcelVOList.add(accidentExcelVO);
		}
		EasyExcel.write(filepath, AccidentExcelVO.class).sheet("故障记录").doWrite(accidentExcelVOList);
		FileUtil.downloadFile(response,filepath);
	}


	private Date getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
		String time = sdf.format(date);
		Date nowTime = null;
		try {
			nowTime = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return nowTime;
	}


	private String dateFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
		String time = sdf.format(date);
		return time;
	}


	private List<Integer> getCurrentUserVehicleIds() {
		User user = userService.getCurrentUser();
		List<Vehicle> vehicles = vehicleMapper.selectAll();
		List<Integer> currentUserVehicleIds = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getUserId().equals(user.getUserId())) {
				currentUserVehicleIds.add(vehicle.getVehicleId());
			}
		}
		return currentUserVehicleIds;
	}
}
