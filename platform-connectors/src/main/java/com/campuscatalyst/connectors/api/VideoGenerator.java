package com.campuscatalyst.connectors.api;

import com.campuscatalyst.connectors.model.VideoRenderRequest;
import com.campuscatalyst.connectors.model.RenderStatus;
import com.campuscatalyst.connectors.model.VideoAssetLocation;
import org.pf4j.ExtensionPoint;

/**
 * Extension point for video generation services.
 *
 * Video generators handle:
 * - Submitting video render requests to external services
 * - Polling render status
 * - Retrieving completed video assets
 */
public interface VideoGenerator extends ExtensionPoint {

    /**
     * Submit a video render request.
     *
     * @param request the render request with template and content
     * @return render job ID
     */
    String submitRender(VideoRenderRequest request);

    /**
     * Get the status of a render job.
     *
     * @param renderJobId the render job ID
     * @return current render status
     */
    RenderStatus getStatus(String renderJobId);

    /**
     * Get the result of a completed render.
     *
     * @param renderJobId the render job ID
     * @return location of the rendered video asset
     */
    VideoAssetLocation getResult(String renderJobId);

    /**
     * Get the generator identifier.
     *
     * @return generator ID (e.g., "shotstack", "cloudinary")
     */
    String getGeneratorId();

    /**
     * Check if this generator supports a specific template type.
     *
     * @param templateType the template type
     * @return true if supported
     */
    boolean supportsTemplate(String templateType);
}
