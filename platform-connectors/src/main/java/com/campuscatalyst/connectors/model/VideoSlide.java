package com.campuscatalyst.connectors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Video slide content for rendering.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoSlide {

    private String headline;
    private String subtext;
    private String imageUrl;
    private String backgroundUrl;
    private int durationSeconds;
    private String transitionType;
}
