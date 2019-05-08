package com.ggiit.easyblog.project.system.user.controller;

import com.ggiit.easyblog.common.annotation.Log;
import com.ggiit.easyblog.framework.web.entity.ApiResult;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


/**
 * 用户控制器
 *
 * @author gao
 * @date 2019.4.23
 */
@RestController
@RequestMapping("api/v1/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户列表数据
     *
     * @return 用户列表数据
     */
    @Log("用户列表数据")
    @GetMapping("list")
    public ApiResult list(User user) {
        return ApiResult.success(userService.findList(user));
    }

    /**
     * 用户分页数据
     *
     * @param pageNum  页码
     * @param pageSize 页大小
     * @param sort     排序字段
     * @return 分页数据
     */
    @Log("用户分页数据")
    @GetMapping("page")
    public ApiResult page(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "sort", defaultValue = "id") String sort, User user) {
        return ApiResult.success(userService.findPage(user, PageRequest.of(pageNum - 1, pageSize, new Sort(Sort.Direction.DESC, sort))));
    }

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 更新后的用户对象
     */
    @Log("更新用户数据")
    @PutMapping("update")
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
    @PostMapping("insert")
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
    @DeleteMapping("delete/{id}")
    public ApiResult delete(@PathVariable("id") String id) {
        return ApiResult.success(userService.deleteById(id));
    }

    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    @Log("批量删除用户数据")
    @DeleteMapping("deleteInBatch/{ids}")
    public ApiResult deleteInBatch(@PathVariable("ids") String ids) {
        return ApiResult.success(userService.deleteInBatch(ids));
    }

}
