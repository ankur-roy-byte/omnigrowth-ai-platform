package com.campuscatalyst.app.dto;

import com.campuscatalyst.domain.common.DomainType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * Data Transfer Object for Tenant entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TenantDto {

    private UUID id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    private DomainType domainType;

    @Size(max = 50)
    private String timezone;

    private String settings;

    private boolean active;

    private Instant createdAt;
    private Instant updatedAt;
}
