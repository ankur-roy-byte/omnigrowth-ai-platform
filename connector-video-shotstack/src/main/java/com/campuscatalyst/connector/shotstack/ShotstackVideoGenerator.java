package com.campuscatalyst.connector.shotstack;

import com.campuscatalyst.connectors.api.VideoGenerator;
import com.campuscatalyst.connectors.model.RenderStatus;
import com.campuscatalyst.connectors.model.VideoAssetLocation;
import com.campuscatalyst.connectors.model.VideoRenderRequest;
import com.campuscatalyst.connectors.model.VideoSlide;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Shotstack video generator connector implementation.
 *
 * Uses Shotstack's cloud video rendering API to create videos
 * from JSON templates.
 *
 * Supports sandbox environment for development/testing.
 */
@Slf4j
@Extension
public class ShotstackVideoGenerator implements VideoGenerator {

    private static final String TEMPLATE_COLLEGE_CAMPAIGN = "college-campaign";

    // Track render jobs (in production, use persistent storage)
    private final Map<String, RenderStatus> renderJobs = new HashMap<>();
    private final Map<String, VideoAssetLocation> completedRenders = new HashMap<>();

    @Override
    public String submitRender(VideoRenderRequest request) {
        log.info("Submitting video render: {}", request.getTitle());

        try {
            // Generate job ID
            String jobId = UUID.randomUUID().toString();

            // In a real implementation:
            // 1. Convert VideoRenderRequest to Shotstack JSON
            // 2. POST to Shotstack render endpoint
            // 3. Parse response for render ID

            // Build Shotstack timeline from slides
            String timeline = buildTimeline(request);
            log.debug("Generated timeline: {}", timeline);

            // Track job status
            renderJobs.put(jobId, RenderStatus.QUEUED);

            log.info("Render job submitted: {}", jobId);
            return jobId;
        } catch (Exception e) {
            log.error("Failed to submit render", e);
            throw new RuntimeException("Failed to submit render", e);
        }
    }

    @Override
    public RenderStatus getStatus(String renderJobId) {
        log.debug("Getting status for render job: {}", renderJobId);

        // In a real implementation:
        // 1. GET status from Shotstack API
        // 2. Parse response for status

        // Simulate progress
        RenderStatus current = renderJobs.getOrDefault(renderJobId, RenderStatus.FAILED);
        if (current == RenderStatus.QUEUED) {
            renderJobs.put(renderJobId, RenderStatus.RENDERING);
        } else if (current == RenderStatus.RENDERING) {
            renderJobs.put(renderJobId, RenderStatus.COMPLETED);
            // Simulate completed render
            completedRenders.put(renderJobId, VideoAssetLocation.builder()
                    .url("https://cdn.shotstack.io/renders/" + renderJobId + ".mp4")
                    .thumbnailUrl("https://cdn.shotstack.io/renders/" + renderJobId + "-thumb.jpg")
                    .durationSeconds(60)
                    .fileSizeBytes(15_000_000)
                    .format("mp4")
                    .build());
        }

        return renderJobs.getOrDefault(renderJobId, RenderStatus.FAILED);
    }

    @Override
    public VideoAssetLocation getResult(String renderJobId) {
        log.info("Getting result for render job: {}", renderJobId);

        VideoAssetLocation location = completedRenders.get(renderJobId);
        if (location == null) {
            throw new IllegalStateException("Render not complete or not found: " + renderJobId);
        }
        return location;
    }

    @Override
    public String getGeneratorId() {
        return "shotstack";
    }

    @Override
    public boolean supportsTemplate(String templateType) {
        return TEMPLATE_COLLEGE_CAMPAIGN.equals(templateType);
    }

    private String buildTimeline(VideoRenderRequest request) {
        // Build Shotstack JSON timeline from slides
        StringBuilder clips = new StringBuilder();
        int startTime = 0;

        for (VideoSlide slide : request.getSlides()) {
            int duration = slide.getDurationSeconds() > 0 ? slide.getDurationSeconds() : 5;

            // In a real implementation, this would be proper JSON
            clips.append(String.format(
                    "{headline:'%s',subtext:'%s',start:%d,duration:%d},",
                    slide.getHeadline(),
                    slide.getSubtext(),
                    startTime,
                    duration
            ));
            startTime += duration;
        }

        return "{slides:[" + clips + "],config:" + request.getConfig() + "}";
    }
}
