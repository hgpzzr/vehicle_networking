package com.example.vehicle_networking.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * hgp
 */
public class MaintenanceRecord implements Serializable {
    private Integer maintenanceId;

    private Integer vehicleId;

    private String describe;

    private Double maintenanceCosts;

    private Date maintenanceBeginTime;

    private Date maintenanceEndTime;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Double getMaintenanceCosts() {
        return maintenanceCosts;
    }

    public void setMaintenanceCosts(Double maintenanceCosts) {
        this.maintenanceCosts = maintenanceCosts;
    }

    public Date getMaintenanceBeginTime() {
        return maintenanceBeginTime;
    }

    public void setMaintenanceBeginTime(Date maintenanceBeginTime) {
        this.maintenanceBeginTime = maintenanceBeginTime;
    }

    public Date getMaintenanceEndTime() {
        return maintenanceEndTime;
    }

    public void setMaintenanceEndTime(Date maintenanceEndTime) {
        this.maintenanceEndTime = maintenanceEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", maintenanceId=").append(maintenanceId);
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", describe=").append(describe);
        sb.append(", maintenanceCosts=").append(maintenanceCosts);
        sb.append(", maintenanceBeginTime=").append(maintenanceBeginTime);
        sb.append(", maintenanceEndTime=").append(maintenanceEndTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}