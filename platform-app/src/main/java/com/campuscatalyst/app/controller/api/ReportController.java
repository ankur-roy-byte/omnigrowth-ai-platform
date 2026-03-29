package com.campuscatalyst.app.controller.api;

import com.campuscatalyst.reporting.model.CampaignReportData;
import com.campuscatalyst.reporting.model.NormalizedMetric;
import com.campuscatalyst.reporting.service.ReportExportService;
import com.campuscatalyst.reporting.service.ReportGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * REST API for generating and downloading campaign reports.
 */
@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportGeneratorService reportGeneratorService;
    private final ReportExportService reportExportService;

    @GetMapping("/campaigns/{campaignId}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<CampaignReportData> generateReport(
            @PathVariable UUID campaignId,
            @RequestParam(defaultValue = "30") int days) {

        Instant endTime = Instant.now();
        Instant startTime = endTime.minus(days, ChronoUnit.DAYS);

        // In production, fetch actual metrics from database
        List<NormalizedMetric> metrics = new ArrayList<>();

        CampaignReportData report = reportGeneratorService.generateReport(
                campaignId,
                "Campaign " + campaignId,
                startTime,
                endTime,
                metrics
        );

        return ResponseEntity.ok(report);
    }

    @GetMapping("/campaigns/{campaignId}/download")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<String> downloadReport(
            @PathVariable UUID campaignId,
            @RequestParam(defaultValue = "json") String format,
            @RequestParam(defaultValue = "30") int days) {

        Instant endTime = Instant.now();
        Instant startTime = endTime.minus(days, ChronoUnit.DAYS);

        // In production, fetch actual metrics from database
        List<NormalizedMetric> metrics = new ArrayList<>();

        CampaignReportData report = reportGeneratorService.generateReport(
                campaignId,
                "Campaign " + campaignId,
                startTime,
                endTime,
                metrics
        );

        String content;
        String contentType;
        String filename;

        if ("csv".equalsIgnoreCase(format)) {
            content = reportExportService.exportAsCsv(report);
            contentType = "text/csv";
            filename = "campaign-report-" + campaignId + ".csv";
        } else {
            content = reportExportService.exportAsJson(report);
            contentType = MediaType.APPLICATION_JSON_VALUE;
            filename = "campaign-report-" + campaignId + ".json";
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(content);
    }
}
