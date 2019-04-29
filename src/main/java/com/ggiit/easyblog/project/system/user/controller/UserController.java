package com.ggiit.easyblog.project.system.user.controller;

import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public ResponseEntity list(User user) {
        return ResponseEntity.ok(userService.findList(user));
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
    public ResponseEntity page(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, @RequestParam(value = "sort", defaultValue = "id") String sort, User user) {
        return ResponseEntity.ok(userService.findPage(user, PageRequest.of(pageNum-1, pageSize, new Sort(Sort.Direction.DESC, sort))));
    }

    /**
     * 更新或新增用户
     *
     * @param user 用户对象
     * @return 更新或新增后的用户对象
     */
    @GetMapping("save")
    @ResponseBody
    public ResponseEntity save(User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    /**
     * 删除用户
     *
     * @param id 用户编号
     * @return 受影响行数
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(userService.deleteById(id));
    }

    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    @GetMapping("deleteInBatch/{ids}")
    @ResponseBody
    public ResponseEntity deleteInBatch(@PathVariable("ids") String ids) {
        return ResponseEntity.ok(userService.deleteInBatch(ids));
    }

}
