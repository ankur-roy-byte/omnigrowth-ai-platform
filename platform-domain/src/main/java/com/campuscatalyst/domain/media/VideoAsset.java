package com.campuscatalyst.domain.media;

import com.campuscatalyst.domain.campaign.Campaign;
import com.campuscatalyst.domain.common.BaseEntity;
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

/**
 * VideoAsset represents a video that has been generated for a campaign.
 *
 * Videos are created using external rendering services (like Shotstack)
 * and stored in cloud storage for publishing.
 */
@Entity
@Table(name = "video_assets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoAsset extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @Column(name = "template_id")
    private String templateId;

    @Column(name = "title")
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "render_status", nullable = false)
    @Builder.Default
    private RenderStatus renderStatus = RenderStatus.PENDING;

    @Column(name = "render_job_id")
    private String renderJobId; // External renderer job ID

    @Column(name = "storage_url")
    private String storageUrl; // Cloud storage URL

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(name = "duration_seconds")
    private Integer durationSeconds;

    @Column(name = "render_config", columnDefinition = "TEXT")
    private String renderConfig; // JSON template configuration

    @Column(name = "error_message")
    private String errorMessage;
}
