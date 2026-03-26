package com.campuscatalyst.connectors.exception;

/**
 * Thrown for fatal errors that will not succeed on retry.
 * Examples: invalid input, permission denied, resource not found.
 */
public class ConnectorFatalException extends ConnectorException {

    private final String errorCode;

    public ConnectorFatalException(String connectorId, String message) {
        super(connectorId, message);
        this.errorCode = null;
    }

    public ConnectorFatalException(String connectorId, String message, String errorCode) {
        super(connectorId, message);
        this.errorCode = errorCode;
    }

    public ConnectorFatalException(String connectorId, String message, Throwable cause) {
        super(connectorId, message, cause);
        this.errorCode = null;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
