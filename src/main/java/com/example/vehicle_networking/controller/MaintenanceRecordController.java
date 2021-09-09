package com.example.vehicle_networking.controller;

import com.example.vehicle_networking.accessctro.RoleControl;
import com.example.vehicle_networking.enums.ResultEnum;
import com.example.vehicle_networking.enums.RoleEnum;
import com.example.vehicle_networking.form.MaintenanceInfoForm;
import com.example.vehicle_networking.form.MaintenanceRecordForm;
import com.example.vehicle_networking.form.UpdateMaintenanceRecordForm;
import com.example.vehicle_networking.form.UpdateMaintenanceInfoForm;
import com.example.vehicle_networking.service.MaintenanceRecordService;
import com.example.vehicle_networking.utils.ResultVOUtil;
import com.example.vehicle_networking.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 13:14
 **/
@RestController
@RequestMapping("/api/maintenanceRecord")
@CrossOrigin
@Api(tags = "汽车维修记录")
@Slf4j
public class MaintenanceRecordController {

    @Autowired
    private MaintenanceRecordService maintenanceRecordService;



    @PostMapping("/addRecord")
    @ApiOperation("添加车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO addMaintenanceRecord(@Valid @RequestParam MaintenanceRecordForm maintenanceRecordForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.addRecord(maintenanceRecordForm);
    }




    @PostMapping("/deleteRecord")
    @ApiOperation("删除车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO deleteMaintenanceRecord(@NotNull @RequestParam("maintenanceId") Integer maintenanceId,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.deleteRecord(maintenanceId);
    }



    @PostMapping("/updateRecord")
    @ApiOperation("编辑修改车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO updateMaintenanceRecord(@Valid @RequestParam UpdateMaintenanceRecordForm updateMaintenanceRecordForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.updateRecord(updateMaintenanceRecordForm);
    }




    @GetMapping("/getOneRecord")
    @ApiOperation("获取单条车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO getOneRecord(@NotNull @RequestParam("maintenanceId") Integer maintenanceId,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.getOneRecord(maintenanceId);
    }


    @GetMapping("/getAllRecords")
    @ApiOperation("获取所有的车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO egtAllRecord(){
        return maintenanceRecordService.getAllRecords();
    }




    @GetMapping("/getRecordByVehicleId")
    @ApiOperation("根据车辆编号获得车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO getMaintenanceRecordByVehicleId(@NotNull @RequestParam("vehicleId") Integer vehicleId,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.getRecordsByVehicleId(vehicleId);
    }



    @PostMapping("/addInfo")
    @ApiOperation("添加维修详情，具体维修部分")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO addMaintenanceInfo(@Valid @RequestParam MaintenanceInfoForm maintenanceInfoForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.addInfo(maintenanceInfoForm);
    }



    @PostMapping("/deleteInfo")
    @ApiOperation("删除车辆维修详情")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO deleteMaintenanceInfo(@NotNull @RequestParam("maintenanceInfoId") Integer maintenanceInfoId,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.deleteInfo(maintenanceInfoId);
    }


    @PostMapping("/updateInfo")
    @ApiOperation("编辑修改车辆维修详情")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO updateMaintenanceInfo(@Valid @RequestParam UpdateMaintenanceInfoForm updateMaintenanceInfoForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.updateInfo(updateMaintenanceInfoForm);
    }


    @GetMapping("getInfoByMaintenanceId")
    @ApiOperation("根据车辆维修记录编号获取维修详情")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO getMaintenanceInfoByMaintenanceId(@NotNull @RequestParam("maintenanceId") Integer maintenanceId,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("必填项未填！");
            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
        }
        return maintenanceRecordService.getInfoByMaintenanceId(maintenanceId);
    }



}
