package com.campuscatalyst.domain.tenant;

import com.campuscatalyst.domain.common.BaseEntity;
import com.campuscatalyst.domain.common.DomainType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Tenant entity representing an organization (e.g., a college/university)
 * using the platform.
 *
 * Each tenant operates in isolation with their own:
 * - Users and roles
 * - Campaigns and content
 * - Connected social media accounts
 * - Analytics data
 */
@Entity
@Table(name = "tenants")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tenant extends BaseEntity {

    @NotBlank
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "domain_type", nullable = false)
    @Builder.Default
    private DomainType domainType = DomainType.EDUCATION;

    @Size(max = 50)
    @Column(name = "timezone")
    @Builder.Default
    private String timezone = "UTC";

    @Column(name = "settings", columnDefinition = "TEXT")
    private String settings; // JSON string for flexible configuration

    @Column(name = "active")
    @Builder.Default
    private boolean active = true;
}
