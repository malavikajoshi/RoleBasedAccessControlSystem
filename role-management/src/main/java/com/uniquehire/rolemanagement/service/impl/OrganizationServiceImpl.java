package com.uniquehire.rolemanagement.service.impl;
import com.uniquehire.rolemanagement.dto.request.OrganizationRequestDto;
import com.uniquehire.rolemanagement.dto.response.OrganizationResponseDTO;
import com.uniquehire.rolemanagement.entity.Organization;
import com.uniquehire.rolemanagement.exception.ResourceNotFoundException;
import com.uniquehire.rolemanagement.repository.OrganizationRepository;
import com.uniquehire.rolemanagement.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponseDTO createOrganization(OrganizationRequestDto requestDto) {
        Organization organization = mapToEntity(requestDto);
        Organization savedOrganization = organizationRepository.save(organization);
        return mapToResponseDto(savedOrganization);
    }

    @Override
    public OrganizationResponseDTO getOrganizationById(Long orgId) {
        Organization organization = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization id not found"));
        return mapToResponseDto(organization);
    }

    @Override
    public List<OrganizationResponseDTO> getAllOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();
        return organizations.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    @Override
    public OrganizationResponseDTO updateOrganization(Long orgId, OrganizationRequestDto requestDto) {
        Organization existingOrganization = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization id not found"));

        existingOrganization.setOrgName(requestDto.getOrgName());
        existingOrganization.setAddress(requestDto.getAddress());
        existingOrganization.setCode(requestDto.getCode());

        Organization updatedOrganization = organizationRepository.save(existingOrganization);
        return mapToResponseDto(updatedOrganization);
    }

    @Override
    public String deleteOrganization(Long orgId) {
        Organization organization = organizationRepository.findById(orgId)
                .orElseThrow(() -> new ResourceNotFoundException("Organization id not found"));

        organizationRepository.delete(organization);
        return "Organization deleted successfully";
    }

    private Organization mapToEntity(OrganizationRequestDto requestDto) {
        return Organization.builder()
                .orgName(requestDto.getOrgName())
                .address(requestDto.getAddress())
                .code(requestDto.getCode())
                .build();
    }

    private OrganizationResponseDTO mapToResponseDto(Organization organization) {
        return OrganizationResponseDTO.builder()
                .orgId(organization.getOrgId())
                .orgName(organization.getOrgName())
                .address(organization.getAddress())
                .code(organization.getCode())
                .build();
    }
}