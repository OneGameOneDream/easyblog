package com.ggiit.easyblog.project.system.menu.repository;


import com.ggiit.easyblog.project.system.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 菜单持久层
 *
 * @author gao
 * @date 2019.5.9
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
    /**
     * 根据菜单名查询菜单
     *
     * @param menuName 菜单名
     * @return Menu 菜单对象
     */
    Menu findByName(String menuName);
}
