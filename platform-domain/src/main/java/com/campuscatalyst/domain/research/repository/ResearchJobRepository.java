package com.campuscatalyst.domain.research.repository;

import com.campuscatalyst.domain.common.Status;
import com.campuscatalyst.domain.research.ResearchJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResearchJobRepository extends JpaRepository<ResearchJob, UUID> {

    List<ResearchJob> findByCampaignId(UUID campaignId);

    List<ResearchJob> findByCampaignIdAndStatus(UUID campaignId, Status status);

    List<ResearchJob> findByStatus(Status status);
}
