package com.campuscatalyst.domain.publishing;

import com.campuscatalyst.domain.campaign.Campaign;
import com.campuscatalyst.domain.common.BaseEntity;
import com.campuscatalyst.domain.common.Platform;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * Publication represents content that has been published to a social platform.
 *
 * Tracks the publication status, platform-specific post IDs, and publish timing.
 */
@Entity
@Table(name = "publications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publication extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private Platform platform;

    @Column(name = "post_id")
    private String postId; // Platform-specific post/video ID

    @Column(name = "post_url")
    private String postUrl; // Direct URL to the post

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private PublicationStatus status = PublicationStatus.SCHEDULED;

    @Column(name = "scheduled_time")
    private Instant scheduledTime;

    @Column(name = "publish_time")
    private Instant publishTime;

    @Column(name = "content_asset_id")
    private String contentAssetId; // Reference to ContentAsset

    @Column(name = "video_asset_id")
    private String videoAssetId; // Reference to VideoAsset

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata; // Platform-specific metadata
}
