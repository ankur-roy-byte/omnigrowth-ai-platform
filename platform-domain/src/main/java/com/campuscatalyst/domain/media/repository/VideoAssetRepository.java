package com.campuscatalyst.domain.media.repository;

import com.campuscatalyst.domain.media.RenderStatus;
import com.campuscatalyst.domain.media.VideoAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoAssetRepository extends JpaRepository<VideoAsset, UUID> {

    List<VideoAsset> findByCampaignId(UUID campaignId);

    List<VideoAsset> findByRenderStatus(RenderStatus status);

    Optional<VideoAsset> findByRenderJobId(String renderJobId);
}
