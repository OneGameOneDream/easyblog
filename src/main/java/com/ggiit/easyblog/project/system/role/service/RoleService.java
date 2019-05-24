package com.ggiit.easyblog.project.system.role.service;

import com.ggiit.easyblog.project.system.role.entity.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 角色业务层接口
 *
 * @author gao
 * @date 2019.5.9
 */
@CacheConfig(cacheNames = "role")
public interface RoleService {
    /**
     * 通过id查询角色
     *
     * @param id 角色编号
     * @return Role 角色对象
     */
    @Cacheable(key = "'role_'+#p0")
    Role get(String id);

    /**
     * 查询所有角色
     *
     * @return 角色集合
     */
    @Cacheable(key = "'roles'")
    List<Role> findList();

    /**
     * 查询分页数据
     *
     * @param role     角色对象
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object findPage(Role role, Pageable pageable);

    /**
     * 更新角色信息
     *
     * @param role 角色对象
     * @return Role 更新后的角色对象
     */
    @CacheEvict(allEntries = true)
    Role update(Role role);

    /**
     * 新增角色信息
     *
     * @param role 角色对象
     * @return Role 新增后的角色
     */
    @CacheEvict(allEntries = true)
    Role insert(Role role);

    /**
     * 根据id删除角色
     *
     * @param id 角色编号
     * @return 受影响行数
     */
    @CacheEvict(allEntries = true)
    Long deleteRoleById(String id);


    /**
     * 批量删除角色
     *
     * @param ids 多个角色编号（用_隔开）
     * @return 受影响行数
     */
    @CacheEvict(allEntries = true)
    Long deleteRolesByIdIn(String ids);

    /**
     * 根据角色名查询角色
     *
     * @param roleName 角色名
     * @return role 角色对象
     */
    @Cacheable(key = "'findByName_'+#p0")
    Role findByName(String roleName);

    /**
     * 根据用户id查询其拥有角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    @Cacheable(key = "'findRolesByUserId_'+#p0")
    List<Role> findRolesByUserId(String userId);

}
