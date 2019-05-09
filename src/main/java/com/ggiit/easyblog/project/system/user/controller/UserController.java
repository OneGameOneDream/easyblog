package com.ggiit.easyblog.project.system.user.controller;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 用户控制器
 *
 * @author gao
 * @date 2019.4.23
 */
@RestController
@RequestMapping("api")
public class UserController {

    /**
     * 用户业务对象
     */
    @Autowired
    private UserService userService;


    /**
     * 用户分页数据
     *
     * @param pageNum  页码
     * @param pageSize 页大小
     * @return 分页数据
     */
    @Log("查询用户数据")
    @GetMapping("users")
    @PreAuthorize("hasRole('admin')")
    public ApiResult page(User user, @RequestParam(name = "pageNum", defaultValue = "0") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        return ApiResult.success(userService.findPage(user, PageRequest.of(pageNum, pageSize)));
    }

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 更新后的用户对象
     */
    @Log("更新用户数据")
    @PutMapping("users")
    public ApiResult update(User user) {
        return ApiResult.success(userService.update(user));
    }

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 新增后的用户对象
     */
    @Log("新增用户数据")
    @PostMapping("users")
    public ApiResult insert(User user) {
        return ApiResult.success(userService.insert(user));
    }


    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 受影响行数
     */
    @Log("删除用户数据")
    @DeleteMapping("users/{id}")
    public ApiResult delete(@PathVariable("id") String id) {
        return ApiResult.success(userService.deleteUserById(id));
    }

    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    @Log("批量删除用户数据")
    @DeleteMapping("users/{ids}")
    public ApiResult deleterolesByIdIn(@PathVariable("ids") String ids) {
        return ApiResult.success(userService.deleteUsersByIdIn(ids));
    }

}
