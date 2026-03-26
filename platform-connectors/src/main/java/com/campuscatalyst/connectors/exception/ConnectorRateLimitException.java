package com.campuscatalyst.connectors.exception;

import java.time.Duration;
import java.util.Optional;

/**
 * Thrown when connector hits rate limits (HTTP 429).
 * Contains retry information if provided by the API.
 */
public class ConnectorRateLimitException extends ConnectorException {

    private final Duration retryAfter;
    private final int remainingQuota;

    public ConnectorRateLimitException(String connectorId, String message) {
        super(connectorId, message);
        this.retryAfter = null;
        this.remainingQuota = 0;
    }

    public ConnectorRateLimitException(String connectorId, String message, Duration retryAfter) {
        super(connectorId, message);
        this.retryAfter = retryAfter;
        this.remainingQuota = 0;
    }

    public ConnectorRateLimitException(String connectorId, String message, Duration retryAfter, int remainingQuota) {
        super(connectorId, message);
        this.retryAfter = retryAfter;
        this.remainingQuota = remainingQuota;
    }

    public Optional<Duration> getRetryAfter() {
        return Optional.ofNullable(retryAfter);
    }

    public int getRemainingQuota() {
        return remainingQuota;
    }
}
