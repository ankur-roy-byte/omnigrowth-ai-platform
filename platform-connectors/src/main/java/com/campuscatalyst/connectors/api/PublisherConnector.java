package com.campuscatalyst.connectors.api;

import com.campuscatalyst.connectors.model.PublishRequest;
import com.campuscatalyst.connectors.model.PublicationResult;
import com.campuscatalyst.connectors.model.PlatformCapability;
import com.campuscatalyst.domain.common.Platform;
import org.pf4j.ExtensionPoint;

import java.util.Set;

/**
 * Extension point for social media publishing connectors.
 *
 * Publisher connectors handle:
 * - Publishing video content to social platforms
 * - Publishing text/image posts to social platforms
 * - OAuth authentication with platforms
 */
public interface PublisherConnector extends ExtensionPoint {

    /**
     * Publish a video to the platform.
     *
     * @param request the publish request with video and metadata
     * @return publication result with post ID and URL
     */
    PublicationResult publishVideo(PublishRequest request);

    /**
     * Publish a text/image post to the platform.
     *
     * @param request the publish request with content and media
     * @return publication result with post ID and URL
     */
    PublicationResult publishPost(PublishRequest request);

    /**
     * Check if this connector supports a specific capability.
     *
     * @param capability the platform capability
     * @return true if supported
     */
    boolean supports(PlatformCapability capability);

    /**
     * Get all supported capabilities.
     *
     * @return set of supported capabilities
     */
    Set<PlatformCapability> getSupportedCapabilities();

    /**
     * Get the target platform.
     *
     * @return the platform this connector publishes to
     */
    Platform getPlatform();

    /**
     * Get the connector identifier.
     *
     * @return connector ID
     */
    String getConnectorId();
}
