package com.ggiit.easyblog.project.system.user.controller;

import com.ggiit.easyblog.common.annotation.Log;
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
    @Log("用户列表数据")
    @GetMapping("list")
    @ResponseBody
    public Object list(User user) {
        return userService.findList(user);
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
    public Object page(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "sort", defaultValue = "id") String sort, User user) {
        return userService.findPage(user, PageRequest.of(pageNum - 1, pageSize, new Sort(Sort.Direction.DESC, sort)));
    }

    /**
     * 更新用户
     *
     * @param user 用户对象
     * @return 更新后的用户对象
     */
    @GetMapping("update")
    @ResponseBody
    public Object update(User user) {
        return userService.update(user);
    }

    /**
     * 新增用户
     *
     * @param user 用户对象
     * @return 新增后的用户对象
     */
    @GetMapping("insert")
    @ResponseBody
    public Object insert(User user) {
        return userService.insert(user);
    }


    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 受影响行数
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable("id") String id) {
        return userService.deleteById(id);
    }

    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    @GetMapping("deleteInBatch/{ids}")
    @ResponseBody
    public Object deleteInBatch(@PathVariable("ids") String ids) {
        return userService.deleteInBatch(ids);
    }

}
