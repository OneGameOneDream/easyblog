package com.ggiit.easyblog.project.system.user.service;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.WebKeys;
import com.ggiit.easyblog.project.system.user.Queryer.UserQueryer;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 用戶业务层实现
 *
 * @author gao
 * @date 2019.4.23
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserQueryer userQueryer;

    /**
     * 通过id查询用户
     *
     * @param id 用户编号
     * @return User 用户对象
     */
    @Override
    public User get(String id) {
        return userRepository.getOne(id);
    }

    /**
     * 查询用户列表信息
     *
     * @return List 用户集合
     */
    @Override
    public List<User> findList(User user) {
        return userRepository.findAll(userQueryer.getSpecification(user));
    }

    /**
     * 查询分页数据
     *
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    @Override
    public Page<User> findPage(User user, Pageable pageable) {
        return userRepository.findAll(userQueryer.getSpecification(user), pageable);
    }

    /**
     * 保存用户信息
     *
     * @param user 用户对象
     * @return User 更新或新增后的用户对象
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * 批量保存用户
     *
     * @param userList 用户集合
     * @return List 更新或新增后的用户集合
     */
    @Override
    public List<User> saveAll(List<User> userList) {
        return userRepository.saveAll(userList);
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户编号
     * @return 受影响行数
     */
    @Override
    public Long deleteById(String id) {
        return userRepository.deleteUsersById(id);
    }

    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    @Override
    public Long deleteInBatch(String ids) {
        return userRepository.deleteUsersByIdIn(Arrays.asList(ids.split(",")));
    }
}
