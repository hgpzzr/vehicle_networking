package com.example.vehicle_networking.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
}
