package com.example.vehicle_networking.entity;

import java.io.Serializable;
import java.util.Date;

public class AccidentRecord implements Serializable {
    private Integer accidentId;

    private Integer vehicleId;

    private String accidentReason;

    private String occurrenceSite;

    private String damageCondition;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(Integer accidentId) {
        this.accidentId = accidentId;
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getAccidentReason() {
        return accidentReason;
    }

    public void setAccidentReason(String accidentReason) {
        this.accidentReason = accidentReason == null ? null : accidentReason.trim();
    }

    public String getOccurrenceSite() {
        return occurrenceSite;
    }

    public void setOccurrenceSite(String occurrenceSite) {
        this.occurrenceSite = occurrenceSite == null ? null : occurrenceSite.trim();
    }

    public String getDamageCondition() {
        return damageCondition;
    }

    public void setDamageCondition(String damageCondition) {
        this.damageCondition = damageCondition == null ? null : damageCondition.trim();
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
        sb.append(", accidentId=").append(accidentId);
        sb.append(", vehicleId=").append(vehicleId);
        sb.append(", accidentReason=").append(accidentReason);
        sb.append(", occurrenceSite=").append(occurrenceSite);
        sb.append(", damageCondition=").append(damageCondition);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}