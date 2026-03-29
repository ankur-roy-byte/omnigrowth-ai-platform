package com.campuscatalyst.app.controller.api;

import com.campuscatalyst.app.dto.CampaignDto;
import com.campuscatalyst.app.dto.DtoMapper;
import com.campuscatalyst.app.service.CampaignService;
import com.campuscatalyst.domain.campaign.Campaign;
import com.campuscatalyst.domain.common.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * REST API for managing campaigns.
 */
@RestController
@RequestMapping("/api/v1/campaigns")
@RequiredArgsConstructor
public class CampaignController {

    private final CampaignService campaignService;
    private final DtoMapper mapper;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<CampaignDto>> getCampaigns(
            @RequestParam(required = false) UUID tenantId,
            @RequestParam(required = false) Status status) {

        List<Campaign> campaigns;
        if (tenantId != null) {
            campaigns = campaignService.findByTenantId(tenantId);
        } else if (status != null) {
            campaigns = campaignService.findByStatus(status);
        } else {
            campaigns = campaignService.findAll();
        }

        List<CampaignDto> dtos = campaigns.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<CampaignDto> getCampaignById(@PathVariable UUID id) {
        return campaignService.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CampaignDto> createCampaign(
            @RequestParam UUID tenantId,
            @Valid @RequestBody CampaignDto campaignDto) {
        try {
            Campaign campaign = mapper.toEntity(campaignDto);
            Campaign created = campaignService.create(tenantId, campaign);
            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CampaignDto> updateCampaign(
            @PathVariable UUID id,
            @Valid @RequestBody CampaignDto campaignDto) {
        try {
            Campaign updates = mapper.toEntity(campaignDto);
            Campaign updated = campaignService.update(id, updates);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CampaignDto> updateCampaignStatus(
            @PathVariable UUID id,
            @RequestBody Map<String, String> body) {
        try {
            Status status = Status.valueOf(body.get("status").toUpperCase());
            Campaign updated = campaignService.updateStatus(id, status);
            return ResponseEntity.ok(mapper.toDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCampaign(@PathVariable UUID id) {
        try {
            campaignService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
