package com.campuscatalyst.connectors.exception;

/**
 * Thrown when connector authentication fails.
 * Common causes: expired tokens, revoked permissions, invalid credentials.
 */
public class ConnectorAuthException extends ConnectorException {

    private final boolean tokenExpired;

    public ConnectorAuthException(String connectorId, String message) {
        super(connectorId, message);
        this.tokenExpired = false;
    }

    public ConnectorAuthException(String connectorId, String message, boolean tokenExpired) {
        super(connectorId, message);
        this.tokenExpired = tokenExpired;
    }

    public ConnectorAuthException(String connectorId, String message, Throwable cause) {
        super(connectorId, message, cause);
        this.tokenExpired = false;
    }

    public boolean isTokenExpired() {
        return tokenExpired;
    }
}
