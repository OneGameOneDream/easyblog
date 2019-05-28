package com.ggiit.easyblog.project.system.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ggiit.easyblog.common.constant.WebKeys;
import com.ggiit.easyblog.common.exception.EntityNotFoundException;
import com.ggiit.easyblog.common.exception.EntityExistException;
import com.ggiit.easyblog.common.util.page.PageUtil;
import com.ggiit.easyblog.project.system.menu.entity.Menu;
import com.ggiit.easyblog.project.system.menu.service.MenuService;
import com.ggiit.easyblog.project.system.role.entity.Role;
import com.ggiit.easyblog.project.system.role.service.RoleService;
import com.ggiit.easyblog.project.system.user.entity.User;
import com.ggiit.easyblog.project.system.user.query.UserQuery;
import com.ggiit.easyblog.project.system.user.repository.UserRepository;
import com.ggiit.easyblog.project.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 用戶业务层实现
 *
 * @author gao
 * @date 2019.4.23
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    /**
     * 用户持久层对象
     */
    @Autowired
    private UserRepository userRepository;
    /**
     * 角色业务层对象
     */
    @Autowired
    private RoleService roleService;
    /**
     * 菜单业务层对象
     */
    @Autowired
    private MenuService menuService;

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
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id 为 " + id + " 的用户没有找到"));
    }


    /**
     * 查询分页数据
     *
     * @param pageable 分页配置
     * @return Page 分页对象
     */
    @Override
    public Object findPage(User user, Pageable pageable) {
        Page<User> page = userRepository.findAll(userQuery.getListSpecification(user), pageable);
        return PageUtil.toPage(page);
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
            throw new EntityExistException("用户名 " + user.getUsername() + " 已经存在");
        }
        if (userRepository.findByEmail(user.getEmail()) != null && !user.getId().equals(u.getId())) {
            throw new EntityExistException("Email " + user.getEmail() + " 已经存在");
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
            throw new EntityExistException("用户名 " + user.getUsername() + " 已经存在");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new EntityExistException("Email " + user.getEmail() + " 已经存在");
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

    /**
     * 根据用户对象查询用户权限
     *
     * @param user 用户对象
     * @return 权限集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Collection<GrantedAuthority> findAuthorities(User user) {
        //权限集合(在Security中，角色和权限共用GrantedAuthority接口，唯一的不同角色就是多了个前缀"ROLE_"[严格区分大小写]，
        // 而且它没有Shiro的那种从属关系，即一个角色包含哪些权限等等。
        // 在Security看来角色和权限时一样的，它认证的时候，把所有权限（角色、权限）都取出来，而不是分开验证。)
        List<GrantedAuthority> authorities = new ArrayList<>();
        //通过用户集合查询其拥有角色集合
        List<Role> roles = roleService.findRolesByUserId(user.getId());
        //用户角色
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(WebKeys.ROLE_PREFIX + role.getKey()));
        }
        //通过角色集合查询其拥有菜单
        List<Menu> menus = menuService.findMenusByUserId(user.getId());
        //获取用户的菜单权限
        for (Menu menu : menus) {
            if (!StrUtil.isBlank(menu.getPermission())) {
                authorities.add(new SimpleGrantedAuthority(menu.getPermission()));
            }
        }
        return authorities;
    }

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return user 用户对象
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return user 用户对象
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
