package com.uniquehire.rolemanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleRequest {

    @NotBlank(message = "Role name is required")
    private String roleName;

    private String description;

}