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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 13:14
 **/
@RestController
@RequestMapping("/maintenanceRecord")
@CrossOrigin
@Api(tags = "汽车维修记录")
@Slf4j
public class MaintenanceRecordController {

    @Autowired
    private MaintenanceRecordService maintenanceRecordService;



    @PostMapping("/addRecord")
    @ApiOperation("添加车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO addMaintenanceRecord(@Valid MaintenanceRecordForm maintenanceRecordForm){
        return maintenanceRecordService.addRecord(maintenanceRecordForm);
    }



    @GetMapping("/deleteRecord")
    @ApiOperation("删除车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO deleteMaintenanceRecord(@RequestParam("maintenanceId") Integer maintenanceId){
        return maintenanceRecordService.deleteRecord(maintenanceId);
    }



    @PostMapping("/updateRecord")
    @ApiOperation("编辑修改车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO updateMaintenanceRecord(@Valid UpdateMaintenanceRecordForm updateMaintenanceRecordForm){
        return maintenanceRecordService.updateRecord(updateMaintenanceRecordForm);
    }




    @GetMapping("/getOneRecord")
    @ApiOperation("获取单条车辆维修记录")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO getOneRecord(@RequestParam("maintenanceId") Integer maintenanceId) {
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
    public ResultVO getMaintenanceRecordByVehicleId(@RequestParam("vehicleId") Integer vehicleId){
        return maintenanceRecordService.getRecordsByVehicleId(vehicleId);
    }



    @PostMapping("/addInfo")
    @ApiOperation("添加维修详情，具体维修部分")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO addMaintenanceInfo(@Valid MaintenanceInfoForm maintenanceInfoForm){
        return maintenanceRecordService.addInfo(maintenanceInfoForm);
    }



//    @GetMapping("/deleteInfo")
//    @ApiOperation("删除车辆维修详情")
//    @RoleControl(role = RoleEnum.USER)
//    public ResultVO deleteMaintenanceInfo(@NotNull @RequestParam("maintenanceInfoId") Integer maintenanceInfoId,BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            log.info("必填项未填！");
//            return ResultVOUtil.error(ResultEnum.BIND_ERROR);
//        }
//        return maintenanceRecordService.deleteInfo(maintenanceInfoId);
//    }


    @PostMapping("/updateInfo")
    @ApiOperation("编辑修改车辆维修详情")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO updateMaintenanceInfo(UpdateMaintenanceInfoForm updateMaintenanceInfoForm){
        return maintenanceRecordService.updateInfo(updateMaintenanceInfoForm);
    }


    @GetMapping("getInfoByMaintenanceId")
    @ApiOperation("根据车辆维修记录编号获取维修详情")
    @RoleControl(role = RoleEnum.USER)
    public ResultVO getMaintenanceInfoByMaintenanceId(@RequestParam("maintenanceId")  Integer maintenanceId){
        return maintenanceRecordService.getInfoByMaintenanceId(maintenanceId);
    }

    @GetMapping("export")
    @ApiOperation("导出维修记录")
    public void exportMaintenanceRecords(HttpServletResponse response,Integer vehicleId){
        maintenanceRecordService.exportMaintenanceRecords(response,vehicleId);
    }

}
