package com.ggiit.easyblog.project.system.user.service;


import com.ggiit.easyblog.project.system.user.entity.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 用户业务层接口
 *
 * @author gao
 * @date 2019.4.23
 */
@CacheConfig(cacheNames = "user")
public interface UserService {

    /**
     * 通过id查询用户
     *
     * @param id 用户编号
     * @return User 用户对象
     */
    @Cacheable(key = "#p0")
    User get(String id);


    /**
     * 查询分页数据
     *
     * @param user     用戶对象
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    Page<User> findPage(User user, Pageable pageable);

    /**
     * 更新用户信息
     *
     * @param user 用户对象
     * @return User 更新后的用户对象
     */
    User update(User user);

    /**
     * 新增用户信息
     *
     * @param user 用户对象
     * @return User 新增后的用户
     */
    User insert(User user);

    /**
     * 根据id删除用户
     *
     * @param id 用户编号
     * @return 受影响行数
     */
    Long deleteUserById(String id);


    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    Long deleteUsersByIdIn(String ids);

    /**
     * 根据用户对象查询用户权限
     *
     * @param user 用户对象
     * @return 权限集合
     */
    Collection<GrantedAuthority> findAuthorities(User user);

}
