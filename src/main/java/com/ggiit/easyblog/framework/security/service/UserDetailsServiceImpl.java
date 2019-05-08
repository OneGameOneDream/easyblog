package com.ggiit.easyblog.framework.security.service;

import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 安全服务业务层
 *
 * @author gao
 * @date 2019.5.8
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

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
//    @Log("用户登陆验证")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查找用户
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("没有该用户 '%s'.", username));
        } else {
            //这里返回上面继承了 UserDetails  接口的用户类
            return user;
        }
    }
}
