package com.uniquehire.rolemanagement.controller;

import com.uniquehire.rolemanagement.dto.request.RoleRequest;
import com.uniquehire.rolemanagement.dto.response.ApiResponse;
import com.uniquehire.rolemanagement.dto.response.RoleResponse;
import com.uniquehire.rolemanagement.service.RoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@Valid @RequestBody RoleRequest request){

        log.info("Received request to create role with name: {}", request.getRoleName());

        ApiResponse<RoleResponse> response = roleService.createRole(request);

        log.info("Create role response message: {}", response.getMessage());

        return response;
    }

    @PutMapping("/{id}")
    public ApiResponse<RoleResponse> updateRole(@PathVariable Long id,
                                                @Valid @RequestBody RoleRequest request){

        log.info("Received request to update role with id: {}", id);

        ApiResponse<RoleResponse> response = roleService.updateRole(id, request);

        log.info("Update role response message: {}", response.getMessage());

        return response;
    }

    @GetMapping
    public Page<RoleResponse> getRoles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        log.info("Fetching roles with pagination - page: {}, size: {}", page, size);

        return roleService.getRoles(page, size);
    }

    @GetMapping("/{id}")
    public ApiResponse<RoleResponse> getRole(@PathVariable Long id){

        log.info("Fetching role with id: {}", id);

        ApiResponse<RoleResponse> response = roleService.getRoleById(id);

        log.info("Get role response message: {}", response.getMessage());

        return response;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteRole(@PathVariable Long id){

        log.info("Deleting role with id: {}", id);

        ApiResponse<String> response = roleService.deleteRole(id);

        log.info("Delete role response message: {}", response.getMessage());

        return response;
    }

    @PostMapping("/{roleId}/permissions/{permissionId}")
    public ApiResponse<String> assignPermission(@PathVariable Long roleId,
                                                @PathVariable Long permissionId){

        log.info("Assigning permission {} to role {}", permissionId, roleId);

        ApiResponse<String> response = roleService.assignPermission(roleId, permissionId);

        log.info("Assign permission response message: {}", response.getMessage());

        return response;
    }

    @PostMapping("/users/{userId}/roles/{roleId}")
    public ApiResponse<String> assignRoleToUser(@PathVariable Long userId,
                                                @PathVariable Long roleId){

        log.info("Assigning role {} to user {}", roleId, userId);

        ApiResponse<String> response = roleService.assignRoleToUser(userId, roleId);

        log.info("Assign role response message: {}", response.getMessage());

        return response;
    }
}