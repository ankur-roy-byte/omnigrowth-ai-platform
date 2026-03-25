package com.campuscatalyst.connectors.plugin;

import com.campuscatalyst.connectors.api.AnalyticsConnector;
import com.campuscatalyst.connectors.api.PublisherConnector;
import com.campuscatalyst.connectors.api.SearchProvider;
import com.campuscatalyst.connectors.api.VideoGenerator;
import com.campuscatalyst.domain.common.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Registry for accessing connector implementations.
 *
 * Provides lookup methods for finding the right connector
 * based on platform, capabilities, and priority.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ConnectorRegistry {

    private final ConnectorPluginManager pluginManager;

    /**
     * Get the highest-priority search provider.
     */
    public Optional<SearchProvider> getPrimarySearchProvider() {
        return pluginManager.getSearchProviders().stream()
                .min(Comparator.comparingInt(SearchProvider::getPriority));
    }

    /**
     * Get search provider by ID.
     */
    public Optional<SearchProvider> getSearchProviderById(String providerId) {
        return pluginManager.getSearchProviders().stream()
                .filter(p -> p.getProviderId().equals(providerId))
                .findFirst();
    }

    /**
     * Get all search providers ordered by priority.
     */
    public List<SearchProvider> getAllSearchProviders() {
        return pluginManager.getSearchProviders().stream()
                .sorted(Comparator.comparingInt(SearchProvider::getPriority))
                .toList();
    }

    /**
     * Get video generator by ID.
     */
    public Optional<VideoGenerator> getVideoGeneratorById(String generatorId) {
        return pluginManager.getVideoGenerators().stream()
                .filter(g -> g.getGeneratorId().equals(generatorId))
                .findFirst();
    }

    /**
     * Get video generator that supports a template type.
     */
    public Optional<VideoGenerator> getVideoGeneratorForTemplate(String templateType) {
        return pluginManager.getVideoGenerators().stream()
                .filter(g -> g.supportsTemplate(templateType))
                .findFirst();
    }

    /**
     * Get publisher connector for a platform.
     */
    public Optional<PublisherConnector> getPublisherForPlatform(Platform platform) {
        return pluginManager.getPublisherConnectors().stream()
                .filter(c -> c.getPlatform() == platform)
                .findFirst();
    }

    /**
     * Get analytics connector for a platform.
     */
    public Optional<AnalyticsConnector> getAnalyticsConnectorForPlatform(Platform platform) {
        return pluginManager.getAnalyticsConnectors().stream()
                .filter(c -> c.getPlatform() == platform)
                .findFirst();
    }

    /**
     * Check if a platform has a publisher connector available.
     */
    public boolean hasPublisherForPlatform(Platform platform) {
        return getPublisherForPlatform(platform).isPresent();
    }

    /**
     * Check if a platform has an analytics connector available.
     */
    public boolean hasAnalyticsForPlatform(Platform platform) {
        return getAnalyticsConnectorForPlatform(platform).isPresent();
    }
}
