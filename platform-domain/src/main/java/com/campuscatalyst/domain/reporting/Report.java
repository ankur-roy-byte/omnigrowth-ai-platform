package com.campuscatalyst.domain.reporting;

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

import java.time.Instant;

/**
 * Report represents a generated analytics report for a campaign.
 *
 * Reports aggregate metrics across publications and time periods,
 * providing insights and KPIs for campaign performance.
 */
@Entity
@Table(name = "reports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id", nullable = false)
    private Campaign campaign;

    @Column(name = "title")
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false)
    @Builder.Default
    private ReportType reportType = ReportType.CAMPAIGN_SUMMARY;

    @NotNull
    @Column(name = "time_range_start", nullable = false)
    private Instant timeRangeStart;

    @NotNull
    @Column(name = "time_range_end", nullable = false)
    private Instant timeRangeEnd;

    @Column(name = "storage_url")
    private String storageUrl; // URL to generated report file (PDF, etc.)

    @Column(name = "report_data", columnDefinition = "TEXT")
    private String reportData; // JSON report content

    @NotNull
    @Column(name = "generated_at", nullable = false)
    private Instant generatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private ReportStatus status = ReportStatus.GENERATING;
}
