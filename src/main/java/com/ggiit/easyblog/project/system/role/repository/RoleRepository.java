package com.ggiit.easyblog.project.system.role.repository;

import com.ggiit.easyblog.project.system.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色持久层
 *
 * @author gao
 * @date 2019.5.8
 */
public interface RoleRepository extends JpaRepository<Role, String> {

}
