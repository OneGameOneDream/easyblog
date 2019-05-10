package com.ggiit.easyblog.project.system.login.controller;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.framework.jwt.JwtUtils;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Log("用户登录")
    @PostMapping(value = "login")
    public ApiResult login() {

        User user = userRepository.findByUsername("ronaldo");
        // 生成令牌
        final String token = jwtUtils.generateAccessToken(user);
        // 返回 token
        return ApiResult.success(token);
    }
}
