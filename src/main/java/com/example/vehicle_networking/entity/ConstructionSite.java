package com.example.vehicle_networking.entity;

import java.io.Serializable;
import java.util.Date;

public class ConstructionSite implements Serializable {
    private Integer constructionSiteId;

    private Integer userId;

    private String longitude;

    private String latitude;

    private String constructionSiteName;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getConstructionSiteId() {
        return constructionSiteId;
    }

    public void setConstructionSiteId(Integer constructionSiteId) {
        this.constructionSiteId = constructionSiteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getConstructionSiteName() {
        return constructionSiteName;
    }

    public void setConstructionSiteName(String constructionSiteName) {
        this.constructionSiteName = constructionSiteName == null ? null : constructionSiteName.trim();
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
        sb.append(", constructionSiteId=").append(constructionSiteId);
        sb.append(", userId=").append(userId);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", constructionSiteName=").append(constructionSiteName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}