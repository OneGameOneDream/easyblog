package com.ggiit.easyblog.project.system.menu.service;


import com.ggiit.easyblog.project.system.menu.entity.Menu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 菜单业务层接口
 *
 * @author gao
 * @date 2019.5.9
 */
@CacheConfig(cacheNames = "menu")
public interface MenuService {
    /**
     * 通过id查询菜单
     *
     * @param id 菜单编号
     * @return Menu 菜单对象
     */
    @Cacheable(key = "'menu_'+#p0")
    Menu get(String id);

    /**
     * 查询所有菜单
     *
     * @return 菜单集合
     */
    @Cacheable(key = "'menus'")
    List<Menu> findList();


    /**
     * 查询分页数据
     *
     * @param menu     菜单对象
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    @Cacheable(keyGenerator = "keyGenerator")
    Object findPage(Menu menu, Pageable pageable);

    /**
     * 更新菜单信息
     *
     * @param menu 菜单对象
     * @return Menu 更新后的菜单对象
     */
    @CacheEvict(allEntries = true)
    Menu update(Menu menu);

    /**
     * 新增菜单信息
     *
     * @param menu 菜单对象
     * @return Menu 新增后的菜单
     */
    @CacheEvict(allEntries = true)
    Menu insert(Menu menu);

    /**
     * 根据菜单名查询菜单
     *
     * @param menuName 菜单名
     * @return Menu 菜单对象
     */
    @Cacheable(key = "'findByName_'+#p0")
    Menu findByName(String menuName);

    /**
     * 根据角色id获取菜单集合
     *
     * @param roleId 角色id
     * @return 菜单集合
     */
    @Cacheable(key = "'findMenusByRoleId_'+#p0")
    List<Menu> findMenusByRoleId(String roleId);

    /**
     * 根据用户id获取菜单集合
     *
     * @param userId 用户id
     * @return 菜单集合
     */
    @Cacheable(key = "'findMenusByUserId_'+#p0")
    List<Menu> findMenusByUserId(String userId);
}
