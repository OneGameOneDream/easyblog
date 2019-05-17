package com.ggiit.easyblog.framework.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/**
 * 资源权限认证器
 *
 * @author gao
 * @date 2019.5.17
 */
@Slf4j
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     * 自定义授权策略
     * decide()方法在url请求时才会调用，服务器启动时不会执行这个方法
     *
     * @param authentication 装载了从数据库读出来的权限(角色) 数据。这里是从MyUserDetailService里的loadUserByUsername方法里的grantedAuths对象的值传过来给 authentication 对象,简单点就是从spring的全局缓存SecurityContextHolder中拿到的，里面是用户的权限信息
     * @param o
     * @param collection
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException 注意： Authentication authentication 如果是前后端分离 则有跨域问题，跨域情况下 authentication 无法获取当前登陆人的身份认证(登陆成功后)，我尝试用token来效验权限
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 无权限访问
        if (CollectionUtils.isEmpty(collection)) {
            log.info("无访问权限.");
            throw new AccessDeniedException("无访问权限.");
        }
        //权限匹配校验
        for (ConfigAttribute configAttribute : collection) {
            //需要的权限
            String needAuth = configAttribute.getAttribute();
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                //grantedAuthority 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
                //判断两个请求的url的权限和用户具有的权限是否相同，如相同，允许访问 权限就是那些以ROLE_为前缀的角色
                if (needAuth.trim().equals(grantedAuthority.getAuthority().trim())) {
                    //匹配到对应的角色，则允许通过
                    return;
                }
            }
        }
        //该url具有访问权限，但是当前登录用户没有匹配到URL对应的权限，则抛出无权限错误
        log.info("无访问权限.");
        throw new AccessDeniedException("无访问权限.");
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
