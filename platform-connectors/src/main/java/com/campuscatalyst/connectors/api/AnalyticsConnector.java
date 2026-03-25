package com.campuscatalyst.connectors.api;

import com.campuscatalyst.connectors.model.MetricsRequest;
import com.campuscatalyst.domain.analytics.MetricSnapshot;
import com.campuscatalyst.domain.common.Platform;
import org.pf4j.ExtensionPoint;

import java.util.List;
import java.util.Set;

/**
 * Extension point for analytics and metrics connectors.
 *
 * Analytics connectors handle:
 * - Fetching engagement metrics from social platforms
 * - Supporting various metric types per platform
 */
public interface AnalyticsConnector extends ExtensionPoint {

    /**
     * Fetch metrics for published content.
     *
     * @param request the metrics request with post ID and date range
     * @return list of metric snapshots
     */
    List<MetricSnapshot> fetchMetrics(MetricsRequest request);

    /**
     * Check if this connector supports a specific metric key.
     *
     * @param metricKey the metric key (e.g., "views", "likes")
     * @return true if supported
     */
    boolean supportsMetric(String metricKey);

    /**
     * Get all supported metric keys.
     *
     * @return set of supported metric keys
     */
    Set<String> getSupportedMetrics();

    /**
     * Get the target platform.
     *
     * @return the platform this connector fetches metrics from
     */
    Platform getPlatform();

    /**
     * Get the connector identifier.
     *
     * @return connector ID
     */
    String getConnectorId();
}
