package com.campuscatalyst.domain.tenant.repository;

import com.campuscatalyst.domain.tenant.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository for User entity operations.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    List<User> findByTenantId(UUID tenantId);

    boolean existsByEmail(String email);
}
