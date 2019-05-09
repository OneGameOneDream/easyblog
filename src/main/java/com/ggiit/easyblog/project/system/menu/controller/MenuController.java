package com.ggiit.easyblog.project.system.menu.controller;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import com.ggiit.easyblog.project.system.menu.entity.Menu;
import com.ggiit.easyblog.project.system.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单控制器
 *
 * @author gao
 * @date 2019.5.9
 */
@RestController
@RequestMapping("api")
public class MenuController {

    /**
     * 菜单业务对象
     */
    @Autowired
    private MenuService menuService;


    /**
     * 菜单分页数据
     *
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 分页数据
     */
    @Log("查询菜单数据")
    @GetMapping("menus")
    public ApiResult page(Menu menu, @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return ApiResult.success(menuService.findPage(menu, PageRequest.of(pageNum, pageSize)));
    }

    /**
     * 更新菜单
     *
     * @param menu 菜单对象
     * @return 更新后的菜单对象
     */
    @Log("更新菜单数据")
    @PutMapping("menus")
    public ApiResult update(Menu menu) {
        return ApiResult.success(menuService.update(menu));
    }

    /**
     * 新增菜单
     *
     * @param menu 菜单对象
     * @return 新增后的菜单对象
     */
    @Log("新增菜单数据")
    @PostMapping("menus")
    public ApiResult insert(Menu menu) {
        return ApiResult.success(menuService.insert(menu));
    }

}
