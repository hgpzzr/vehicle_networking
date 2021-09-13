package com.example.vehicle_networking.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-08 12:29
 **/
@Data
public class AccidentRecordVo {

    @ApiModelProperty("主键Id，事故编号")
    private Integer accidentId;

    @ApiModelProperty("汽车编号")
    private Integer vehicleId;

    @ApiModelProperty("事故原因")
    private String accidentReason;

    @ApiModelProperty("发生地点")
    private String occurrenceSite;

    @ApiModelProperty("损坏情况")
    private String damageCondition;

    @ApiModelProperty("车辆事故记录创建时间")
    private String createTime;

    @ApiModelProperty("车牌号")
    private String licenseNumber;
}
