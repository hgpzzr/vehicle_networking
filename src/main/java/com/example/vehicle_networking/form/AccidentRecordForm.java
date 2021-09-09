package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-07 19:46
 **/
@Data
public class AccidentRecordForm {

    @ApiModelProperty("汽车编号")
    @NotNull
    private Integer vehicleId;

    @ApiModelProperty("事故原因")
    @NotNull
    private String accidentReason;

    @ApiModelProperty("发生地点")
    @NotNull
    private String occurrenceSite;

    @ApiModelProperty("损坏情况")
    @NotNull
    private String damageCondition;


}
