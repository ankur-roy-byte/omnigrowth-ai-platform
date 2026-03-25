package com.campuscatalyst.connectors.model;

import com.campuscatalyst.domain.common.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Request for publishing content to a social platform.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublishRequest {

    private Platform platform;
    private String title;
    private String description;
    private List<String> tags;
    private String videoUrl;
    private String thumbnailUrl;
    private String text;
    private List<String> imageUrls;
    private String accessToken;
    private String channelId; // YouTube channel, LinkedIn page, etc.
    private Map<String, String> platformSpecificOptions;
    private boolean asDraft; // For TikTok draft mode
}
