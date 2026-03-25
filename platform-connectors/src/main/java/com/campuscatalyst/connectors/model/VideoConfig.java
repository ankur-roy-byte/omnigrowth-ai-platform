package com.campuscatalyst.connectors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Video rendering configuration.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoConfig {

    @Builder.Default
    private int width = 1920;

    @Builder.Default
    private int height = 1080;

    @Builder.Default
    private int fps = 30;

    @Builder.Default
    private String outputFormat = "mp4";

    private String backgroundMusicUrl;
    private String voiceoverUrl;
    private String watermarkUrl;
}
