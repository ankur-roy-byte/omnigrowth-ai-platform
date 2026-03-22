package com.campuscatalyst.domain.content;

import com.campuscatalyst.domain.campaign.Campaign;
import com.campuscatalyst.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
 * ContentPlan defines the strategic outline for campaign content.
 *
 * It includes the content structure, key messages to convey,
 * and calls-to-action for the audience.
 */
@Entity
@Table(name = "content_plans")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentPlan extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @Column(name = "outline", columnDefinition = "TEXT")
    private String outline; // JSON structured outline

    @Column(name = "key_messages", columnDefinition = "TEXT")
    private String keyMessages; // JSON array of key messages

    @Column(name = "cta", columnDefinition = "TEXT")
    private String cta; // Call-to-action text and links

    @Column(name = "target_audience")
    private String targetAudience;

    @Column(name = "tone")
    private String tone; // e.g., professional, casual, inspiring

    @Column(name = "approved")
    @Builder.Default
    private boolean approved = false;
}
