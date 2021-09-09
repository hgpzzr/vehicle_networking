package com.example.vehicle_networking.entity;

import java.io.Serializable;

public class MaintenanceInfo implements Serializable {
    private Integer maintenanceInfoId;

    private Integer maintenanceId;

    private String maintenancePart;

    private static final long serialVersionUID = 1L;

    public Integer getMaintenanceInfoId() {
        return maintenanceInfoId;
    }

    public void setMaintenanceInfoId(Integer maintenanceInfoId) {
        this.maintenanceInfoId = maintenanceInfoId;
    }

    public Integer getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(Integer maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getMaintenancePart() {
        return maintenancePart;
    }

    public void setMaintenancePart(String maintenancePart) {
        this.maintenancePart = maintenancePart == null ? null : maintenancePart.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", maintenanceInfoId=").append(maintenanceInfoId);
        sb.append(", maintenanceId=").append(maintenanceId);
        sb.append(", maintenancePart=").append(maintenancePart);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}