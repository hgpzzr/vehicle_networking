package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 19:57
 **/
@Data
public class MaintenanceInfoForm {

    @ApiModelProperty("车辆维修记录编号")
    @NotNull
    private Integer maintenanceId;

    @ApiModelProperty("维修详情部分")
    @NotBlank
    private String maintenancePart;
}
