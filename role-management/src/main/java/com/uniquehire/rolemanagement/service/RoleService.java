package com.uniquehire.rolemanagement.service;

import com.uniquehire.rolemanagement.dto.request.RoleRequest;
import com.uniquehire.rolemanagement.dto.response.ApiResponse;
import com.uniquehire.rolemanagement.dto.response.RoleResponse;

import org.springframework.data.domain.Page;

import java.util.List;

public interface RoleService {

    ApiResponse<RoleResponse> createRole(RoleRequest request);

    ApiResponse<RoleResponse> updateRole(Long roleId, RoleRequest request);

    List<RoleResponse> getAllRoles();

    ApiResponse<RoleResponse> getRoleById(Long roleId);

    ApiResponse<String> deleteRole(Long roleId);

    ApiResponse<String> assignPermission(Long roleId, Long permissionId);

    ApiResponse<String> assignRoleToUser(Long userId, Long roleId);

    Page<RoleResponse> getRoles(int page, int size);
}