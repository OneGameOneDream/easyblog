package com.ggiit.easyblog.project.system.role.entity;


import com.ggiit.easyblog.framework.web.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色实体类
 *
 * @author gao
 * @date 2019.5.8
 */
@Entity
@Table(name = "T_SYS_ROLE")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {
    /**
     * 角色名称
     */
    @Column(name = "NAME_", nullable = false, length = 50)
    private String name;
    /**
     * 角色权限字符串
     */
    @Column(name = "KEY_", nullable = false, length = 100)
    private String key;
    /**
     * 显示顺序
     */
    @Column(name = "SORT_", nullable = false)
    private Integer sort;
    /**
     * 角色状态：1正常、0停用
     */
    @Column(name = "STATE_", nullable = false)
    private Boolean state;
    /**
     * 角色拥有用户集合
     */
//    @ManyToMany(mappedBy = "roleSet", fetch = FetchType.LAZY)
//    private Set<User> userSet;
}
