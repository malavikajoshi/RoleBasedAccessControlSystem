package com.uniquehire.rolemanagement.repository;

import com.uniquehire.rolemanagement.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}