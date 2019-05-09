package com.ggiit.easyblog.project.system.menu.service;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.exception.MenuNameExistException;
import com.ggiit.easyblog.project.system.menu.entity.Menu;
import com.ggiit.easyblog.project.system.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 查询分页数据
     *
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    @Override
    public Page<Menu> findPage(Menu menu, Pageable pageable) {
        return menuRepository.findAll(pageable);
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
            throw new MenuNameExistException();
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
            throw new MenuNameExistException();
        }
        return menuRepository.save(menu);
    }
}
