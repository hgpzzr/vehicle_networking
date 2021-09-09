package com.example.vehicle_networking.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 20:55
 **/
@Data
public class MaintenanceInfoVo {

    @ApiModelProperty("主键key")
    private Integer maintenanceInfoId;

    @ApiModelProperty("车辆维修记录编号")
    private Integer maintenanceId;

    @ApiModelProperty("车辆维修详情")
    private String maintenancePart;
}
