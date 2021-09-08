package com.example.vehicle_networking.enums;

import lombok.Getter;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/3/29 19:45
 */
@Getter
public enum  ResultEnum {
    USER_EXIST_ERROR(1,"用户已存在"),
    DATABASE_OPTION_ERROR(2,"数据库操作失败"),
    USER_NOT_EXIST_ERROR(3,"用户不存在"),
    PASSWORD_ERROR(4,"密码错误"),

    PASSWORD_LENGTH_ERROR(1000,"密码长度错误"),
    USER_NAME_LENGTH_ERROR(1001,"用户名长度错误"),
    ELECTRONIC_FENCE_EXIT_ERROR(1002,"该工地已存在电子围栏"),
    PARAM_NULL_ERROR(1003,"参数不能为空"),
    NOT_SELF_OPTION_ERROR(1004,"不是本人操作"),




    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(403, "权限不足"),
    NOT_FOUND(404, "url错误,请求路径未找到"),
    SERVER_ERROR(500, "服务器未知错误:%s"),
    BIND_ERROR(511, "参数校验错误:%s"),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式");

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
