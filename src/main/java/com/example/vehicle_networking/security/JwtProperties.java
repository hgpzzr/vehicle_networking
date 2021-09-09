package com.example.vehicle_networking.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hgp
 * @version 1.0
 * @date 2021/3/29 19:49
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;

    private String expiration;

    private String tokenStart;

    private String tokenName;

    /**
     * 添加用户默认密码
     */
    private String defaultPassword;
}