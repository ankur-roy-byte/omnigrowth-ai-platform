package com.campuscatalyst.app.dto;

import com.campuscatalyst.domain.campaign.Campaign;
import com.campuscatalyst.domain.tenant.Tenant;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between entities and DTOs.
 */
@Component
public class DtoMapper {

    public TenantDto toDto(Tenant tenant) {
        if (tenant == null) return null;

        return TenantDto.builder()
                .id(tenant.getId())
                .name(tenant.getName())
                .domainType(tenant.getDomainType())
                .timezone(tenant.getTimezone())
                .settings(tenant.getSettings())
                .active(tenant.isActive())
                .createdAt(tenant.getCreatedAt())
                .updatedAt(tenant.getUpdatedAt())
                .build();
    }

    public Tenant toEntity(TenantDto dto) {
        if (dto == null) return null;

        return Tenant.builder()
                .name(dto.getName())
                .domainType(dto.getDomainType())
                .timezone(dto.getTimezone())
                .settings(dto.getSettings())
                .active(dto.isActive())
                .build();
    }

    public CampaignDto toDto(Campaign campaign) {
        if (campaign == null) return null;

        return CampaignDto.builder()
                .id(campaign.getId())
                .tenantId(campaign.getTenant() != null ? campaign.getTenant().getId() : null)
                .name(campaign.getName())
                .description(campaign.getDescription())
                .domainFocus(campaign.getDomainFocus())
                .goals(campaign.getGoals())
                .platformsEnabled(campaign.getPlatformsEnabled())
                .status(campaign.getStatus())
                .scheduleStart(campaign.getScheduleStart())
                .scheduleEnd(campaign.getScheduleEnd())
                .scheduleCron(campaign.getScheduleCron())
                .createdAt(campaign.getCreatedAt())
                .updatedAt(campaign.getUpdatedAt())
                .build();
    }

    public Campaign toEntity(CampaignDto dto) {
        if (dto == null) return null;

        return Campaign.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .domainFocus(dto.getDomainFocus())
                .goals(dto.getGoals())
                .platformsEnabled(dto.getPlatformsEnabled())
                .scheduleStart(dto.getScheduleStart())
                .scheduleEnd(dto.getScheduleEnd())
                .scheduleCron(dto.getScheduleCron())
                .build();
    }
}
