package com.ggiit.easyblog;

import com.ggiit.easyblog.framework.jpa.UserIDAuditorBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * SpringBoot启动类
 *
 * @author gao
 * @date 2019.4.23
 */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-jdbc.properties")
@PropertySource("classpath:application-jpa.properties")
@PropertySource("classpath:application-redis.properties")
@PropertySource("classpath:application-security.properties")
@PropertySource("classpath:application-log.properties")
@PropertySource("classpath:application-jwt.properties")
public class EasyblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyblogApplication.class, args);
    }


    /**
     * 注冊自动审核
     *
     * @return
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new UserIDAuditorBean();
    }
}
