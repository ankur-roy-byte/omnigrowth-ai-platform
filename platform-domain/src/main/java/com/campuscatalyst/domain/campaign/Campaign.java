package com.campuscatalyst.domain.campaign;

import com.campuscatalyst.domain.common.BaseEntity;
import com.campuscatalyst.domain.common.Platform;
import com.campuscatalyst.domain.common.Status;
import com.campuscatalyst.domain.tenant.Tenant;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Campaign entity representing a marketing campaign for a tenant.
 *
 * A campaign orchestrates the full content lifecycle:
 * 1. Research phase - gather topics and keywords
 * 2. Planning phase - create content plan
 * 3. Production phase - generate content assets and videos
 * 4. Publishing phase - distribute to enabled platforms
 * 5. Analytics phase - collect and report metrics
 */
@Entity
@Table(name = "campaigns")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Size(max = 100)
    @Column(name = "domain_focus")
    private String domainFocus; // e.g., "admissions", "alumni", "research"

    @Column(name = "goals", columnDefinition = "TEXT")
    private String goals; // JSON array of campaign goals

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "campaign_platforms", joinColumns = @JoinColumn(name = "campaign_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    @Builder.Default
    private Set<Platform> platformsEnabled = new HashSet<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.DRAFT;

    @Column(name = "schedule_start")
    private Instant scheduleStart;

    @Column(name = "schedule_end")
    private Instant scheduleEnd;

    @Column(name = "schedule_cron")
    private String scheduleCron; // Cron expression for recurring campaigns
}
