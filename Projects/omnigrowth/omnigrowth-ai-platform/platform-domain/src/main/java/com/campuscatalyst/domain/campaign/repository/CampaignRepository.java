package com.campuscatalyst.domain.campaign.repository;

import com.campuscatalyst.domain.campaign.Campaign;
import com.campuscatalyst.domain.common.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository for Campaign entity operations.
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, UUID> {

    List<Campaign> findByTenantId(UUID tenantId);

    List<Campaign> findByTenantIdAndStatus(UUID tenantId, Status status);

    List<Campaign> findByStatus(Status status);
}
