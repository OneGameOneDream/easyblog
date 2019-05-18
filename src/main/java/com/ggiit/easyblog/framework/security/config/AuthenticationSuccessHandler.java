package com.ggiit.easyblog.framework.security.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.framework.jwt.JwtUtils;
import com.ggiit.easyblog.framework.jwt.entity.JwtUser;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Token登录成功处理
 *
 * @author gao
 * @date 2019.5.18
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /**
     * Token工具类
     */
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Log("登录系统")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) authentication.getPrincipal()).getAuthorities();
        List<String> list = new ArrayList<>();
        for (GrantedAuthority g : authorities) {
            list.add(g.getAuthority());
        }
        // 登陆成功生成token
        String token;

        token = jwtUtils.generateAccessToken((UserDetails) authentication.getPrincipal());

        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSONUtil.toJsonStr(ApiResult.success(token)));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
}
