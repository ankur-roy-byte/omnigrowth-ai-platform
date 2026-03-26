package com.campuscatalyst.connector.youtube;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

/**
 * YouTube connector plugin entry point.
 */
public class YouTubePlugin extends Plugin {

    public YouTubePlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("YouTube connector plugin started");
    }

    @Override
    public void stop() {
        log.info("YouTube connector plugin stopped");
    }
}
