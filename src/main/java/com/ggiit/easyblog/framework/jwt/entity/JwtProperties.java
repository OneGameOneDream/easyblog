package com.ggiit.easyblog.framework.jwt.entity;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT配置
 *
 * @author gao
 * @date 2019.5.24
 */
@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     *
     */
    private String header;
    /**
     *
     */
    private String secret;
    /**
     *
     */
    private String tokenHead;
    /**
     *
     */
    private Long expiration;
    /**
     *
     */
    private Long accessToken;
    /**
     *
     */
    private Long refreshToken;

}
