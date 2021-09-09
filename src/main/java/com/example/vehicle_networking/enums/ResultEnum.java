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






    AUTHENTICATION_ERROR(401, "用户认证失败,请重新登录"),
    PERMISSION_DENNY(403, "权限不足"),
    NOT_FOUND(404, "url错误,请求路径未找到"),
    SERVER_ERROR(500, "服务器未知错误:%s"),
    BIND_ERROR(511, "参数校验错误:%s"),
    REQUEST_METHOD_ERROR(550, "不支持%s的请求方式"),



    ACCIDENT_RECORD_IS_EMPTY(3000,"车辆事故记录为空"),
    ACCIDENT_RECORD_ADD_ERROR(3001,"车辆事故记录添加失败"),
    ACCIDENT_RECORD_DELETE_ERROR(3002,"车辆事故记录删除失败"),
    ACCIDENT_RECORD_UPDATE_ERROR(3003,"车辆事故记录编辑失败"),


    MAINTENANCE_RECORD_IS_EMPTY(3100,"车辆维修记录为空"),
    MAINTENANCE_RECORD_ADD_ERROR(3101,"车辆维修记录添加失败"),
    MAINTENANCE_RECORD_DELETE_ERROR(3102,"车辆维修记录删除失败"),
    MAINTENANCE_RECORD_UPDATE_ERROR(3103,"车辆维修记录编辑失败"),


    MAINTENANCE_INFO_IS_EMPTY(3200,"车辆维修详情为空"),
    MAINTENANCE_INFO_ADD_ERROR(3201,"车辆维修详情添加失败"),
    MAINTENANCE_INFO_DELETE_ERROR(3202,"车辆维修详情删除失败"),
    MAINTENANCE_INFo_UPDATE_ERROR(3203,"车辆维修详情编辑失败"),
    ;




    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
