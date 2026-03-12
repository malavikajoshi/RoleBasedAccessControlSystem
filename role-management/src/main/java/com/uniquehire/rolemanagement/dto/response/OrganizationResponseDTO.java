package com.uniquehire.rolemanagement.dto.response;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationResponseDTO {
    private Long orgId;
    private String orgName;
    private String address;
    private String code;
}