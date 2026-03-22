package com.campuscatalyst.domain.content.repository;

import com.campuscatalyst.domain.content.ContentAsset;
import com.campuscatalyst.domain.content.ContentAssetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContentAssetRepository extends JpaRepository<ContentAsset, UUID> {

    List<ContentAsset> findByCampaignId(UUID campaignId);

    List<ContentAsset> findByCampaignIdAndType(UUID campaignId, ContentAssetType type);

    List<ContentAsset> findByCampaignIdAndApprovedTrue(UUID campaignId);
}
