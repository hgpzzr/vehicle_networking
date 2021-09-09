package com.example.vehicle_networking.enums;

import lombok.Getter;

import java.util.HashMap;

/**
 * @author hgp
 * @version 1.012312321321312
 * @date 2021/3/29 19:45
 */
@Getter
public enum RoleEnum {
    /**
     *
     */
    USER(0, "普通用户"),
    ADMIN(1, "普通管理员"),
    SUPPER_ADMIN(2, "系统管理员");


    private Integer value;
    private String role;

    RoleEnum(Integer value, String role) {
        this.value = value;
        this.role = role;
    }

    public static String getRole(Integer integer) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(SUPPER_ADMIN.getValue(), SUPPER_ADMIN.getRole());
        hashMap.put(ADMIN.getValue(), ADMIN.getRole());
        hashMap.put(USER.getValue(), USER.getRole());
        return hashMap.get(integer);
    }

    public static Integer getValue(String role) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(SUPPER_ADMIN.getRole(), SUPPER_ADMIN.getValue());
        hashMap.put(ADMIN.getRole(), ADMIN.getValue());
        hashMap.put(USER.getRole(), USER.getValue());
        return hashMap.get(role);
    }
}

