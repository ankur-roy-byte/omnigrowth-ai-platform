package com.campuscatalyst.connectors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Result from a search query.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {

    private String title;
    private String url;
    private String snippet;
    private String displayUrl;
    private double relevanceScore;
}
