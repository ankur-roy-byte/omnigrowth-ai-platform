package com.campuscatalyst.domain.tenant;

/**
 * User roles within the platform.
 */
public enum UserRole {
    /**
     * Full administrative access to tenant configuration
     */
    ADMIN,

    /**
     * Can create and manage campaigns, content, and publishing
     */
    MANAGER,

    /**
     * Can create and edit content, but cannot publish
     */
    EDITOR,

    /**
     * Read-only access to reports and analytics
     */
    VIEWER
}
