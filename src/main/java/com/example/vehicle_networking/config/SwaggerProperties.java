package com.example.vehicle_networking.config;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/3/9 10:07
 */

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {

    private Boolean enable;

    private String applicationName;

    private String applicationVersion;

    private String applicationDescription;

    private String tryHost;
}