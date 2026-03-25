package com.campuscatalyst.connectors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Location of a rendered video asset.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoAssetLocation {

    private String url;
    private String thumbnailUrl;
    private int durationSeconds;
    private long fileSizeBytes;
    private String format;
}
