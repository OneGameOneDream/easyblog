package com.ggiit.easyblog.project.system.login.controller;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.common.util.security.UserUtils;
import com.ggiit.easyblog.framework.jwt.JwtUtils;
import com.ggiit.easyblog.framework.jwt.entity.JwtUser;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权、根据token获取用户详细信息
 *
 * @author gao
 * @date 2019.5.10
 */
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 登录授权
     */
    @Log("获取token")
    @GetMapping(value = "login")
    public ApiResult login() {

        JwtUser user = UserUtils.getUser();
        // 生成令牌
        final String token = jwtUtils.generateAccessToken(user);
        // 返回 token
        return ApiResult.success(token);
    }

    /**
     * 解析Token
     */
    @Log("解析token")
    @GetMapping(value = "convert")
    public ApiResult convert(@RequestParam String token) {
        JwtUser user = jwtUtils.getUserFromToken(token);
        // 返回 token
        return ApiResult.success(user);
    }

}
