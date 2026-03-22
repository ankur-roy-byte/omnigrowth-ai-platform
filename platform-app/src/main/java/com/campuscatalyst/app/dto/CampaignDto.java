package com.campuscatalyst.app.dto;

import com.campuscatalyst.domain.common.Platform;
import com.campuscatalyst.domain.common.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/**
 * Data Transfer Object for Campaign entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDto {

    private UUID id;

    private UUID tenantId;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;

    private String description;

    @Size(max = 100)
    private String domainFocus;

    private String goals;

    private Set<Platform> platformsEnabled;

    private Status status;

    private Instant scheduleStart;
    private Instant scheduleEnd;
    private String scheduleCron;

    private Instant createdAt;
    private Instant updatedAt;
}
