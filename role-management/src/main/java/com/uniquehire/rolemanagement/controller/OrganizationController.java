package com.uniquehire.rolemanagement.controller;
import com.uniquehire.rolemanagement.dto.request.OrganizationRequestDto;
import com.uniquehire.rolemanagement.dto.response.OrganizationResponseDTO;
import com.uniquehire.rolemanagement.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationResponseDTO> createOrganization(
            @Valid @RequestBody OrganizationRequestDto requestDto) {
        return new ResponseEntity<>(organizationService.createOrganization(requestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<OrganizationResponseDTO> getOrganizationById(@PathVariable Long orgId) {
        return ResponseEntity.ok(organizationService.getOrganizationById(orgId));
    }

    @GetMapping
    public ResponseEntity<List<OrganizationResponseDTO>> getAllOrganizations() {
        return ResponseEntity.ok(organizationService.getAllOrganizations());
    }

    @PutMapping("/{orgId}")
    public ResponseEntity<OrganizationResponseDTO> updateOrganization(
            @PathVariable Long orgId,
            @Valid @RequestBody OrganizationRequestDto requestDto) {
        return ResponseEntity.ok(organizationService.updateOrganization(orgId, requestDto));
    }

    @DeleteMapping("/{orgId}")
    public ResponseEntity<String> deleteOrganization(@PathVariable Long orgId) {
        return ResponseEntity.ok(organizationService.deleteOrganization(orgId));
    }
}
