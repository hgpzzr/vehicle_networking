package com.example.vehicle_networking.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 14:09
 **/
@Data
public class MaintenanceRecordVo {

    @ApiModelProperty("主键Id,维修记录编号")
    private Integer maintenanceId;

    @ApiModelProperty("车辆编号")
    private Integer vehicleId;

    @ApiModelProperty("主要改动描述")
    private String describe;

    @ApiModelProperty("维修费用")
    private Double maintenanceCosts;

    @ApiModelProperty("开始维修时间")
    private Date maintenanceBeginTime;

    @ApiModelProperty("结束维修时间")
    private Date maintenanceEndTime;

    @ApiModelProperty("车辆维修记录创建时间")
    private String creatTime;
}
