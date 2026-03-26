package com.campuscatalyst.connector.tiktok;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

/**
 * TikTok connector plugin entry point.
 */
public class TikTokPlugin extends Plugin {

    public TikTokPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("TikTok connector plugin started");
    }

    @Override
    public void stop() {
        log.info("TikTok connector plugin stopped");
    }
}
