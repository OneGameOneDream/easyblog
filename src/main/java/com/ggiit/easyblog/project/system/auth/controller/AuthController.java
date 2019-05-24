package com.ggiit.easyblog.project.system.auth.controller;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.framework.jwt.JwtUtils;
import com.ggiit.easyblog.framework.jwt.entity.JwtUser;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * 授权、根据token获取用户详细信息
 *
 * @author gao
 * @date 2019.5.10
 */
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    /**
     * 登录授权
     */
    @Log("获取token")
    @GetMapping("login")
    public ApiResult login(@RequestParam String username, @RequestParam String password) {

        // 1 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(username, password);
        // 2 认证
        Authentication authentication = this.authenticationManager.authenticate(token);
        // 3 保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 4 加载UserDetails
        UserDetails details = this.userDetailsService.loadUserByUsername(username);
        // 5 生成自定义token
        // 返回 token
        return ApiResult.success("登录成功",jwtUtils.generateAccessToken(details));
    }

    /**
     * 解析Token
     */
    @Log("解析token")
    @GetMapping("/{token}")
    public ApiResult convert(@PathVariable String token) {
        JwtUser user = jwtUtils.getUserFromToken(token);
        // 返回 token
        return ApiResult.success(user);
    }

}
