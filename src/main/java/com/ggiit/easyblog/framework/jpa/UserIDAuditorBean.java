package com.ggiit.easyblog.framework.jpa;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 创建人及修改人自动注入
 *
 * @author gao
 * @date 2019.4.23
 */

public class UserIDAuditorBean implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("gao");
    }
}
