package com.ggiit.easyblog.project.system.user.service;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.exception.EmailExistException;
import com.ggiit.easyblog.common.exception.UsernameExistException;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.query.UserQuery;
import com.ggiit.easyblog.project.system.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * 用戶业务层实现
 *
 * @author gao
 * @date 2019.4.23
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 用户持久层对象
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * 用户查询器对象
     */
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
        if (userRepository.findByUsername(user.getUsername()) != null && !user.getId().equals(u.getId())) {
            throw new UsernameExistException();
        }
        if (userRepository.findByEmail(user.getEmail()) != null && !user.getId().equals(u.getId())) {
            throw new EmailExistException();
        }
        //动态更新
        //用户名
        if (!StrUtil.isBlank(user.getUsername())) {
            u.setUsername(user.getUsername());
        }
        //用户头像
        if (!StrUtil.isBlank(user.getAvatar())) {
            u.setAvatar(user.getAvatar());
        }
        //用户电话
        if (!StrUtil.isBlank(user.getPhone())) {
            u.setPhone(user.getPhone());
        }
        //用户状态
        if (user.getState() != null) {
            u.setState(user.getState());
        }
        //用户邮箱
        if (!StrUtil.isBlank(user.getEmail())) {
            u.setEmail(user.getEmail());
        }
        //用户删除标识
        if (user.getDelFlag() != null) {
            u.setDelFlag(user.getDelFlag());
        }
        //用户密码
        if (!StrUtil.isBlank(user.getPassword())) {
            u.setPassword(user.getPassword());
        }
        //用户昵称
        if (!StrUtil.isBlank(user.getNickname())) {
            u.setNickname(user.getNickname());
        }
        //用户登陆ip
        if (!StrUtil.isBlank(user.getLoginIp())) {
            u.setLoginIp(user.getLoginIp());
        }
        //用户登陆时间
        if (user.getLoginDate() != null) {
            u.setLoginDate(user.getLoginDate());
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
            throw new UsernameExistException();
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailExistException();
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
    public Long deleteUserById(String id) {
        return userRepository.deleteUserById(id);
    }

    /**
     * 批量删除用户
     *
     * @param ids 多个用户编号（用_隔开）
     * @return 受影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long deleteUsersByIdIn(String ids) {
        return userRepository.deleteUsersByIdIn(Arrays.asList(ids.split(",")));
    }
}
