package com.campuscatalyst.connectors.plugin;

import com.campuscatalyst.connectors.api.AnalyticsConnector;
import com.campuscatalyst.connectors.api.PublisherConnector;
import com.campuscatalyst.connectors.api.SearchProvider;
import com.campuscatalyst.connectors.api.VideoGenerator;
import lombok.extern.slf4j.Slf4j;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Manages connector plugins using PF4J.
 *
 * Plugins are loaded from the /plugins directory and provide
 * implementations of connector interfaces.
 */
@Slf4j
@Component
public class ConnectorPluginManager {

    private static final String PLUGINS_DIR = "plugins";

    private PluginManager pluginManager;

    @PostConstruct
    public void init() {
        Path pluginsPath = Paths.get(PLUGINS_DIR);
        log.info("Initializing connector plugins from: {}", pluginsPath.toAbsolutePath());

        pluginManager = new DefaultPluginManager(pluginsPath);
        pluginManager.loadPlugins();
        pluginManager.startPlugins();

        log.info("Loaded {} plugins", pluginManager.getPlugins().size());
        pluginManager.getPlugins().forEach(plugin ->
            log.info("  - {} v{}", plugin.getPluginId(), plugin.getDescriptor().getVersion())
        );
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutting down connector plugins");
        if (pluginManager != null) {
            pluginManager.stopPlugins();
            pluginManager.unloadPlugins();
        }
    }

    /**
     * Get all loaded search providers.
     */
    public List<SearchProvider> getSearchProviders() {
        return pluginManager.getExtensions(SearchProvider.class);
    }

    /**
     * Get all loaded video generators.
     */
    public List<VideoGenerator> getVideoGenerators() {
        return pluginManager.getExtensions(VideoGenerator.class);
    }

    /**
     * Get all loaded publisher connectors.
     */
    public List<PublisherConnector> getPublisherConnectors() {
        return pluginManager.getExtensions(PublisherConnector.class);
    }

    /**
     * Get all loaded analytics connectors.
     */
    public List<AnalyticsConnector> getAnalyticsConnectors() {
        return pluginManager.getExtensions(AnalyticsConnector.class);
    }

    /**
     * Get the underlying plugin manager.
     */
    public PluginManager getPluginManager() {
        return pluginManager;
    }
}
