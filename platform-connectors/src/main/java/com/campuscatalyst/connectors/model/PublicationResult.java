package com.campuscatalyst.connectors.model;

import com.campuscatalyst.domain.common.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Result from publishing content.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicationResult {

    private boolean success;
    private Platform platform;
    private String postId;
    private String postUrl;
    private Instant publishedAt;
    private String errorMessage;
    private String errorCode;
}
