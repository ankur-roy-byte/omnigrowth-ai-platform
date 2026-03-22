package com.campuscatalyst.domain.publishing.repository;

import com.campuscatalyst.domain.common.Platform;
import com.campuscatalyst.domain.publishing.Publication;
import com.campuscatalyst.domain.publishing.PublicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, UUID> {

    List<Publication> findByCampaignId(UUID campaignId);

    List<Publication> findByCampaignIdAndPlatform(UUID campaignId, Platform platform);

    List<Publication> findByStatus(PublicationStatus status);

    List<Publication> findByStatusAndScheduledTimeBefore(PublicationStatus status, Instant time);

    Optional<Publication> findByPlatformAndPostId(Platform platform, String postId);
}
