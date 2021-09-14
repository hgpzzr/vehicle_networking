package com.example.vehicle_networking.service.impl;

import com.alibaba.excel.EasyExcel;
import com.example.vehicle_networking.entity.MaintenanceInfo;
import com.example.vehicle_networking.entity.MaintenanceRecord;
import com.example.vehicle_networking.entity.User;
import com.example.vehicle_networking.entity.Vehicle;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.MaintenanceInfoForm;
import com.example.vehicle_networking.form.MaintenanceRecordForm;
import com.example.vehicle_networking.form.UpdateMaintenanceInfoForm;
import com.example.vehicle_networking.form.UpdateMaintenanceRecordForm;
import com.example.vehicle_networking.mapper.MaintenanceInfoMapper;
import com.example.vehicle_networking.mapper.MaintenanceRecordMapper;
import com.example.vehicle_networking.mapper.VehicleMapper;
import com.example.vehicle_networking.service.MaintenanceRecordService;
import com.example.vehicle_networking.service.UserService;
import com.example.vehicle_networking.utils.FileUtil;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 13:19
 **/
@Service
@Slf4j
public class MaintenanceRecordServiceImpl implements MaintenanceRecordService {



    @Autowired
    private MaintenanceRecordMapper maintenanceRecordMapper;

    @Autowired
    private MaintenanceInfoMapper maintenanceInfoMapper;


    @Autowired
    private UserService userService;

    @Autowired
    private VehicleMapper vehicleMapper;
    @Value("${excel.filePath}")
    private String excelFilePath;

    @Override
    public ResultVO addRecord(MaintenanceRecordForm maintenanceRecordForm) {
        MaintenanceRecord maintenanceRecord = new MaintenanceRecord();
        BeanUtils.copyProperties(maintenanceRecordForm,maintenanceRecord);
        maintenanceRecord.setCreateTime(getCurrentTime());
        maintenanceRecord.setUpdateTime(new Date(0));
        int result = maintenanceRecordMapper.insert(maintenanceRecord);
        if(result == 0){
            return ResultVOUtil.error(ResultEnum.MAINTENANCE_RECORD_ADD_ERROR);
        }
        return ResultVOUtil.success();
    }


    @Override
    public ResultVO deleteRecord(Integer maintenanceId) {
        try {
            maintenanceInfoMapper.deleteByMaintenanceRecordId(maintenanceId);
            maintenanceRecordMapper.deleteByPrimaryKey(maintenanceId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtil.error(ResultEnum.MAINTENANCE_RECORD_DELETE_ERROR);
        }
        return ResultVOUtil.success();
    }


    @Override
    public ResultVO updateRecord(UpdateMaintenanceRecordForm updateMaintenanceRecordForm) {
        MaintenanceRecord maintenanceRecord = maintenanceRecordMapper.selectByPrimaryKey(updateMaintenanceRecordForm.getMaintenanceId());
        BeanUtils.copyProperties(updateMaintenanceRecordForm,maintenanceRecord);
        maintenanceRecord.setUpdateTime(getCurrentTime());
        int result = maintenanceRecordMapper.updateByPrimaryKey(maintenanceRecord);
        if(result == 0){
            return ResultVOUtil.error(ResultEnum.MAINTENANCE_RECORD_UPDATE_ERROR);
        }
        return ResultVOUtil.success();
    }


    @Override
    public ResultVO getOneRecord(Integer maintenanceId) {
        List<Integer> currentUserVehicleIds = getCurrentUserVehicleIds();
        MaintenanceRecord maintenanceRecord = maintenanceRecordMapper.selectByPrimaryKey(maintenanceId);
        if (currentUserVehicleIds.contains(maintenanceRecord.getVehicleId())) {
            MaintenanceRecordVo maintenanceRecordVo = new MaintenanceRecordVo();
            BeanUtils.copyProperties(maintenanceRecord,maintenanceRecordVo);
            maintenanceRecordVo.setMaintenanceBeginTime(dateFormat(maintenanceRecord.getMaintenanceBeginTime()));
            maintenanceRecordVo.setMaintenanceEndTime(dateFormat(maintenanceRecord.getMaintenanceEndTime()));
            maintenanceRecordVo.setCreatTime(dateFormat(maintenanceRecord.getCreateTime()));
            return ResultVOUtil.success(maintenanceRecordVo);
        }

           return  ResultVOUtil.error(ResultEnum.MAINTENANCE_RECORD_IS_EMPTY);
    }


    @Override
    public ResultVO getAllRecords() {
        List<MaintenanceRecordVo> voList = new ArrayList<>();
        List<Integer> currentUserVehicleIds = getCurrentUserVehicleIds();
        List<MaintenanceRecord> maintenanceRecords = maintenanceRecordMapper.selectAll();
        for (MaintenanceRecord MR : maintenanceRecords){
            if (currentUserVehicleIds.contains(MR.getVehicleId())) {
                MaintenanceRecordVo vo = new MaintenanceRecordVo();
                BeanUtils.copyProperties(MR,vo);
                vo.setMaintenanceBeginTime(dateFormat(MR.getMaintenanceBeginTime()));
                vo.setMaintenanceEndTime(dateFormat(MR.getMaintenanceEndTime()));
                vo.setCreatTime(dateFormat(MR.getCreateTime()));
                voList.add(vo);
            }
        }
        if(!voList.isEmpty()){
            return ResultVOUtil.success(voList);
        }
        return ResultVOUtil.error(ResultEnum.MAINTENANCE_RECORD_IS_EMPTY);
    }


    @Override
    public ResultVO getRecordsByVehicleId(Integer vehicleId) {
        List<Integer> currentUserVehicleIds = getCurrentUserVehicleIds();
        List<MaintenanceRecordVo> voList = new ArrayList<>();
        List<MaintenanceRecord> maintenanceRecords = maintenanceRecordMapper.selectAll();
        if (currentUserVehicleIds.contains(vehicleId)) {
            for (MaintenanceRecord record : maintenanceRecords){
                if(record.getVehicleId().equals(vehicleId)){
                    MaintenanceRecordVo vo = new MaintenanceRecordVo();
                    BeanUtils.copyProperties(record,vo);
                    vo.setCreatTime(dateFormat(record.getCreateTime()));
                    voList.add(vo);
                }
            }
        }
        if(!voList.isEmpty()){
            return ResultVOUtil.success(voList);
        }
        return ResultVOUtil.error(ResultEnum.MAINTENANCE_RECORD_IS_EMPTY);
    }


    @Override
    public ResultVO addInfo(MaintenanceInfoForm maintenanceInfoForm) {
        MaintenanceInfo maintenanceInfo = new MaintenanceInfo();
        BeanUtils.copyProperties(maintenanceInfoForm,maintenanceInfo);
        int result = maintenanceInfoMapper.insert(maintenanceInfo);
        if(result == 0){
            return ResultVOUtil.error(ResultEnum.MAINTENANCE_INFO_ADD_ERROR);
        }
        return ResultVOUtil.success();
    }


    @Override
    public ResultVO deleteInfo(Integer maintenanceInfoId) {
        int result = maintenanceInfoMapper.deleteByPrimaryKey(maintenanceInfoId);
        if(result == 0){
            return ResultVOUtil.error(ResultEnum.MAINTENANCE_INFO_DELETE_ERROR);
        }
        return ResultVOUtil.success();
    }


    @Override
    public ResultVO updateInfo(UpdateMaintenanceInfoForm updateMaintenanceInfoForm) {
        MaintenanceInfo maintenanceInfo = new MaintenanceInfo();
        List<MaintenanceInfo> maintenanceInfos = maintenanceInfoMapper.selectAll();
        if(maintenanceInfos.isEmpty()){
            return ResultVOUtil.error(ResultEnum.MAINTENANCE_INFO_IS_EMPTY);
        }
        for(MaintenanceInfo info : maintenanceInfos){
            if(info.getMaintenanceId().equals(updateMaintenanceInfoForm.getMaintenanceId())){
                BeanUtils.copyProperties(updateMaintenanceInfoForm,info);
                int result = maintenanceInfoMapper.updateByPrimaryKey(info);
                if(result != 0) {
                    return ResultVOUtil.success();
                }
            }
        }
        return ResultVOUtil.error(ResultEnum.MAINTENANCE_INFo_UPDATE_ERROR);
    }


    @Override
    public ResultVO getInfoByMaintenanceId(Integer maintenanceId) {
        List<MaintenanceInfo> maintenanceInfos = maintenanceInfoMapper.selectByMaintenanceId(maintenanceId);
        if(maintenanceInfos.isEmpty()){
            return ResultVOUtil.error(ResultEnum.MAINTENANCE_INFO_IS_EMPTY);
        }
        List<MaintenanceInfoVo> maintenanceInfoVoList = new ArrayList<>();
        for(MaintenanceInfo info : maintenanceInfos){
            MaintenanceInfoVo infoVo = new MaintenanceInfoVo();
            BeanUtils.copyProperties(info,infoVo);
            maintenanceInfoVoList.add(infoVo);
        }
        return ResultVOUtil.success(maintenanceInfoVoList);
    }

    @Override
    public void exportMaintenanceRecords(HttpServletResponse response, Integer vehicleId) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        String filepath = excelFilePath + format + " 维修记录记录" + ".xlsx";
        List<MaintenanceExcelVO> maintenanceExcelVOList = new ArrayList<>();
        List<MaintenanceRecord> maintenanceRecordList = maintenanceRecordMapper.selectByVehicleId(vehicleId);
        for (MaintenanceRecord maintenanceRecord:maintenanceRecordList){
            MaintenanceExcelVO maintenanceExcelVO = new MaintenanceExcelVO();
            BeanUtils.copyProperties(maintenanceRecord,maintenanceExcelVO);
            maintenanceExcelVO.setCreateTime(simpleDateFormat.format(maintenanceRecord.getCreateTime()));
            maintenanceExcelVO.setMaintenanceBeginTime(simpleDateFormat.format(maintenanceExcelVO.getMaintenanceBeginTime()));
            maintenanceExcelVO.setMaintenanceEndTime(simpleDateFormat.format(maintenanceExcelVO.getMaintenanceEndTime()));
            maintenanceExcelVO.setLicenseNumber(vehicleMapper.selectByPrimaryKey(maintenanceRecord.getVehicleId()).getLicensePlateNumber());
            List<MaintenanceInfo> maintenanceInfoList = maintenanceInfoMapper.selectByMaintenanceId(maintenanceRecord.getMaintenanceId());
            StringBuilder maintenancePart = new StringBuilder();
            for (MaintenanceInfo maintenanceInfo:maintenanceInfoList){
                maintenancePart.append(maintenanceInfo.getMaintenancePart()+";");
            }
            maintenanceExcelVO.setMaintenancePart(maintenancePart.toString());
            maintenanceExcelVOList.add(maintenanceExcelVO);
        }
        EasyExcel.write(filepath, MaintenanceExcelVO.class).sheet("维修记录").doWrite(maintenanceExcelVOList);
        FileUtil.downloadFile(response,filepath);
    }

    private Date getCurrentTime()  {
        Date date = new Date();
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String time = sdf.format(date);
        Date nowTime = null;
        try {
            nowTime = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return nowTime;
    }


    private String dateFormat(Date date){
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
        String time = sdf.format(date);
        return time;
    }


    private List<Integer> getCurrentUserVehicleIds(){
        User user = userService.getCurrentUser();
        List<Vehicle> vehicles = vehicleMapper.selectAll();
        List<Integer> currentUserVehicleIds = new ArrayList<>();
        for(Vehicle vehicle : vehicles){
            if(vehicle.getUserId().equals(user.getUserId())){
                currentUserVehicleIds.add(vehicle.getVehicleId());
            }
        }
        return currentUserVehicleIds;
    }
}
