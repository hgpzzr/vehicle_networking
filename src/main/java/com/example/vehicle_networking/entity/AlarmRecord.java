package com.example.vehicle_networking.entity;

import java.io.Serializable;
import java.util.Date;

public class AlarmRecord implements Serializable {
    private Integer alarmId;

    private Integer vehicleId;

    private String alarmReason;

    private Date createTime;

    private Integer type;

<<<<<<< HEAD
=======
    private Double numericalValue;

>>>>>>> 78b66a4be95d69bb9e701abb2f76166ee67eeea8
    private static final long serialVersionUID = 1L;

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }
<<<<<<< HEAD

=======
    public Double getNumericalValue() {
        return numericalValue;
    }

    public void setNumericalValue(Double numericalValue) {
        this.numericalValue = numericalValue;
    }
>>>>>>> 78b66a4be95d69bb9e701abb2f76166ee67eeea8
    public String getAlarmReason() {
        return alarmReason;
    }

    public void setAlarmReason(String alarmReason) {
        this.alarmReason = alarmReason == null ? null : alarmReason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", alarmId=").append(alarmId);
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", alarmReason=").append(alarmReason);
        sb.append(", createTime=").append(createTime);
        sb.append(", type=").append(type);
<<<<<<< HEAD
=======
        sb.append(", numericalValue=").append(numericalValue);
>>>>>>> 78b66a4be95d69bb9e701abb2f76166ee67eeea8
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}