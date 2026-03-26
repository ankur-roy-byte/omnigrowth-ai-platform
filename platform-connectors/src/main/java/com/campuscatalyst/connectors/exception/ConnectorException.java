package com.campuscatalyst.connectors.exception;

/**
 * Base exception for all connector-related errors.
 */
public abstract class ConnectorException extends Exception {

    private final String connectorId;

    protected ConnectorException(String connectorId, String message) {
        super(message);
        this.connectorId = connectorId;
    }

    protected ConnectorException(String connectorId, String message, Throwable cause) {
        super(message, cause);
        this.connectorId = connectorId;
    }

    public String getConnectorId() {
        return connectorId;
    }
}
