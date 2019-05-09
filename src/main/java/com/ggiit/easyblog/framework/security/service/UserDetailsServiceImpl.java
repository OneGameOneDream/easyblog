package com.ggiit.easyblog.framework.security.service;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.repository.UserRepository;
import com.ggiit.easyblog.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 安全服务业务层
 *
 * @author gao
 * @date 2019.5.8
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 用户service
     */
    @Autowired
    private UserService userService;
    /**
     * 用户持久层
     */
    @Autowired
    private UserRepository userRepository;


    /**
     * 提供一种从用户名可以查到用户并返回的方法
     *
     * @param username 用户名
     * @return UserDetails对象
     * @throws UsernameNotFoundException 用户名不存在
     */
    @Log("用户登陆验证")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", username));
        } else {
            //登陆拦截，记录登陆信息
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            user.setLoginIp(request.getRemoteAddr());
            user.setLoginDate(new Date());
            userService.update(user);
            //这里返回上面继承了 UserDetails  接口的用户类
            return user;
        }
    }
}
