package com.example.vehicle_networking.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/10 20:27
 */
@Data
public class ConstructionVO {
	private Integer constructionSiteId;

	private Integer userId;

	private Double longitude;

	private Double latitude;

	private String constructionSiteName;

	private String createTime;

	private String updateTime;
}
