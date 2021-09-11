package com.example.vehicle_networking.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author GO FOR IT
 */
@ConfigurationProperties(prefix = "basic")
@Component
@Data
public class BaseConfig {
    private Integer collectInterval;
    private Integer perMealOilConsume;
}