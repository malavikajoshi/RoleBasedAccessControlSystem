package com.uniquehire.rolemanagement.service;

import com.uniquehire.rolemanagement.dto.request.OrganizationRequestDto;
import com.uniquehire.rolemanagement.dto.response.OrganizationResponseDTO;
import java.util.List;

public interface OrganizationService {

    OrganizationResponseDTO createOrganization(OrganizationRequestDto requestDto);

    OrganizationResponseDTO getOrganizationById(Long orgId);

    List<OrganizationResponseDTO> getAllOrganizations();

    OrganizationResponseDTO updateOrganization(Long orgId, OrganizationRequestDto requestDto);

    String deleteOrganization(Long orgId);
}