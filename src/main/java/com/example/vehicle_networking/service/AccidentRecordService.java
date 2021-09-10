package com.example.vehicle_networking.service;

import com.example.vehicle_networking.form.AccidentRecordForm;
import com.example.vehicle_networking.form.UpdateAccidentRecordForm;
import com.example.vehicle_networking.vo.ResultVO;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-07 19:31
 **/
public interface AccidentRecordService {

    /**
     * 添加车辆事故记录
     * @param accidentRecordForm
     * @return ResultVO
     */
    ResultVO addRecord(AccidentRecordForm accidentRecordForm);


    /**
     * 删除车辆回顾记录
     * @param accidentId
     * @return ResultVo
     */
    ResultVO deleteRecord(Integer accidentId);


    /**
     * 编辑修改车辆事故记录
     * @param updateAccidentRecordForm
     * @return ResultVO
     */
    ResultVO updateRecord(UpdateAccidentRecordForm updateAccidentRecordForm);


    /**
     * 获取单条车辆事故jilu
     * @param accidentId
     * @return ResultVO
     */
    ResultVO getOneRecord(Integer accidentId);


    /**
     * 获取所有车辆事故记录
     * @return ResultVO
     */
    ResultVO getAllRecords();


    /**
     * 根据汽车编号获取车辆事故记录
     * @param vehicleId
     * @return ResultVO
     */
    ResultVO getRecordsByVehicleId(Integer vehicleId);


}
