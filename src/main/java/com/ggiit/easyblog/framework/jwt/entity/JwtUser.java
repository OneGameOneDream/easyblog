package com.ggiit.easyblog.framework.jwt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ggiit.easyblog.framework.web.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

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
