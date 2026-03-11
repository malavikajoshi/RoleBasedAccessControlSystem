package com.uniquehire.rolemanagement.repository;

import com.uniquehire.rolemanagement.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
}