package com.ggiit.easyblog.project.system.role.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.exception.EntityExistException;
import com.ggiit.easyblog.common.exception.EntityNotFoundException;
import com.ggiit.easyblog.common.util.page.PageUtil;
import com.ggiit.easyblog.project.system.relation.entity.UserRole;
import com.ggiit.easyblog.project.system.relation.repository.UserRoleRepository;
import com.ggiit.easyblog.project.system.role.entity.Role;
import com.ggiit.easyblog.project.system.role.repository.RoleRepository;
import com.ggiit.easyblog.project.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 角色业务层实现
 *
 * @author gao
 * @date 2019.5.9
 */
@Service
public class RoleServiceImpl implements RoleService {


    /**
     * 角色持久层对象
     */
    @Autowired
    private RoleRepository roleRepository;
    /**
     * 用户角色关系持久层对象
     */
    @Autowired
    private UserRoleRepository userRoleRepository;


    /**
     * 通过id查询角色
     *
     * @param id 角色编号
     * @return Role 角色对象
     */
    @Override
    public Role get(String id) {
        return roleRepository.findById(id).orElseThrow(()->new EntityNotFoundException("id 为 " + id + " 的角色没有找到"));
    }

    /**
     * 查询所有角色
     *
     * @return 角色集合
     */
    @Override
    public List<Role> findList() {
        return roleRepository.findAll();
    }

    /**
     * 查询分页数据
     *
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    @Override
    public Object findPage(Role role, Pageable pageable) {
        return PageUtil.toPage(roleRepository.findAll(pageable));
    }

    /**
     * 保存角色信息
     *
     * @param role 角色对象
     * @return Role 更新或新增后的角色对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role update(Role role) {
        Role r = roleRepository.getOne(role.getId());
        if (roleRepository.findByName(role.getName()) != null && !role.getId().equals(r.getId())) {
            throw new EntityExistException("角色名称 " + role.getName() + " 已经存在");
        }
        //动态更新
        //角色名
        if (!StrUtil.isBlank(role.getName())) {
            r.setName(role.getName());
        }
        //角色权限字符串标识
        if (!StrUtil.isBlank(role.getKey())) {
            r.setKey(role.getKey());
        }
        //角色排序
        if (role.getSort() != null) {
            r.setSort(role.getSort());
        }
        //角色状态
        if (role.getState() != null) {
            r.setState(role.getState());
        }
        //角色删除标识
        if (role.getDelFlag() != null) {
            r.setDelFlag(role.getDelFlag());
        }
        return roleRepository.save(r);
    }

    /**
     * 新增角色信息
     *
     * @param role 角色对象
     * @return Role 新增后的角色
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role insert(Role role) {
        if (roleRepository.findByName(role.getName()) != null) {
            throw new EntityExistException("角色名称 " + role.getName() + " 已经存在");
        }
        return roleRepository.save(role);
    }

    /**
     * 根据id删除角色
     *
     * @param id 角色编号
     * @return 受影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteRoleById(String id) {
        return roleRepository.deleteRoleById(id);
    }

    /**
     * 批量删除角色
     *
     * @param ids 多个角色编号（用_隔开）
     * @return 受影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteRolesByIdIn(String ids) {
        return roleRepository.deleteRolesByIdIn(Arrays.asList(ids.split(",")));
    }

    /**
     * @param roleName 角色名
     * @return role 角色对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    /**
     * 根据用户id查询其拥有角色集合
     *
     * @param userId 用户id
     * @return 角色集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Role> findRolesByUserId(String userId) {
        List<String> roleIds = new ArrayList<>();
        return userRoleRepository.findByUserId(userId).map(list -> {
            for (UserRole userRole : list) {
                roleIds.add(userRole.getRoleId());
            }
            return roleRepository.findByIdIn(roleIds);
        }).orElse(new ArrayList<>());
    }
}
