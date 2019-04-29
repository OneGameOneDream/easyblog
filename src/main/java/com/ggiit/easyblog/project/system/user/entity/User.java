package com.ggiit.easyblog.project.system.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ggiit.easyblog.framework.web.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author gao
 * @date 2019.4.23
 */
@Entity
@Table(name = "T_SYS_USER")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {
    /**
     * 用户名
     */
    @Column(name = "USERNAME_", nullable = false, length = 50)
    private String username;
    /**
     * 密码
     */
    @Column(name = "PASSWORD_", nullable = false, length = 50)
    private String password;
    /**
     * 头像
     */
    @Column(name = "AVATAR_")
    private String avatar;
    /**
     * Email
     */
    @Column(name = "EMAIL_", length = 100)
    private String email;
    /**
     * 状态：1启用、0禁用
     */
    @Column(name = "STATE_", length = 1)
    private Boolean state;
    /**
     * 手机号码
     */
    @Column(name = "PHONE_", length = 11)
    private String phone;
    /**
     * 最后登陆IP
     */
    @Column(name = "LOGIN_IP_")
    private String loginIp;
    /**
     * 最后登陆日期
     */
    @Column(name = "LOGIN_DATE_")
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginDate;
    /**
     * 删除标识：1启用、0删除
     */
    @Column(name = "DEL_FLAG_", length = 1)
    private Boolean delFlag;
}
