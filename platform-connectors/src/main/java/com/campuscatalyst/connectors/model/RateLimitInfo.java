package com.campuscatalyst.connectors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Rate limit information from a provider.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RateLimitInfo {

    private int remainingRequests;
    private int maxRequests;
    private Instant resetTime;
    private boolean limited;
}
