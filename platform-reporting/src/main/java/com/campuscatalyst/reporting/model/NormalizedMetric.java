package com.campuscatalyst.reporting.model;

import com.campuscatalyst.domain.common.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

/**
 * Normalized metric record for unified analytics across platforms.
 *
 * Schema: {platform, metricKey, metricValue, timestamp, dimensions}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NormalizedMetric {

    /**
     * Source platform
     */
    private Platform platform;

    /**
     * Metric identifier (e.g., "views", "likes", "engagement_rate")
     */
    private String metricKey;

    /**
     * Metric value
     */
    private Double metricValue;

    /**
     * When the metric was recorded
     */
    private Instant timestamp;

    /**
     * Optional dimensional breakdown (e.g., by country, device, age group)
     */
    private Map<String, String> dimensions;
}
