package com.ggiit.easyblog.project.system.user.service;


import com.ggiit.easyblog.project.system.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户业务层
 *
 * @author gao
 * @date 2019.4.23
 */
public interface UserService {

    /**
     * 通过id查询用户
     *
     * @param id 用户编号
     * @return User 用户对象
     */
    User get(String id);

    /**
     * 查询用户列表信息
     *
     * @return List 用户集合
     */
    List<User> findList(User user);
    /**
     * 查询分页数据
     *
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    Page<User> findPage(User user,Pageable pageable);

    /**
     * 保存用户信息
     *
     * @param user 用户对象
     * @return User 更新或新增后的用户对象
     */
    User save(User user);

    /**
     * 批量保存用户
     *
     * @param userList 用户集合
     * @return List 更新或新增后的用户集合
     */
    List<User> saveAll(List<User> userList);

    /**
     * 根据id删除用户
     *
     * @param id 用户编号
     * @return 受影响行数
     */
    Long deleteById(String id);


    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    Long deleteInBatch(String ids);

}
