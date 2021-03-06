package com.ggiit.easyblog.project.system.menu.entity;

import com.ggiit.easyblog.framework.web.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 菜单权限实体
 *
 * @author gao
 * @date 2019.5.9
 */
@Entity
@Table(name = "T_SYS_MENU")
@Data
@EqualsAndHashCode(callSuper = true)
public class Menu extends BaseEntity {
    /**
     * 菜单名称
     */
    @Column(name = "NAME_", nullable = false, length = 50)
    private String name;
    /**
     * 父菜单ID
     */
    @Column(name = "PARENT_ID_", length = 64)
    private String parentId;
    /**
     * 显示顺序
     */
    @Column(name = "SORT_", length = 100)
    private Integer sort;
    /**
     * 请求地址
     */
    @Column(name = "URL_", length = 200)
    private String url;
    /**
     * 菜单类型（0:目录 1:菜单 2:按钮）
     */
    @Column(name = "TYPE_", nullable = false, length = 1)
    private Integer type;
    /**
     * 菜单状态：1启用、0禁用
     */
    @Column(name = "STATE_", nullable = false, length = 1)
    private Boolean state;
    /**
     * 权限标识
     */
    @Column(name = "PERMISSION_", length = 100)
    private String permission;
    /**
     * 菜单图标
     */
    @Column(name = "ICON_", length = 100)
    private String icon;
}
