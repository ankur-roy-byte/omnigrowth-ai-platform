package com.campuscatalyst.domain.research;

import com.campuscatalyst.domain.campaign.Campaign;
import com.campuscatalyst.domain.common.BaseEntity;
import com.campuscatalyst.domain.common.Status;
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
 * ResearchJob represents a background research task that gathers information
 * about topics, trends, and keywords relevant to a campaign.
 *
 * Research jobs can query multiple sources (search engines, databases)
 * and produce topic clusters and keywords as outputs.
 */
@Entity
@Table(name = "research_jobs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResearchJob extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.PENDING;

    @Column(name = "queries", columnDefinition = "TEXT")
    private String queries; // JSON array of search queries

    @Column(name = "sources", columnDefinition = "TEXT")
    private String sources; // JSON array of data sources used

    @Column(name = "outputs", columnDefinition = "TEXT")
    private String outputs; // JSON summary of research outputs

    @Column(name = "error_message")
    private String errorMessage;
}
