package com.campuscatalyst.reporting.model;

import com.campuscatalyst.domain.common.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Campaign report data structure containing aggregated analytics.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignReportData {

    private UUID campaignId;
    private String campaignName;
    private Instant timeRangeStart;
    private Instant timeRangeEnd;
    private Instant generatedAt;

    /**
     * Summary KPIs
     */
    private SummaryKPIs summaryKpis;

    /**
     * Top performing posts
     */
    private List<PostPerformance> topPosts;

    /**
     * Platform breakdown
     */
    private Map<Platform, PlatformMetrics> platformBreakdown;

    /**
     * Keyword/topic coverage vs engagement correlation
     */
    private List<TopicEngagement> topicEngagement;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SummaryKPIs {
        private long totalViews;
        private long totalLikes;
        private long totalComments;
        private long totalShares;
        private double averageEngagementRate;
        private int totalPublications;
        private int successfulPublications;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostPerformance {
        private UUID publicationId;
        private Platform platform;
        private String postUrl;
        private long views;
        private long likes;
        private double engagementRate;
        private Instant publishedAt;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlatformMetrics {
        private Platform platform;
        private long totalViews;
        private long totalLikes;
        private long totalComments;
        private long totalShares;
        private int postCount;
        private double averageEngagementRate;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopicEngagement {
        private String topicLabel;
        private int postsCovering;
        private double averageEngagementRate;
        private long totalViews;
    }
}
