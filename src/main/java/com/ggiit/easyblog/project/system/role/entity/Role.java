package com.ggiit.easyblog.project.system.role.entity;


import com.ggiit.easyblog.framework.web.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    /**
     * 角色名称
     */
    @Column(name = "NAME_", nullable = false, length = 50)
    private String name;
    /**
     * 角色权限字符串标识
     */
    @Column(name = "KEY_", nullable = false, length = 50)
    private String key;
    /**
     * 显示顺序
     */
    @Column(name = "SORT_", nullable = false)
    private Integer sort;
    /**
     * 角色状态：1正常、0停用
     */
    @Column(name = "STATE_", nullable = false, length = 1)
    private Boolean state;

}
