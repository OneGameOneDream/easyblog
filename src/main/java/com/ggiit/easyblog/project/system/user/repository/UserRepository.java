package com.ggiit.easyblog.project.system.user.repository;


import com.ggiit.easyblog.project.system.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户持久层
 *
 * @author gao
 * @date 2019.4.23
 */
@Repository
public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, String> {

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return user 用户对象
     */
    User findByEmail(String email);


    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return user 用户对象
     */
    User findByUsername(String username);


    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 受影响行数
     */
    Long deleteUserById(String id);

    /**
     * 批量删除
     *
     * @param ids 用户id集合
     * @return 受影响行数
     */
    Long deleteUsersByIdIn(List<String> ids);
}
