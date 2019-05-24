package com.ggiit.easyblog.project.system.relation.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户角色关系
 *
 * @author gao
 * @date 2019.5.24
 */
@Entity
@Table(name = "T_SYS_USER_ROLE")
@Data
@EqualsAndHashCode()
public class UserRole {
    /**
     * 编号
     */
    @Column(name = "ID_", unique = true, nullable = false, length = 64)
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "uuid")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID_", nullable = false, length = 64)
    private String userId;
    /**
     * 角色ID
     */
    @Column(name = "ROLE_ID_", nullable = false, length = 64)
    private String roleId;
}
