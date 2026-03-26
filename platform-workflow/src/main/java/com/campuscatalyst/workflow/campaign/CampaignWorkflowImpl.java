package com.campuscatalyst.workflow.campaign;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

/**
 * Implementation of the campaign workflow.
 *
 * Uses durable timers and retry policies for fault-tolerant execution.
 */
@Slf4j
public class CampaignWorkflowImpl implements CampaignWorkflow {

    private static final Duration METRICS_COLLECTION_DELAY = Duration.ofHours(24);

    private final CampaignActivities activities;

    public CampaignWorkflowImpl() {
        ActivityOptions options = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofMinutes(30))
                .setRetryOptions(RetryOptions.newBuilder()
                        .setInitialInterval(Duration.ofSeconds(1))
                        .setMaximumInterval(Duration.ofMinutes(5))
                        .setBackoffCoefficient(2.0)
                        .setMaximumAttempts(5)
                        .build())
                .build();

        this.activities = Workflow.newActivityStub(CampaignActivities.class, options);
    }

    @Override
    public void executeCampaign(UUID campaignId, UUID tenantId) {
        log.info("Starting campaign workflow for campaign: {} tenant: {}", campaignId, tenantId);

        // Phase 1: Research
        log.info("Phase 1: Running research");
        UUID researchJobId = activities.runResearch(campaignId);

        // Phase 2: Content Planning
        log.info("Phase 2: Generating content plan");
        UUID contentPlanId = activities.generateContentPlan(campaignId, researchJobId);

        // Phase 3: Video Generation
        log.info("Phase 3: Generating videos");
        List<UUID> videoAssetIds = activities.generateVideos(campaignId, contentPlanId);

        // Phase 4: Publishing
        log.info("Phase 4: Publishing to platforms");
        List<UUID> publicationIds = activities.publishToPlatforms(campaignId, videoAssetIds);

        // Phase 5: Wait then collect metrics (durable timer)
        log.info("Phase 5: Waiting {} before collecting metrics", METRICS_COLLECTION_DELAY);
        Workflow.sleep(METRICS_COLLECTION_DELAY);

        // Phase 6: Collect Metrics
        log.info("Phase 6: Collecting metrics");
        activities.collectMetrics(campaignId, publicationIds);

        // Phase 7: Generate Report
        log.info("Phase 7: Generating report");
        UUID reportId = activities.generateReport(campaignId);

        log.info("Campaign workflow completed. Report: {}", reportId);
    }
}
