package com.campuscatalyst.connectors.model;

/**
 * Platform capabilities that connectors can support.
 */
public enum PlatformCapability {
    /**
     * Publish video content
     */
    VIDEO_PUBLISH,

    /**
     * Publish text posts
     */
    TEXT_PUBLISH,

    /**
     * Publish image posts
     */
    IMAGE_PUBLISH,

    /**
     * Schedule posts for future publishing
     */
    SCHEDULING,

    /**
     * Save content as draft
     */
    DRAFT_MODE,

    /**
     * Publish to organization/page account
     */
    ORGANIZATION_POSTING,

    /**
     * Publish to personal account
     */
    PERSONAL_POSTING,

    /**
     * Fetch analytics/metrics
     */
    ANALYTICS
}
