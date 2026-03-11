package com.uniquehire.rolemanagement.service;

import com.uniquehire.rolemanagement.dto.request.RoleRequest;
import com.uniquehire.rolemanagement.dto.response.ApiResponse;
import com.uniquehire.rolemanagement.dto.response.RoleResponse;
import com.uniquehire.rolemanagement.entity.*;
import com.uniquehire.rolemanagement.enums.Status;
import com.uniquehire.rolemanagement.exception.RoleAlreadyExistsException;
import com.uniquehire.rolemanagement.repository.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public ApiResponse<RoleResponse> createRole(RoleRequest request) {

        log.info("Creating role with name {}", request.getRoleName());

        if(roleRepository.findByRoleName(request.getRoleName()).isPresent()){
            log.warn("Role already exists with name {}", request.getRoleName());
            throw new RoleAlreadyExistsException("Role already exists");
        }

        Role role = Role.builder()
                .roleName(request.getRoleName())
                .description(request.getDescription())
                .status(Status.ACTIVE)
                .createdAt(LocalDateTime.now())
                .createdBy("SYSTEM")
                .build();

        Role savedRole = roleRepository.save(role);

        log.info("Role created successfully with id {}", savedRole.getRoleId());

        return ApiResponse.<RoleResponse>builder()
                .success(true)
                .message("Role created successfully")
                .data(map(savedRole))
                .build();
    }

    @Override
    public ApiResponse<RoleResponse> updateRole(Long roleId, RoleRequest request) {

        log.info("Updating role with id {}", roleId);

        Role role = roleRepository.findById(roleId).orElse(null);

        if(role == null){
            log.warn("Role not found with id {}", roleId);

            return ApiResponse.<RoleResponse>builder()
                    .success(false)
                    .message("Role not found")
                    .data(null)
                    .build();
        }

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());
        role.setUpdatedAt(LocalDateTime.now());
        role.setUpdatedBy("SYSTEM");

        Role updatedRole = roleRepository.save(role);

        log.info("Role updated successfully with id {}", roleId);

        return ApiResponse.<RoleResponse>builder()
                .success(true)
                .message("Role updated successfully")
                .data(map(updatedRole))
                .build();
    }

    @Override
    public ApiResponse<RoleResponse> getRoleById(Long roleId) {

        log.info("Fetching role with id {}", roleId);

        Role role = roleRepository.findById(roleId).orElse(null);

        if(role == null){

            log.warn("Role not found with id {}", roleId);

            return ApiResponse.<RoleResponse>builder()
                    .success(false)
                    .message("Role not found")
                    .data(null)
                    .build();
        }

        log.info("Role fetched successfully with id {}", roleId);

        return ApiResponse.<RoleResponse>builder()
                .success(true)
                .message("Role fetched successfully")
                .data(map(role))
                .build();
    }

    @Override
    public ApiResponse<String> deleteRole(Long roleId) {

        log.info("Deleting role with id {}", roleId);

        Role role = roleRepository.findById(roleId).orElse(null);

        if(role == null){
            log.warn("Delete failed. Role not found with id {}", roleId);

            return ApiResponse.<String>builder()
                    .success(false)
                    .message("Role not found")
                    .data(null)
                    .build();
        }

        role.setStatus(Status.DELETED);
        roleRepository.save(role);

        log.info("Role deleted successfully with id {}", roleId);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Role deleted successfully")
                .data(null)
                .build();
    }

    @Override
    public ApiResponse<String> assignPermission(Long roleId, Long permissionId) {

        log.info("Assigning permission {} to role {}", permissionId, roleId);

        Role role = roleRepository.findById(roleId).orElse(null);
        Permission permission = permissionRepository.findById(permissionId).orElse(null);

        if(role == null || permission == null){

            log.warn("Role or Permission not found. roleId: {}, permissionId: {}", roleId, permissionId);

            return ApiResponse.<String>builder()
                    .success(false)
                    .message("Role or Permission not found")
                    .data(null)
                    .build();
        }

        rolePermissionRepository.save(
                RolePermission.builder()
                        .role(role)
                        .permission(permission)
                        .build()
        );

        log.info("Permission {} assigned successfully to role {}", permissionId, roleId);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Permission assigned successfully")
                .data(null)
                .build();
    }

    @Override
    public ApiResponse<String> assignRoleToUser(Long userId, Long roleId) {

        log.info("Assigning role {} to user {}", roleId, userId);

        Role role = roleRepository.findById(roleId).orElse(null);

        if(role == null){

            log.warn("Role not found with id {}", roleId);

            return ApiResponse.<String>builder()
                    .success(false)
                    .message("Role not found")
                    .data(null)
                    .build();
        }

        userRoleRepository.save(
                UserRole.builder()
                        .userId(userId)
                        .role(role)
                        .build()
        );

        log.info("Role {} assigned successfully to user {}", roleId, userId);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Role assigned successfully")
                .data(null)
                .build();
    }

    @Override
    public List<RoleResponse> getAllRoles() {

        log.info("Fetching all roles from database");

        return roleRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public Page<RoleResponse> getRoles(int page, int size) {

        log.info("Fetching roles with pagination page {} and size {}", page, size);

        Pageable pageable = PageRequest.of(page,size);

        return roleRepository.findAll(pageable)
                .map(this::map);
    }

    private RoleResponse map(Role role){

        return RoleResponse.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .status(role.getStatus())
                .build();
    }
}