package com.campuscatalyst.domain.content;

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
 * ContentAsset represents a piece of content created for a campaign.
 *
 * Assets can be captions, scripts, blog posts, or other text-based content
 * that will be used in publications.
 */
@Entity
@Table(name = "content_assets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentAsset extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "asset_type", nullable = false)
    private ContentAssetType type;

    @Column(name = "title")
    private String title;

    @Column(name = "content_text", columnDefinition = "TEXT")
    private String text;

    @Column(name = "version")
    @Builder.Default
    private Integer version = 1;

    @Column(name = "metadata", columnDefinition = "TEXT")
    private String metadata; // JSON for additional properties

    @Column(name = "approved")
    @Builder.Default
    private boolean approved = false;
}
