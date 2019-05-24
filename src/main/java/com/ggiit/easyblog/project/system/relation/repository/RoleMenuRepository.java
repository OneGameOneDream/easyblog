package com.ggiit.easyblog.project.system.relation.repository;

import com.ggiit.easyblog.project.system.relation.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 角色菜单持久层
 *
 * @author gao
 * @date 2019.5.24
 */
public interface RoleMenuRepository extends JpaRepository<RoleMenu, String> {


    /**
     * 通过角色id查询菜单id
     *
     * @param roleId 角色id
     * @return 角色菜单实体
     */
    Optional<List<RoleMenu>> findByRoleId(String roleId);

    /**
     * 通过菜单id查询角色id
     *
     * @param menuId 菜单id
     * @return 角色菜单实体
     */
    Optional<List<RoleMenu>> findByMenuId(String menuId);

}
