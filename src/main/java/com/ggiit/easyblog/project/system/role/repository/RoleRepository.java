package com.ggiit.easyblog.project.system.role.repository;

import com.ggiit.easyblog.project.system.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色持久层
 *
 * @author gao
 * @date 2019.5.8
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    /**
     * 根据角色名查询角色
     *
     * @param roleName 角色名
     * @return role 角色对象
     */
    Role findByName(String roleName);


    /**
     * 删除角色
     *
     * @param id 角色id
     * @return 受影响行数
     */
    Long deleteRoleById(String id);

    /**
     * 批量删除
     *
     * @param ids 角色id集合
     * @return 受影响行数
     */
    Long deleteRolesByIdIn(List<String> ids);
}
