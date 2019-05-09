package com.ggiit.easyblog.project.system.role.controller;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import com.ggiit.easyblog.project.system.role.entity.Role;
import com.ggiit.easyblog.project.system.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 角色控制器
 *
 * @author gao
 * @date 2019.5.9
 */
@RestController
@RequestMapping("api")
public class RoleController {

    /**
     * 角色业务对象
     */
    @Autowired
    private RoleService roleService;

    /**
     * 角色分页数据
     *
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 分页数据
     */
    @Log("查询角色数据")
    @GetMapping("roles")
    public ApiResult page(Role role, @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return ApiResult.success(roleService.findPage(role, PageRequest.of(pageNum, pageSize)));
    }

    /**
     * 更新角色
     *
     * @param role 角色对象
     * @return 更新后的角色对象
     */
    @Log("更新角色数据")
    @PutMapping("roles")
    public ApiResult update(Role role) {
        return ApiResult.success(roleService.update(role));
    }

    /**
     * 新增角色
     *
     * @param role 角色对象
     * @return 新增后的角色对象
     */
    @Log("新增角色数据")
    @PostMapping("roles")
    public ApiResult insert(Role role) {
        return ApiResult.success(roleService.insert(role));
    }


    /**
     * 删除角色
     *
     * @param id 角色编号
     * @return 受影响行数
     */
    @Log("删除角色数据")
    @DeleteMapping("roles/{id}")
    public ApiResult delete(@PathVariable("id") String id) {
        return ApiResult.success(roleService.deleteRoleById(id));
    }

    /**
     * 批量删除角色
     *
     * @param ids 多个角色编号（用_隔开）
     * @return 受影响行数
     */
    @Log("批量删除角色数据")
    @DeleteMapping("roles/{ids}")
    public ApiResult deleterolesByIdIn(@PathVariable("ids") String ids) {
        return ApiResult.success(roleService.deleteRolesByIdIn(ids));
    }

}
