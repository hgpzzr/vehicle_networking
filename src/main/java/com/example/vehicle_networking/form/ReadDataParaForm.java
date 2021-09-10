package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/8 17:01
 */
@Data
public class ReadDataParaForm {
    @ApiModelProperty("用友平台请求地址")
    private String url;

    @ApiModelProperty("请求携带的地址")
    private String cookie;

    @ApiModelProperty("车辆ID")
    @NotNull
    private Integer vehicleId;

    @ApiModelProperty("读取间隔（s）")
    private Integer interval;
}
