package com.campuscatalyst.domain.reporting;

/**
 * Status of report generation.
 */
public enum ReportStatus {
    /**
     * Report is being generated
     */
    GENERATING,

    /**
     * Report generated successfully
     */
    COMPLETED,

    /**
     * Report generation failed
     */
    FAILED
}
