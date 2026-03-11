package com.uniquehire.rolemanagement.dto.response;

import com.uniquehire.rolemanagement.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponse {

    private Long roleId;
    private String roleName;
    private String description;
    private Status status;
}