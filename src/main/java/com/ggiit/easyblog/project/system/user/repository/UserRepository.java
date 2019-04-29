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
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    Long deleteUsersById(String id);

    /**
     * 批量删除
     *
     * @param ids 用户id集合
     * @return
     */
    Long deleteUsersByIdIn(List<String> ids);
}
