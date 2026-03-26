package com.campuscatalyst.connector.tiktok;

import com.campuscatalyst.connectors.api.PublisherConnector;
import com.campuscatalyst.connectors.model.PlatformCapability;
import com.campuscatalyst.connectors.model.PublicationResult;
import com.campuscatalyst.connectors.model.PublishRequest;
import com.campuscatalyst.domain.common.Platform;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.Extension;

import java.time.Instant;
import java.util.Set;

/**
 * TikTok publisher connector implementation.
 *
 * Supports two posting modes per TikTok's Content Posting API:
 * - Direct Post: Immediately publishes the video
 * - Upload as Draft: Uploads video for user to review before publishing
 *
 * Use request.asDraft = true for draft mode.
 */
@Slf4j
@Extension
public class TikTokPublisherConnector implements PublisherConnector {

    private static final Set<PlatformCapability> SUPPORTED_CAPABILITIES = Set.of(
            PlatformCapability.VIDEO_PUBLISH,
            PlatformCapability.DRAFT_MODE,
            PlatformCapability.PERSONAL_POSTING
    );

    @Override
    public PublicationResult publishVideo(PublishRequest request) {
        if (request.isAsDraft()) {
            return uploadAsDraft(request);
        } else {
            return directPost(request);
        }
    }

    private PublicationResult directPost(PublishRequest request) {
        log.info("Direct posting video to TikTok: {}", request.getTitle());

        try {
            // In a real implementation:
            // 1. Initialize video upload
            // 2. Upload video chunks
            // 3. Create post with video_id

            // Placeholder
            String postId = "tiktok_" + System.currentTimeMillis();

            return PublicationResult.builder()
                    .success(true)
                    .platform(Platform.TIKTOK)
                    .postId(postId)
                    .postUrl("https://tiktok.com/@user/video/" + postId)
                    .publishedAt(Instant.now())
                    .build();
        } catch (Exception e) {
            log.error("Failed to direct post to TikTok", e);
            return PublicationResult.builder()
                    .success(false)
                    .platform(Platform.TIKTOK)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    private PublicationResult uploadAsDraft(PublishRequest request) {
        log.info("Uploading video as draft to TikTok: {}", request.getTitle());

        try {
            // In a real implementation:
            // 1. Use draft upload endpoint
            // 2. Upload video
            // 3. Return draft reference ID for user to publish manually

            // Placeholder
            String draftId = "draft_" + System.currentTimeMillis();

            return PublicationResult.builder()
                    .success(true)
                    .platform(Platform.TIKTOK)
                    .postId(draftId)
                    .postUrl(null) // Draft doesn't have a public URL
                    .publishedAt(null) // Not published yet
                    .build();
        } catch (Exception e) {
            log.error("Failed to upload draft to TikTok", e);
            return PublicationResult.builder()
                    .success(false)
                    .platform(Platform.TIKTOK)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    @Override
    public PublicationResult publishPost(PublishRequest request) {
        // TikTok doesn't support text-only posts
        return PublicationResult.builder()
                .success(false)
                .platform(Platform.TIKTOK)
                .errorMessage("TikTok does not support text-only posts")
                .build();
    }

    @Override
    public boolean supports(PlatformCapability capability) {
        return SUPPORTED_CAPABILITIES.contains(capability);
    }

    @Override
    public Set<PlatformCapability> getSupportedCapabilities() {
        return SUPPORTED_CAPABILITIES;
    }

    @Override
    public Platform getPlatform() {
        return Platform.TIKTOK;
    }

    @Override
    public String getConnectorId() {
        return "tiktok-publisher";
    }
}
