package com.campuscatalyst.connectors.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Request for executing a search query.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    private String query;
    private int maxResults;
    private List<String> restrictedSites; // Site-restricted search
    private String language;
    private String region;
    private Map<String, String> customParams;
}
