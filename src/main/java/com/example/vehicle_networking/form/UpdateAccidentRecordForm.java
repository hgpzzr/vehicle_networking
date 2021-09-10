package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: vehicle_networking
 * @description
 * @author: 不会编程的派大星
 * @create: 2021-09-07 20:27
 **/
@Data
public class UpdateAccidentRecordForm {

    @ApiModelProperty("主键Id，事故编号")
    @NotNull
    private Integer accidentId;

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
