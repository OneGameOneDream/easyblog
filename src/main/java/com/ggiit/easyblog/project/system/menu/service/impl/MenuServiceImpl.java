package com.ggiit.easyblog.project.system.menu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.exception.EntityExistException;
import com.ggiit.easyblog.common.util.page.PageUtil;
import com.ggiit.easyblog.project.system.menu.entity.Menu;
import com.ggiit.easyblog.project.system.menu.repository.MenuRepository;
import com.ggiit.easyblog.project.system.menu.service.MenuService;
import com.ggiit.easyblog.project.system.relation.entity.RoleMenu;
import com.ggiit.easyblog.project.system.relation.entity.UserRole;
import com.ggiit.easyblog.project.system.relation.repository.RoleMenuRepository;
import com.ggiit.easyblog.project.system.relation.repository.UserRoleRepository;
import com.ggiit.easyblog.project.system.role.entity.Role;
import com.ggiit.easyblog.project.system.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单业务层实现
 *
 * @author gao
 * @date 2019.5.9
 */
@Service
public class MenuServiceImpl implements MenuService {

    /**
     * 菜单持久层对象
     */
    @Autowired
    private MenuRepository menuRepository;
    /**
     * 角色持久层对象
     */
    @Autowired
    private RoleRepository roleRepository;
    /**
     * 角色菜单持久层对象
     */
    @Autowired
    private RoleMenuRepository roleMenuRepository;

    /**
     * 用户角色持久层对象
     */
    @Autowired
    private UserRoleRepository userRoleRepository;


    /**
     * 通过id查询菜单
     *
     * @param id 菜单编号
     * @return Menu 菜单对象
     */
    @Override
    public Menu get(String id) {
        return menuRepository.getOne(id);
    }

    /**
     * 查询所有菜单
     *
     * @return 菜单集合
     */
    @Override
    public List<Menu> findList() {
        return menuRepository.findAll();
    }

    /**
     * 查询分页数据
     *
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    @Override
    public Object findPage(Menu menu, Pageable pageable) {
        return PageUtil.toPage(menuRepository.findAll(pageable));
    }

    /**
     * 保存菜单信息
     *
     * @param menu 菜单对象
     * @return Menu 更新或新增后的菜单对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu update(Menu menu) {
        Menu m = menuRepository.getOne(menu.getId());
        if (menuRepository.findByName(menu.getName()) != null && !menu.getId().equals(m.getId())) {
            throw new EntityExistException("菜单名称 " + menu.getName() + " 已经存在");
        }
        //动态更新
        //菜单名
        if (!StrUtil.isBlank(menu.getName())) {
            m.setName(menu.getName());
        }
        //菜单父id
        if (!StrUtil.isBlank(menu.getParentId())) {
            m.setParentId(menu.getParentId());
        }
        //菜单显示顺序
        if (menu.getSort() != null) {
            m.setSort(menu.getSort());
        }
        //菜单请求地址
        if (!StrUtil.isBlank(menu.getUrl())) {
            m.setUrl(menu.getUrl());
        }
        //菜单类型
        if (menu.getType() != null) {
            m.setType(menu.getType());
        }
        //菜单权限标识
        if (!StrUtil.isBlank(menu.getPermission())) {
            m.setPermission(menu.getPermission());
        }
        //菜单图标
        if (!StrUtil.isBlank(menu.getIcon())) {
            m.setIcon(menu.getIcon());
        }
        //菜单状态
        if (menu.getState() != null) {
            m.setState(menu.getState());
        }
        //菜单删除标识
        if (menu.getDelFlag() != null) {
            m.setDelFlag(menu.getDelFlag());
        }
        return menuRepository.save(m);
    }

    /**
     * 新增菜单信息
     *
     * @param menu 菜单对象
     * @return Menu 新增后的菜单
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Menu insert(Menu menu) {
        if (menuRepository.findByName(menu.getName()) != null) {
            throw new EntityExistException("菜单名称 " + menu.getName() + " 已经存在");
        }
        return menuRepository.save(menu);
    }

    /**
     * 根据菜单名查询菜单
     *
     * @param menuName 菜单名
     * @return Menu 菜单对象
     */
    @Override
    public Menu findByName(String menuName) {
        return menuRepository.findByName(menuName);
    }

    /**
     * 根据角色id获取菜单集合
     *
     * @param roleId 角色id
     * @return 菜单集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> findMenusByRoleId(String roleId) {
        List<String> menuIds = new ArrayList<>();
        return roleMenuRepository.findByRoleId(roleId).map(list -> {
            for (RoleMenu roleMenu : list) {
                menuIds.add(roleMenu.getMenuId());
            }
            return menuRepository.findByIdIn(menuIds);
        }).orElse(new ArrayList<>());
    }

    /**
     * 根据用户id获取菜单集合
     *
     * @param userId 用户id
     * @return 菜单集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> findMenusByUserId(String userId) {
        List<String> roleIds = new ArrayList<>();
        List<Menu> menus = new ArrayList<>();
        List<Role> roles = userRoleRepository.findByUserId(userId).map(list -> {
            for (UserRole userRole : list) {
                roleIds.add(userRole.getRoleId());
            }
            return roleRepository.findByIdIn(roleIds);
        }).orElse(new ArrayList<>());
        for (Role role : roles) {
            menus.addAll(findMenusByRoleId(role.getId()));
        }
        //去重复
        return menus.stream().distinct().collect(Collectors.toList());
    }
}
