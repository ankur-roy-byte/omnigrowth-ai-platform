package com.campuscatalyst.app.tenant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown when tenant context is required but not available.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TenantRequiredException extends RuntimeException {

    public TenantRequiredException(String message) {
        super(message);
    }
}
