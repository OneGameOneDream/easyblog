package com.ggiit.easyblog.project.system.user.service;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.query.UserQuery;
import com.ggiit.easyblog.project.system.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 用戶业务层实现
 *
 * @author gao
 * @date 2019.4.23
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserQuery userQuery;

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
        return userRepository.findAll(userQuery.getListSpecification(user));
    }

    /**
     * 查询分页数据
     *
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    @Override
    public Page<User> findPage(User user, Pageable pageable) {
        return userRepository.findAll(userQuery.getListSpecification(user), pageable);
    }

    /**
     * 保存用户信息
     *
     * @param user 用户对象
     * @return User 更新或新增后的用户对象
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User update(User user) {
        User u = userRepository.getOne(user.getId());
        if (userRepository.findByUsername(user.getUsername()) != null) {
            logger.warn("用户名:" + u.getUsername() + "已存在!");
            return null;
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            logger.warn("邮箱:" + u.getEmail() + "已存在!");
            return null;
        }
        //动态更新
        if (!StrUtil.isBlank(user.getUsername())) {
            u.setUsername(user.getUsername());
        }
        if (!StrUtil.isBlank(user.getAvatar())) {
            u.setAvatar(user.getAvatar());
        }
        if (!StrUtil.isBlank(user.getPhone())) {
            u.setPhone(user.getPhone());
        }
        if (user.getState() != null) {
            u.setState(user.getState());
        }
        if (!StrUtil.isBlank(user.getEmail())) {
            u.setEmail(user.getEmail());
        }
        if (user.getDelFlag() != null) {
            u.setDelFlag(user.getDelFlag());
        }
        if (!StrUtil.isBlank(user.getPassword())) {
            u.setPassword(user.getPassword());
        }
        return userRepository.save(u);
    }

    /**
     * 新增用户信息
     *
     * @param user 用户对象
     * @return User 新增后的用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User insert(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            logger.warn("用户名:" + user.getUsername() + "已存在!");
            return null;
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            logger.warn("邮箱:" + user.getEmail() + "已存在!");
            return null;
        }
        return userRepository.save(user);
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户编号
     * @return 受影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(rollbackFor = Exception.class)
    public Long deleteInBatch(String ids) {
        return userRepository.deleteUsersByIdIn(Arrays.asList(ids.split(",")));
    }
}
