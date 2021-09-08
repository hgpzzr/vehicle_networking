package com.example.vehicle_networking.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 9:31
 */
@Data
public class HistoricalPositionFrom {

    @NotNull
    @ApiModelProperty("车辆ID")
    private Integer vehicleId;

    @ApiModelProperty("开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginDate;

    @ApiModelProperty("结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endDate;
}
