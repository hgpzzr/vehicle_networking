package com.example.vehicle_networking.service.impl;

import com.example.vehicle_networking.entity.AccidentRecord;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.form.AccidentRecordForm;
import com.example.vehicle_networking.form.UpdateAccidentRecordForm;
import com.example.vehicle_networking.mapper.AccidentRecordMapper;
import com.example.vehicle_networking.mapper.MaintenanceRecordMapper;
import com.example.vehicle_networking.service.AccidentRecordService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.AccidentRecordVo;
import com.example.vehicle_networking.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public ResultVO addRecord(AccidentRecordForm accidentRecordForm) {
        AccidentRecord accidentRecord = new AccidentRecord();
        BeanUtils.copyProperties(accidentRecordForm,accidentRecord);
        accidentRecord.setCreateTime(getCurrentTime());
        int result = accidentRecordMapper.insert(accidentRecord);
        if(result == 0){
            return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_ADD_ERROR);
        }
        return ResultVOUtil.success();
    }


    @Override
    public ResultVO deleteRecord(Integer accidentId) {
        int result = accidentRecordMapper.deleteByPrimaryKey(accidentId);
        if(result == 0){
            ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_DELETE_ERROR);
        }
        return ResultVOUtil.success();
    }


    @Override
    public ResultVO updateRecord(UpdateAccidentRecordForm updateAccidentRecordForm) {
        AccidentRecord accidentRecord = accidentRecordMapper.selectByPrimaryKey(updateAccidentRecordForm.getAccidentId());
        BeanUtils.copyProperties(updateAccidentRecordForm,accidentRecord);
        accidentRecord.setCreateTime(getCurrentTime());
        int result = accidentRecordMapper.updateByPrimaryKey(accidentRecord);
        if(result == 0){
            return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_UPDATE_ERROR);
        }
        return ResultVOUtil.success();
    }


    @Override
    public ResultVO getOneRecord(Integer accidentId) {
        AccidentRecord accidentRecord = accidentRecordMapper.selectByPrimaryKey(accidentId);
        AccidentRecordVo recordVo = new AccidentRecordVo();
        BeanUtils.copyProperties(accidentRecord,recordVo);
        recordVo.setCreateTime(dateFormat(accidentRecord.getCreateTime()));
        if(accidentRecord == null){
            return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_IS_EMPTY);
        }
        return ResultVOUtil.success(recordVo);
    }


    @Override
    public ResultVO getAllRecords() {
        List<AccidentRecord> records = accidentRecordMapper.selectAll();
        List<AccidentRecordVo> recordVoList = new ArrayList<>();
        if(!records.isEmpty()){
            for(AccidentRecord record : records){
                AccidentRecordVo vo = new AccidentRecordVo();
                BeanUtils.copyProperties(record,vo);
                vo.setCreateTime(dateFormat(record.getCreateTime()));
                recordVoList.add(vo);
            }
            return ResultVOUtil.success(recordVoList);
        }
        return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_IS_EMPTY);
    }


    @Override
    public ResultVO getRecordsByVehicleId(Integer vehicleId) {
        List<AccidentRecord> accidentRecords = accidentRecordMapper.selectAll();
        if(accidentRecords.isEmpty()){
            return ResultVOUtil.error(ResultEnum.ACCIDENT_RECORD_IS_EMPTY);
        }
        List<AccidentRecordVo> voList = new ArrayList<>();
        for(AccidentRecord record : accidentRecords){
            if(record.getVehicleId().equals(vehicleId)){
                AccidentRecordVo vo = new AccidentRecordVo();
                BeanUtils.copyProperties(record,vo);
                vo.setCreateTime(dateFormat(record.getCreateTime()));
                voList.add(vo);
            }
        }
        return ResultVOUtil.success(voList);
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
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String time = sdf.format(date);
        return time;
    }
}
