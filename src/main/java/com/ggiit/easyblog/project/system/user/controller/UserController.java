package com.ggiit.easyblog.project.system.user.controller;

import com.ggiit.easyblog.framework.web.entity.ApiResult;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 用户控制器
 *
 * @author gao
 * @date 2019.4.23
 */
@Controller
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户列表数据
     *
     * @return 用户列表数据
     */
    @GetMapping("list")
    @ResponseBody
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
    @GetMapping("page")
    @ResponseBody
    public ApiResult page(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "sort", defaultValue = "id") String sort, User user) {
        return ApiResult.success(userService.findPage(user, PageRequest.of(pageNum - 1, pageSize, new Sort(Sort.Direction.DESC, sort))));
    }

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 更新后的用户对象
     */
    @GetMapping("update")
    @ResponseBody
    public ApiResult update(User user) {
        return ApiResult.success(userService.update(user));
    }

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 新增后的用户对象
     */
    @GetMapping("insert")
    @ResponseBody
    public ApiResult insert(User user) {
        return ApiResult.success(userService.insert(user));
    }


    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 受影响行数
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public ApiResult delete(@PathVariable("id") String id) {
        return ApiResult.success(userService.deleteById(id));
    }

    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    @GetMapping("deleteInBatch/{ids}")
    @ResponseBody
    public ApiResult deleteInBatch(@PathVariable("ids") String ids) {
        return ApiResult.success(userService.deleteInBatch(ids));
    }

}
