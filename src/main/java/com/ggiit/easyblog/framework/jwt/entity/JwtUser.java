package com.ggiit.easyblog.framework.jwt.entity;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ggiit.easyblog.common.constant.WebKeys;
import com.ggiit.easyblog.framework.web.entity.BaseEntity;
import com.ggiit.easyblog.project.system.menu.entity.Menu;
import com.ggiit.easyblog.project.system.role.entity.Role;
import com.ggiit.easyblog.project.system.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录认证用户
 *
 * @author gao
 * @date 2019.5.12
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser extends BaseEntity implements UserDetails {

    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 头像
     */
    private String avatar;
    /**
     * Email
     */
    private String email;
    /**
     * 状态：1启用、0禁用
     */
    private Boolean state;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 最后登陆IP
     */
    private String loginIp;
    /**
     * 最后登陆日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginDate;
    /**
     * 权限集合
     */
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;


//****************************************************************************************************

    /**
     * 密码
     *
     * @return 密码
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 用户名
     *
     * @return 用户名
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 获取用户权限集合
     *
     * @return 用户权限集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    /**
     * 账户是否未过期
     *
     * @return true or false
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     *
     * @return true or false
     */
    @Override
    public boolean isAccountNonLocked() {
        return state;
    }

    /**
     * 密码是否未过期
     *
     * @return true or false
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活
     *
     * @return true or false
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
