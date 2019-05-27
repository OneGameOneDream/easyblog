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
     * 存放Token的Header Key
     */
    private String header;
    /**
     * 密匙key
     */
    private String secret;
    /**
     * 自定义token 前缀字符
     */
    private String tokenHead;
    /**
     * 过期时间   单位秒
     */
    private Long expiration;

}
