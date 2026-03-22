package com.campuscatalyst.domain.content.repository;

import com.campuscatalyst.domain.content.ContentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContentPlanRepository extends JpaRepository<ContentPlan, UUID> {

    List<ContentPlan> findByCampaignId(UUID campaignId);

    Optional<ContentPlan> findByCampaignIdAndApprovedTrue(UUID campaignId);
}
