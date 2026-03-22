package com.campuscatalyst.domain.analytics;

import com.campuscatalyst.domain.common.BaseEntity;
import com.campuscatalyst.domain.common.Platform;
import com.campuscatalyst.domain.publishing.Publication;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

/**
 * MetricSnapshot captures analytics data for a publication at a point in time.
 *
 * Metrics are collected periodically from platform APIs and stored
 * for trend analysis and reporting.
 */
@Entity
@Table(name = "metric_snapshots")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricSnapshot extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id", nullable = false)
    private Publication publication;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "platform", nullable = false)
    private Platform platform;

    @NotBlank
    @Column(name = "metric_key", nullable = false)
    private String metricKey; // e.g., "views", "likes", "shares", "comments"

    @NotNull
    @Column(name = "metric_value", nullable = false)
    private Double metricValue;

    @NotNull
    @Column(name = "recorded_at", nullable = false)
    private Instant timestamp;

    @Column(name = "dimensions", columnDefinition = "TEXT")
    private String dimensions; // JSON for additional breakdown (e.g., by country, device)
}
