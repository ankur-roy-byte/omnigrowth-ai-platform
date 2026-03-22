package com.campuscatalyst.app.service;

import com.campuscatalyst.domain.campaign.Campaign;
import com.campuscatalyst.domain.campaign.repository.CampaignRepository;
import com.campuscatalyst.domain.common.Status;
import com.campuscatalyst.domain.tenant.Tenant;
import com.campuscatalyst.domain.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for managing marketing campaigns.
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CampaignService {

    private final CampaignRepository campaignRepository;
    private final TenantRepository tenantRepository;

    public List<Campaign> findAll() {
        return campaignRepository.findAll();
    }

    public List<Campaign> findByTenantId(UUID tenantId) {
        return campaignRepository.findByTenantId(tenantId);
    }

    public Optional<Campaign> findById(UUID id) {
        return campaignRepository.findById(id);
    }

    public List<Campaign> findByStatus(Status status) {
        return campaignRepository.findByStatus(status);
    }

    @Transactional
    public Campaign create(UUID tenantId, Campaign campaign) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found: " + tenantId));

        campaign.setTenant(tenant);
        campaign.setStatus(Status.DRAFT);
        return campaignRepository.save(campaign);
    }

    @Transactional
    public Campaign update(UUID id, Campaign updates) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Campaign not found: " + id));

        if (updates.getName() != null) {
            campaign.setName(updates.getName());
        }
        if (updates.getDescription() != null) {
            campaign.setDescription(updates.getDescription());
        }
        if (updates.getDomainFocus() != null) {
            campaign.setDomainFocus(updates.getDomainFocus());
        }
        if (updates.getGoals() != null) {
            campaign.setGoals(updates.getGoals());
        }
        if (updates.getPlatformsEnabled() != null) {
            campaign.setPlatformsEnabled(updates.getPlatformsEnabled());
        }
        if (updates.getScheduleStart() != null) {
            campaign.setScheduleStart(updates.getScheduleStart());
        }
        if (updates.getScheduleEnd() != null) {
            campaign.setScheduleEnd(updates.getScheduleEnd());
        }

        return campaignRepository.save(campaign);
    }

    @Transactional
    public Campaign updateStatus(UUID id, Status status) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Campaign not found: " + id));
        campaign.setStatus(status);
        return campaignRepository.save(campaign);
    }

    @Transactional
    public void delete(UUID id) {
        if (!campaignRepository.existsById(id)) {
            throw new IllegalArgumentException("Campaign not found: " + id);
        }
        campaignRepository.deleteById(id);
    }
}
