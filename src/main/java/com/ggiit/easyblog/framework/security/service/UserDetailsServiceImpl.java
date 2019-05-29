package com.ggiit.easyblog.framework.security.service;

import com.ggiit.easyblog.framework.jwt.entity.JwtUser;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
     * 提供一种从用户名可以查到用户并返回的方法
     *
     * @param username 用户名
     * @return UserDetails对象
     * @throws UsernameNotFoundException 用户名不存在
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", username));
        } else {
            //这里返回上面继承了 UserDetails  接口的用户类
            return createJwtUser(user);
        }
    }

    /**
     * 创建JwtUser对象
     *
     * @param user 用户对象
     * @return SpringSecurity安全用户
     */
    private UserDetails createJwtUser(User user) {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername(Optional.ofNullable(user.getUsername()).orElse(""));
        jwtUser.setNickname(Optional.ofNullable(user.getNickname()).orElse(""));
        jwtUser.setPassword(Optional.ofNullable(user.getPassword()).orElse(""));
        jwtUser.setAvatar(Optional.ofNullable(user.getAvatar()).orElse(""));
        jwtUser.setEmail(Optional.ofNullable(user.getEmail()).orElse(""));
        jwtUser.setState(Optional.ofNullable(user.getState()).orElse(false));
        jwtUser.setPhone(Optional.ofNullable(user.getPhone()).orElse(""));
        jwtUser.setLoginIp(Optional.ofNullable(user.getLoginIp()).orElse(""));
        jwtUser.setLoginDate(Optional.ofNullable(user.getLoginDate()).orElse(null));
        jwtUser.setAuthorities(Optional.ofNullable(userService.findAuthorities(user)).orElse(null));
        jwtUser.setId(Optional.ofNullable(user.getId()).orElse(""));
        jwtUser.setCreateBy(Optional.ofNullable(user.getCreateBy()).orElse(""));
        jwtUser.setCreateTime(Optional.ofNullable(user.getCreateTime()).orElse(null));
        jwtUser.setUpdateBy(Optional.ofNullable(user.getUpdateBy()).orElse(""));
        jwtUser.setUpdateTime(Optional.ofNullable(user.getUpdateTime()).orElse(null));
        jwtUser.setRemark(Optional.ofNullable(user.getRemark()).orElse(""));
        jwtUser.setDelFlag(Optional.ofNullable(user.getDelFlag()).orElse(false));
        return jwtUser;
    }

}
