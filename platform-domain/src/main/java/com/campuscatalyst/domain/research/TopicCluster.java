package com.campuscatalyst.domain.research;

import com.campuscatalyst.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TopicCluster groups related topics discovered during research.
 *
 * Each cluster represents a thematic area that can be targeted
 * in content creation for the campaign.
 */
@Entity
@Table(name = "topic_clusters")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicCluster extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "research_job_id", nullable = false)
    private ResearchJob researchJob;

    @NotBlank
    @Size(max = 255)
    @Column(name = "topic_label", nullable = false)
    private String topicLabel;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "evidence_refs", columnDefinition = "TEXT")
    private String evidenceRefs; // JSON array of source references

    @Column(name = "relevance_score")
    private Double relevanceScore;
}
