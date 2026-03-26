package com.campuscatalyst.connectors.exception;

/**
 * Thrown for transient errors that may succeed on retry.
 * Examples: network timeouts, temporary service unavailability.
 */
public class ConnectorTransientException extends ConnectorException {

    private final boolean retryable;

    public ConnectorTransientException(String connectorId, String message) {
        super(connectorId, message);
        this.retryable = true;
    }

    public ConnectorTransientException(String connectorId, String message, Throwable cause) {
        super(connectorId, message, cause);
        this.retryable = true;
    }

    public ConnectorTransientException(String connectorId, String message, boolean retryable) {
        super(connectorId, message);
        this.retryable = retryable;
    }

    public boolean isRetryable() {
        return retryable;
    }
}
