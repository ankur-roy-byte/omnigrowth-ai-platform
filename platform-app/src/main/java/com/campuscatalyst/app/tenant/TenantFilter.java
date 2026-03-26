package com.campuscatalyst.app.tenant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Filter that extracts tenant ID from request and sets TenantContext.
 * Supports header-based tenant identification for API requests.
 */
@Slf4j
@Component
@Order(1)
public class TenantFilter extends OncePerRequestFilter {

    public static final String TENANT_HEADER = "X-Tenant-ID";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            String tenantIdHeader = request.getHeader(TENANT_HEADER);

            if (tenantIdHeader != null && !tenantIdHeader.isBlank()) {
                try {
                    UUID tenantId = UUID.fromString(tenantIdHeader.trim());
                    TenantContext.setTenantId(tenantId);
                    log.debug("Tenant context set: {}", tenantId);
                } catch (IllegalArgumentException e) {
                    log.warn("Invalid tenant ID format in header: {}", tenantIdHeader);
                }
            }

            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        // Skip tenant filtering for health and actuator endpoints
        return path.startsWith("/actuator") ||
               path.equals("/api/v1/health") ||
               path.startsWith("/error");
    }
}
