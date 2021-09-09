package com.example.vehicle_networking.enums;

import lombok.Getter;

/**
 * @author GO FOR IT
 */

@Getter
public enum OperatingStatusEnum {

    NOT_RUNNING(0, "未运行"),
    FLAMEOUT(1, "熄火"),
    RUNNING(2, "运行中");


    private Integer value;
    private String role;

    OperatingStatusEnum(Integer value, String role) {
        this.value = value;
        this.role = role;
    }
}
