package com.campuscatalyst.connectors.model;

import com.campuscatalyst.domain.common.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * Request for fetching metrics.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricsRequest {

    private Platform platform;
    private String postId;
    private String accessToken;
    private Instant startDate;
    private Instant endDate;
    private List<String> metricKeys;
}
