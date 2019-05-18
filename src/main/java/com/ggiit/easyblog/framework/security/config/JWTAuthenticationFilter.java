package com.ggiit.easyblog.framework.security.config;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.exception.TokenException;
import com.ggiit.easyblog.framework.jwt.JwtUtils;
import com.ggiit.easyblog.framework.jwt.entity.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Collections;
import org.apache.tomcat.util.http.ResponseUtil;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * jwt权限过滤器
 *
 * @author gao
 * @date 2019.5.18
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    /**
     * Token工具类
     */
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Token请求头
     */
    @Value("{jwt.header}")
    private String head;


    /**
     * Token请求头前缀
     */
    @Value("{jwt.tokenHead}")
    private String tokenHead;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //获取请求头
        String header = request.getHeader(head);
        //如果请求头为空，从参数中获取token
        if (StrUtil.isBlank(header)) {
            header = request.getParameter(head);
        }
        //未符合自定义token要求标识
        Boolean notValid = StrUtil.isBlank(header) || (!header.startsWith(tokenHead));
        if (notValid) {
            chain.doFilter(request, response);
            return;
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(header, response);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.toString();
        }

        chain.doFilter(request, response);
    }

    /**
     * 验证用户名密码
     *
     * @param header   token
     * @param response 响应
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String header, HttpServletResponse response) {

        // 用户名
        String username = null;
        // 权限
        List<GrantedAuthority> authorities = new ArrayList<>();
        // JWT
        try {
            // 解析token
            JwtUser user = jwtUtils.getUserFromToken(header);
            // 获取用户名
            username = user.getUsername();
            // 获取权限
            // 未缓存 读取权限数据
            authorities.addAll(user.getAuthorities());
        } catch (ExpiredJwtException e) {
            throw e;
        } catch (Exception e) {
            throw new TokenException();
        }

        if (StrUtil.isNotBlank(username)) {
            //踩坑提醒 此处password不能为null
            User principal = new User(username, "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }
        return null;
    }


}
