package com.campuscatalyst.connector.linkedin;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

/**
 * LinkedIn connector plugin entry point.
 */
public class LinkedInPlugin extends Plugin {

    public LinkedInPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("LinkedIn connector plugin started");
    }

    @Override
    public void stop() {
        log.info("LinkedIn connector plugin stopped");
    }
}
