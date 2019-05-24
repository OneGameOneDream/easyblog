package com.ggiit.easyblog.project.system.relation.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 角色菜单
 *
 * @author gao
 * @date 2019.5.24
 */
@Entity
@Table(name = "T_SYS_ROLE_MENU")
@Data
@EqualsAndHashCode()
public class RoleMenu {
    /**
     * 编号
     */
    @Column(name = "ID_", unique = true, nullable = false, length = 64)
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /**
     * 角色ID
     */
    @Column(name = "ROLE_ID_", nullable = false, length = 64)
    private String roleId;
    /**
     * 菜单ID
     */
    @Column(name = "MENU_ID_", nullable = false, length = 64)
    private String menuId;

}
