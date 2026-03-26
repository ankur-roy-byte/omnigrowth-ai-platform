package com.campuscatalyst.app.tenant;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect that enforces tenant context for @TenantScoped methods.
 */
@Slf4j
@Aspect
@Component
public class TenantAspect {

    @Around("@annotation(TenantScoped) || @within(TenantScoped)")
    public Object enforceTenantContext(ProceedingJoinPoint joinPoint) throws Throwable {
        if (TenantContext.getTenantId().isEmpty()) {
            log.error("Tenant context required but not available for: {}", joinPoint.getSignature());
            throw new TenantRequiredException(
                "Tenant context required for " + joinPoint.getSignature().getName()
            );
        }

        log.debug("Tenant context validated for: {} with tenant: {}",
                joinPoint.getSignature().getName(),
                TenantContext.getTenantId().orElse(null));

        return joinPoint.proceed();
    }
}
