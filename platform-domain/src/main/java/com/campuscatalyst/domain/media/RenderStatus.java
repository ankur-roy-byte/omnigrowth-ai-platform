package com.campuscatalyst.domain.media;

/**
 * Status of video rendering process.
 */
public enum RenderStatus {
    /**
     * Render job not yet submitted
     */
    PENDING,

    /**
     * Render job submitted and queued
     */
    QUEUED,

    /**
     * Video is being rendered
     */
    RENDERING,

    /**
     * Render completed successfully
     */
    COMPLETED,

    /**
     * Render failed
     */
    FAILED
}
