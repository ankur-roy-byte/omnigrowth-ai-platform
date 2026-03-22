package com.campuscatalyst.domain.tenant;

import com.campuscatalyst.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity representing a person who can access the platform.
 *
 * Users belong to a tenant and have roles that determine their permissions.
 * OAuth identities allow linking external identity providers.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @Column(name = "display_name")
    private String displayName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<UserRole> roles = new HashSet<>();

    @Column(name = "oauth_identities", columnDefinition = "TEXT")
    private String oauthIdentities; // JSON: provider -> identity mapping

    @Column(name = "active")
    @Builder.Default
    private boolean active = true;
}
