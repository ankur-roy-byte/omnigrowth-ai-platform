package com.campuscatalyst.reporting.service;

import com.campuscatalyst.reporting.model.CampaignReportData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service for exporting reports to various formats.
 */
@Slf4j
@Service
public class ReportExportService {

    private final ObjectMapper objectMapper;

    public ReportExportService() {
        this.objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Export report as JSON string.
     */
    public String exportAsJson(CampaignReportData report) {
        try {
            return objectMapper.writeValueAsString(report);
        } catch (Exception e) {
            log.error("Failed to export report as JSON", e);
            throw new RuntimeException("Failed to export report", e);
        }
    }

    /**
     * Export report as CSV (summary only).
     */
    public String exportAsCsv(CampaignReportData report) {
        StringBuilder csv = new StringBuilder();

        // Header
        csv.append("Campaign Report: ").append(report.getCampaignName()).append("\n");
        csv.append("Generated: ").append(report.getGeneratedAt()).append("\n");
        csv.append("Period: ").append(report.getTimeRangeStart())
                .append(" to ").append(report.getTimeRangeEnd()).append("\n");
        csv.append("\n");

        // Summary KPIs
        csv.append("Summary KPIs\n");
        csv.append("Metric,Value\n");
        if (report.getSummaryKpis() != null) {
            csv.append("Total Views,").append(report.getSummaryKpis().getTotalViews()).append("\n");
            csv.append("Total Likes,").append(report.getSummaryKpis().getTotalLikes()).append("\n");
            csv.append("Total Comments,").append(report.getSummaryKpis().getTotalComments()).append("\n");
            csv.append("Total Shares,").append(report.getSummaryKpis().getTotalShares()).append("\n");
            csv.append("Engagement Rate,")
                    .append(String.format("%.2f%%", report.getSummaryKpis().getAverageEngagementRate()))
                    .append("\n");
        }

        // Platform breakdown
        csv.append("\nPlatform Breakdown\n");
        csv.append("Platform,Views,Likes,Comments,Shares,Engagement Rate\n");
        if (report.getPlatformBreakdown() != null) {
            report.getPlatformBreakdown().forEach((platform, metrics) -> {
                csv.append(platform.name()).append(",")
                        .append(metrics.getTotalViews()).append(",")
                        .append(metrics.getTotalLikes()).append(",")
                        .append(metrics.getTotalComments()).append(",")
                        .append(metrics.getTotalShares()).append(",")
                        .append(String.format("%.2f%%", metrics.getAverageEngagementRate()))
                        .append("\n");
            });
        }

        return csv.toString();
    }
}
