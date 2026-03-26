package com.campuscatalyst.workflow.campaign;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.util.List;
import java.util.UUID;

/**
 * Campaign activities interface defining individual workflow steps.
 */
@ActivityInterface
public interface CampaignActivities {

    /**
     * Run research to discover topics and keywords.
     */
    @ActivityMethod
    UUID runResearch(UUID campaignId);

    /**
     * Generate content plan based on research results.
     */
    @ActivityMethod
    UUID generateContentPlan(UUID campaignId, UUID researchJobId);

    /**
     * Generate video content from templates.
     */
    @ActivityMethod
    List<UUID> generateVideos(UUID campaignId, UUID contentPlanId);

    /**
     * Publish content to enabled platforms.
     */
    @ActivityMethod
    List<UUID> publishToPlatforms(UUID campaignId, List<UUID> videoAssetIds);

    /**
     * Collect metrics from platforms.
     */
    @ActivityMethod
    void collectMetrics(UUID campaignId, List<UUID> publicationIds);

    /**
     * Generate campaign report.
     */
    @ActivityMethod
    UUID generateReport(UUID campaignId);
}
