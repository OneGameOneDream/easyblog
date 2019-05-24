package com.ggiit.easyblog.framework.security.config;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.exception.TokenException;
import com.ggiit.easyblog.framework.jwt.JwtUtils;
import com.ggiit.easyblog.framework.jwt.entity.JwtProperties;
import com.ggiit.easyblog.framework.jwt.entity.JwtUser;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * token校验
 *
 * @author gao
 * @date 2019.5.18
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Token工具类
     */
    @Autowired
    private JwtUtils jwtUtils;
    /**
     * jwt配置
     */
    @Autowired
    private JwtProperties jwtProperties;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取请求头
        String token = request.getHeader(jwtProperties.getHeader());
        //如果请求头为空，从参数中获取token
        if (StrUtil.isBlank(token)) {
            token = request.getParameter(token);
        }
        //未符合自定义token要求标识
        Boolean notValid = StrUtil.isBlank(token) || (!token.startsWith(jwtProperties.getTokenHead()));
        if (notValid) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token.replace(jwtProperties.getTokenHead(), ""), response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            throw e;
        }
        //放行
        chain.doFilter(request, response);
    }

    /**
     * 验证用户名密码
     *
     * @param header   token
     * @param response 响应
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token, HttpServletResponse response) {

        // 用户名
        String username = null;
        // 权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        JwtUser user = new JwtUser();
        // JWT
        try {
            // 解析token
            user = jwtUtils.getUserFromToken(token);
            // 获取用户名
            username = user.getUsername();
            // 未缓存 读取权限数据
            authorities.addAll(user.getAuthorities());
        } catch (ExpiredJwtException e) {
            throw e;
        }
        if (StrUtil.isNotBlank(username)) {
            //踩坑提醒 此处password不能为null
            //User principal = new User(username, "", authorities);
            return new UsernamePasswordAuthenticationToken(user, null, authorities);
        }
        return null;
    }


}
