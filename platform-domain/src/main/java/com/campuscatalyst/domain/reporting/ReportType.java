package com.campuscatalyst.domain.reporting;

/**
 * Types of reports that can be generated.
 */
public enum ReportType {
    /**
     * Overall campaign performance summary
     */
    CAMPAIGN_SUMMARY,

    /**
     * Detailed platform-specific metrics
     */
    PLATFORM_BREAKDOWN,

    /**
     * Content performance analysis
     */
    CONTENT_ANALYSIS,

    /**
     * Audience engagement report
     */
    ENGAGEMENT,

    /**
     * Comparison across campaigns
     */
    COMPARATIVE,

    /**
     * Weekly/monthly scheduled report
     */
    SCHEDULED
}
