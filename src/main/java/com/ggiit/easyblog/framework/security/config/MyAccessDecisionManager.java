package com.ggiit.easyblog.framework.security.config;

import io.jsonwebtoken.lang.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 权限决断器
 * 判断用户拥有的权限或角色是否有资源访问权限
 *
 * @author gao
 * @date 2019.5.17
 */
@Component
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * 权限决断
     *
     * @param authentication 用户对象
     * @param o
     * @param collection     资源所需权限集合
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //如果权限集合为空则无权限访问
        if (Collections.isEmpty(collection)) {
            return;
        }
        //如果用户权限中有权限集合中的权限，则放行
        Iterator<ConfigAttribute> iterator = collection.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            //需要的权限
            String needAuth = ca.getAttribute();
            //遍历用户权限
            for (GrantedAuthority auth : authentication.getAuthorities()) {
                //如果拥有所需权限，放行
                if (needAuth.equals(auth.getAuthority())) {
                    return;
                }
            }
        }
        //无则无权访问
        throw new AccessDeniedException("抱歉，您没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
