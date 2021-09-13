package com.example.vehicle_networking.entity;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;
import java.util.Date;

public class OilConsumptionRecord implements Serializable {
    private Integer oilConsumptionId;

    private Integer vehicleId;

    private Date date;

    private Double oilConsumption;

    private Double workTime;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getOilConsumptionId() {
        return oilConsumptionId;
    }

    public void setOilConsumptionId(Integer oilConsumptionId) {
        this.oilConsumptionId = oilConsumptionId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getOilConsumption() {
        return oilConsumption;
    }

    public void setOilConsumption(Double oilConsumption) {
        this.oilConsumption = oilConsumption;
    }

    public Double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Double workTime) {
        this.workTime = workTime;
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
        sb.append(", oilConsumptionId=").append(oilConsumptionId);
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", date=").append(date);
        sb.append(", oilConsumption=").append(oilConsumption);
        sb.append(", workTime=").append(workTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}