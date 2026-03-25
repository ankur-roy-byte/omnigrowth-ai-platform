package com.campuscatalyst.connectors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Request for video rendering.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoRenderRequest {

    private String templateId;
    private String title;
    private List<VideoSlide> slides;
    private VideoConfig config;
    private Map<String, Object> customData;
}
