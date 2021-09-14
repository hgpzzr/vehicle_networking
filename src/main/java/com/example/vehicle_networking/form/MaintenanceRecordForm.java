package com.example.vehicle_networking.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 13:22
 **/
@Data
public class MaintenanceRecordForm {

    @ApiModelProperty("汽车编号")
    @NotNull
    private Integer vehicleId;

    @ApiModelProperty("主要改动描述")
    @NotBlank
    private String describe;

    @ApiModelProperty("维修费用")
    @NotNull
    private Double maintenanceCosts;

    @ApiModelProperty("开始维修时间")
    @NotNull
    private Date maintenanceBeginTime;

    @ApiModelProperty("结束维修时间")
    @NotNull
    private Date maintenanceEndTime;



}
