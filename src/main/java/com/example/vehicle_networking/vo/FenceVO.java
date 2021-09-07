package com.example.vehicle_networking.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/9/7 20:45
 */
@Data
public class FenceVO {
	private Integer fenceId;

	private Integer constructionSiteId;

	private String longitude;

	private String latitude;

	private Double radius;

	private String createTime;

	private String updateTime;
}
