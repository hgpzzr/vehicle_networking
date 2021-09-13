package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.MaintenanceInfoForm;
import com.example.vehicle_networking.form.MaintenanceRecordForm;
import com.example.vehicle_networking.form.UpdateMaintenanceInfoForm;
import com.example.vehicle_networking.form.UpdateMaintenanceRecordForm;
import com.example.vehicle_networking.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 13:18
 **/
public interface MaintenanceRecordService {


    /**
     * 添加车辆维修记录
     * @param maintenanceRecordForm
     * @return ResultVO
     */
    ResultVO addRecord(MaintenanceRecordForm maintenanceRecordForm);


    /**
     * 删除车辆维修记录
     * @param maintenanceId
     * @return ResultVO
     */
    ResultVO deleteRecord(Integer maintenanceId);


    /**
     * 编辑修改车辆维修记录
     * @param updateMaintenanceRecordForm
     * @return ResultVO
     */
    ResultVO updateRecord(UpdateMaintenanceRecordForm updateMaintenanceRecordForm);


    /**
     * 获取单条车辆维修记录
     * @param maintenanceId
     * @return ResultVO
     */
    ResultVO getOneRecord(Integer maintenanceId);


    /**
     * 获取所有车辆维修记录
     * @return
     */
    ResultVO getAllRecords();


    /**
     * 通过汽车编号获得维修记录
     * @param vehicleId
     * @return ResultVO
     */
    ResultVO getRecordsByVehicleId(Integer vehicleId);


    /**
     * 添加维修详情记录
     * @param maintenanceInfoForm
     * @return ResultVO
     */
    ResultVO addInfo(MaintenanceInfoForm maintenanceInfoForm);


    /**
     * 删除车辆维修详情
     * @param maintenanceInfoId
     * @return ResultVO
     */
    ResultVO deleteInfo(Integer maintenanceInfoId);


    /**
     * 编辑修改车辆维修详情
     * @param updateMaintenanceInfoForm
     * @return ResultVO
     */
    ResultVO updateInfo(UpdateMaintenanceInfoForm updateMaintenanceInfoForm);


    /**
     * 根据车辆维修记录编号获取维修详情
     * @param maintenanceId
     * @return ResultVO
     */
    ResultVO getInfoByMaintenanceId(Integer maintenanceId);

    void exportMaintenanceRecords(HttpServletResponse response,Integer vehicleId);
}
