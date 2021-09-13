package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.accessctro.RoleControl;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.enums.RoleEnum;
import com.example.vehicle_networking.form.AccidentRecordForm;
import com.example.vehicle_networking.form.UpdateAccidentRecordForm;
import com.example.vehicle_networking.service.AccidentRecordService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-07 19:42
 **/
@RequestMapping("/accidentRecord")
@RestController
@CrossOrigin
@Slf4j
@Api(tags = "车辆故障记录")
public class AccidentRecordController {

    @Autowired
    private AccidentRecordService accidentRecordService;




    @PostMapping("/addRecord")
    @ApiOperation("添加车辆事故记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO addAccidentRecord(@Valid  AccidentRecordForm accidentRecordForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return accidentRecordService.addRecord(accidentRecordForm);
    }



    @GetMapping ("/deleteRecord")
    @ApiOperation("删除车辆事故记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO deleteAccidentRecord( @NotNull @RequestParam("accidentId") Integer accidentId, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return accidentRecordService.deleteRecord(accidentId);
    }



    @PostMapping("/updateRecord")
    @ApiOperation("编辑修改车辆事故记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO updateAccidentRecord(@Valid UpdateAccidentRecordForm updateAccidentRecordForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return accidentRecordService.updateRecord(updateAccidentRecordForm);
    }


    @GetMapping("/getOneRecord")
    @ApiOperation("获取单条车辆事故记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO getOneAccidentRecord(@RequestParam("accidentId") Integer accidentId){
        return accidentRecordService.getOneRecord(accidentId);
    }


    @GetMapping("/getAllRecords")
    @ApiOperation("获取所有车辆事故记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO getAllAccidentRecords(){
        return accidentRecordService.getAllRecords();
    }


    @GetMapping("/getRecordByVehicleId")
    @ApiOperation("根据汽车编号获取事故记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO getAccidentRecordsByVehicleId(@NotNull  Integer vehicleId,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return accidentRecordService.getRecordsByVehicleId(vehicleId);
    }

    @GetMapping("/export")
    @ApiOperation("根据汽车编号导出（可不传）")
    public void export(HttpServletResponse response,Integer vehicleId){
        accidentRecordService.exportAccidentRecords(response,vehicleId);
    }

}
