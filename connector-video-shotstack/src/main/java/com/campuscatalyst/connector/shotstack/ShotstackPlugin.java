package com.campuscatalyst.connector.shotstack;

import org.pf4j.Plugin;
import org.pf4j.PluginWrapper;

/**
 * Shotstack video generator plugin entry point.
 */
public class ShotstackPlugin extends Plugin {

    public ShotstackPlugin(PluginWrapper wrapper) {
        super(wrapper);
    }

    @Override
    public void start() {
        log.info("Shotstack video generator plugin started");
    }

    @Override
    public void stop() {
        log.info("Shotstack video generator plugin stopped");
    }
}
