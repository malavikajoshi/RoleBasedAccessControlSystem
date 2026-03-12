package com.uniquehire.rolemanagement.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationRequestDto {

    @NotBlank(message = "Organization name is required")
    private String orgName;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Code is required")
    private String code;
}