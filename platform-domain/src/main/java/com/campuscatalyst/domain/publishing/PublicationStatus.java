package com.campuscatalyst.domain.publishing;

/**
 * Status of a publication.
 */
public enum PublicationStatus {
    /**
     * Scheduled for future publishing
     */
    SCHEDULED,

    /**
     * Currently being published
     */
    PUBLISHING,

    /**
     * Successfully published
     */
    PUBLISHED,

    /**
     * Publication failed
     */
    FAILED,

    /**
     * Publication cancelled
     */
    CANCELLED,

    /**
     * Saved as draft on the platform
     */
    DRAFT
}
