package com.example.vehicle_networking.entity;

import java.io.Serializable;

public class RealTimeData implements Serializable {
    private Integer realTimeId;

    private Integer vehicleId;

    private Double engineSpeed;

    private Double fuelMargin;

    private Double engineTemperature;

    private Double speed;

    private Integer runningState;

    private Integer lockedState;

    private static final long serialVersionUID = 1L;

    public Integer getRealTimeId() {
        return realTimeId;
    }

    public void setRealTimeId(Integer realTimeId) {
        this.realTimeId = realTimeId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Double getEngineSpeed() {
        return engineSpeed;
    }

    public void setEngineSpeed(Double engineSpeed) {
        this.engineSpeed = engineSpeed;
    }

    public Double getFuelMargin() {
        return fuelMargin;
    }

    public void setFuelMargin(Double fuelMargin) {
        this.fuelMargin = fuelMargin;
    }

    public Double getEngineTemperature() {
        return engineTemperature;
    }

    public void setEngineTemperature(Double engineTemperature) {
        this.engineTemperature = engineTemperature;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getRunningState() {
        return runningState;
    }

    public void setRunningState(Integer runningState) {
        this.runningState = runningState;
    }

    public Integer getLockedState() {
        return lockedState;
    }

    public void setLockedState(Integer lockedState) {
        this.lockedState = lockedState;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", realTimeId=").append(realTimeId);
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", engineSpeed=").append(engineSpeed);
        sb.append(", fuelMargin=").append(fuelMargin);
        sb.append(", engineTemperature=").append(engineTemperature);
        sb.append(", speed=").append(speed);
        sb.append(", runningState=").append(runningState);
        sb.append(", lockedState=").append(lockedState);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}