package com.example.vehicle_networking.entity;

import java.io.Serializable;
import java.util.Date;

public class ElectronicFence implements Serializable {
    private Integer fenceId;

    private Integer constructionSiteId;

    private String longitude;

    private String latitude;

    private Double radius;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getFenceId() {
        return fenceId;
    }

    public void setFenceId(Integer fenceId) {
        this.fenceId = fenceId;
    }

    public Integer getConstructionSiteId() {
        return constructionSiteId;
    }

    public void setConstructionSiteId(Integer constructionSiteId) {
        this.constructionSiteId = constructionSiteId;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
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
        sb.append(", fenceId=").append(fenceId);
        sb.append(", constructionSiteId=").append(constructionSiteId);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", radius=").append(radius);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}