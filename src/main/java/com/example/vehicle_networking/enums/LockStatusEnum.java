package com.example.vehicle_networking.enums;


import lombok.Getter;

@Getter
public enum LockStatusEnum {
    UNLOCKED(0, "未锁机"),
    LOCKED(1, "锁机");


    private Integer value;
    private String role;

    LockStatusEnum(Integer value, String role) {
        this.value = value;
        this.role = role;
    }
}
