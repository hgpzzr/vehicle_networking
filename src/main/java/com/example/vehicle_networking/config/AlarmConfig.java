package com.example.vehicle_networking.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：GO FOR IT
 * @description：
 * @date ：2021/9/10 20:56
 */
@ConfigurationProperties(prefix = "alarm.threshold")
@Component
@Data
public class AlarmConfig {
    private Integer speedMax;
    private Integer temperatureMax;
    private Integer inclinationMax;
}
