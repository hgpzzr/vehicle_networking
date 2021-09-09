package com.example.vehicle_networking.vo;

import com.example.vehicle_networking.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/3/29 19:52
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;



}

