package com.ggiit.easyblog.project.system.role.service;

import com.ggiit.easyblog.project.system.role.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 角色业务层接口
 *
 * @author gao
 * @date 2019.5.9
 */
public interface RoleService {
    /**
     * 通过id查询角色
     *
     * @param id 角色编号
     * @return Role 角色对象
     */
    Role get(String id);


    /**
     * 查询分页数据
     *
     * @param role     角色对象
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    Page<Role> findPage(Role role, Pageable pageable);

    /**
     * 更新角色信息
     *
     * @param role 角色对象
     * @return Role 更新后的角色对象
     */
    Role update(Role role);

    /**
     * 新增角色信息
     *
     * @param role 角色对象
     * @return Role 新增后的角色
     */
    Role insert(Role role);

    /**
     * 根据id删除角色
     *
     * @param id 角色编号
     * @return 受影响行数
     */
    Long deleteRoleById(String id);


    /**
     * 批量删除角色
     *
     * @param ids 多个角色编号（用_隔开）
     * @return 受影响行数
     */
    Long deleteRolesByIdIn(String ids);

}
