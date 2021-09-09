package com.example.vehicle_networking.entity;

import java.io.Serializable;
import java.util.Date;

public class Vehicle implements Serializable {
    private Integer vehicleId;

    private Integer userId;

    private String licensePlateNumber;

    private Double mileage;

    private Integer categoryId;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber == null ? null : licensePlateNumber.trim();
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", userId=").append(userId);
        sb.append(", licensePlateNumber=").append(licensePlateNumber);
        sb.append(", mileage=").append(mileage);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}