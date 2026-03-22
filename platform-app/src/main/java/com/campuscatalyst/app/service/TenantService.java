package com.campuscatalyst.app.service;

import com.campuscatalyst.domain.tenant.Tenant;
import com.campuscatalyst.domain.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service for managing tenants (organizations using the platform).
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TenantService {

    private final TenantRepository tenantRepository;

    public List<Tenant> findAll() {
        return tenantRepository.findAll();
    }

    public Optional<Tenant> findById(UUID id) {
        return tenantRepository.findById(id);
    }

    public Optional<Tenant> findByName(String name) {
        return tenantRepository.findByName(name);
    }

    @Transactional
    public Tenant create(Tenant tenant) {
        if (tenantRepository.existsByName(tenant.getName())) {
            throw new IllegalArgumentException("Tenant with name '" + tenant.getName() + "' already exists");
        }
        return tenantRepository.save(tenant);
    }

    @Transactional
    public Tenant update(UUID id, Tenant updates) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found: " + id));

        if (updates.getName() != null) {
            tenant.setName(updates.getName());
        }
        if (updates.getDomainType() != null) {
            tenant.setDomainType(updates.getDomainType());
        }
        if (updates.getTimezone() != null) {
            tenant.setTimezone(updates.getTimezone());
        }
        if (updates.getSettings() != null) {
            tenant.setSettings(updates.getSettings());
        }

        return tenantRepository.save(tenant);
    }

    @Transactional
    public void delete(UUID id) {
        if (!tenantRepository.existsById(id)) {
            throw new IllegalArgumentException("Tenant not found: " + id);
        }
        tenantRepository.deleteById(id);
    }

    @Transactional
    public Tenant setActive(UUID id, boolean active) {
        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tenant not found: " + id));
        tenant.setActive(active);
        return tenantRepository.save(tenant);
    }
}
