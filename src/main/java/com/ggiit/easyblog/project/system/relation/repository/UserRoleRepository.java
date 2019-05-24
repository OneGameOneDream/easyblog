package com.ggiit.easyblog.project.system.relation.repository;

import com.ggiit.easyblog.project.system.relation.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 用户角色持久层
 *
 * @author gao
 * @date 2019.5.24
 */
public interface UserRoleRepository extends JpaRepository<UserRole, String> {


    /**
     * 通过用户id查询角色id
     *
     * @param userId 用户id
     * @return 用户角色集合
     */
    Optional<List<UserRole>> findByUserId(String userId);

    /**
     * 通过角色id查询用户
     *
     * @param roleId 角色id
     * @return 用户角色集合
     */
    Optional<List<UserRole>> findByRoleId(String roleId);

}
