package com.campuscatalyst.connectors.api;

import com.campuscatalyst.connectors.model.SearchRequest;
import com.campuscatalyst.connectors.model.SearchResult;
import com.campuscatalyst.connectors.model.RateLimitInfo;
import org.pf4j.ExtensionPoint;

import java.util.List;
import java.util.Optional;

/**
 * Extension point for search providers.
 *
 * Search providers are responsible for:
 * - Executing search queries against external search APIs
 * - Fetching documents from URLs (when policy allows)
 * - Providing rate limit information
 */
public interface SearchProvider extends ExtensionPoint {

    /**
     * Execute a search query.
     *
     * @param request the search request containing query and options
     * @return list of search results
     */
    List<SearchResult> search(SearchRequest request);

    /**
     * Fetch raw document content from a URL.
     *
     * @param url the URL to fetch
     * @return optional raw document content
     */
    Optional<String> fetchDocument(String url);

    /**
     * Get current rate limit information.
     *
     * @return rate limit info
     */
    RateLimitInfo rateLimitInfo();

    /**
     * Get the provider identifier.
     *
     * @return provider ID (e.g., "google", "bing")
     */
    String getProviderId();

    /**
     * Get priority for this provider (lower = higher priority).
     *
     * @return priority value
     */
    default int getPriority() {
        return 100;
    }
}
