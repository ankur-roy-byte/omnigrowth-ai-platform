package com.campuscatalyst.workflow.campaign;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.UUID;

/**
 * Campaign workflow interface defining the full campaign lifecycle.
 *
 * The workflow orchestrates:
 * 1. Research phase - gather topics and keywords
 * 2. Planning phase - create content plan
 * 3. Production phase - generate content and videos
 * 4. Publishing phase - distribute to platforms
 * 5. Analytics phase - collect metrics and generate reports
 */
@WorkflowInterface
public interface CampaignWorkflow {

    /**
     * Execute the campaign workflow.
     *
     * @param campaignId the campaign ID
     * @param tenantId the tenant ID
     */
    @WorkflowMethod
    void executeCampaign(UUID campaignId, UUID tenantId);
}
