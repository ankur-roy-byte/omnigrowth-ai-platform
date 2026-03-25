package com.campuscatalyst.reporting.service;

import com.campuscatalyst.domain.common.Platform;
import com.campuscatalyst.reporting.model.CampaignReportData;
import com.campuscatalyst.reporting.model.NormalizedMetric;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Service for generating campaign reports.
 *
 * Aggregates metrics from all publications and platforms,
 * calculates KPIs, and compiles comprehensive reports.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReportGeneratorService {

    /**
     * Generate a campaign report covering the specified time range.
     */
    public CampaignReportData generateReport(UUID campaignId, String campaignName,
                                              Instant startTime, Instant endTime,
                                              List<NormalizedMetric> metrics) {
        log.info("Generating report for campaign: {} from {} to {}", campaignId, startTime, endTime);

        // Calculate summary KPIs
        CampaignReportData.SummaryKPIs summaryKpis = calculateSummaryKPIs(metrics);

        // Group by platform
        Map<Platform, CampaignReportData.PlatformMetrics> platformBreakdown =
                calculatePlatformBreakdown(metrics);

        // Find top performing posts (placeholder - needs publication data)
        List<CampaignReportData.PostPerformance> topPosts = new ArrayList<>();

        // Calculate topic engagement (placeholder - needs topic data)
        List<CampaignReportData.TopicEngagement> topicEngagement = new ArrayList<>();

        return CampaignReportData.builder()
                .campaignId(campaignId)
                .campaignName(campaignName)
                .timeRangeStart(startTime)
                .timeRangeEnd(endTime)
                .generatedAt(Instant.now())
                .summaryKpis(summaryKpis)
                .topPosts(topPosts)
                .platformBreakdown(platformBreakdown)
                .topicEngagement(topicEngagement)
                .build();
    }

    private CampaignReportData.SummaryKPIs calculateSummaryKPIs(List<NormalizedMetric> metrics) {
        long totalViews = sumMetric(metrics, "views");
        long totalLikes = sumMetric(metrics, "likes");
        long totalComments = sumMetric(metrics, "comments");
        long totalShares = sumMetric(metrics, "shares");

        double engagementRate = totalViews > 0 ?
                (double) (totalLikes + totalComments + totalShares) / totalViews * 100 : 0;

        return CampaignReportData.SummaryKPIs.builder()
                .totalViews(totalViews)
                .totalLikes(totalLikes)
                .totalComments(totalComments)
                .totalShares(totalShares)
                .averageEngagementRate(engagementRate)
                .totalPublications(0) // Would be set from actual publication data
                .successfulPublications(0)
                .build();
    }

    private Map<Platform, CampaignReportData.PlatformMetrics> calculatePlatformBreakdown(
            List<NormalizedMetric> metrics) {

        Map<Platform, CampaignReportData.PlatformMetrics> breakdown = new HashMap<>();

        for (Platform platform : Platform.values()) {
            List<NormalizedMetric> platformMetrics = metrics.stream()
                    .filter(m -> m.getPlatform() == platform)
                    .toList();

            if (!platformMetrics.isEmpty()) {
                long views = sumMetric(platformMetrics, "views");
                long likes = sumMetric(platformMetrics, "likes");
                long comments = sumMetric(platformMetrics, "comments");
                long shares = sumMetric(platformMetrics, "shares");

                double engagementRate = views > 0 ?
                        (double) (likes + comments + shares) / views * 100 : 0;

                breakdown.put(platform, CampaignReportData.PlatformMetrics.builder()
                        .platform(platform)
                        .totalViews(views)
                        .totalLikes(likes)
                        .totalComments(comments)
                        .totalShares(shares)
                        .averageEngagementRate(engagementRate)
                        .build());
            }
        }

        return breakdown;
    }

    private long sumMetric(List<NormalizedMetric> metrics, String metricKey) {
        return metrics.stream()
                .filter(m -> metricKey.equals(m.getMetricKey()))
                .mapToLong(m -> m.getMetricValue().longValue())
                .sum();
    }
}
