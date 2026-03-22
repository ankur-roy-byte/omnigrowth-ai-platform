package com.campuscatalyst.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Campus Catalyst CloudBot - AI-powered marketing automation platform
 * for educational institutions.
 *
 * This platform enables colleges and universities to:
 * - Research trending topics in their domain
 * - Generate content plans and marketing assets
 * - Create and render videos automatically
 * - Publish to multiple social platforms (YouTube, LinkedIn, TikTok)
 * - Track engagement metrics and generate reports
 */
@SpringBootApplication(scanBasePackages = "com.campuscatalyst")
@EnableJpaRepositories(basePackages = "com.campuscatalyst")
@EnableJpaAuditing
public class CampusCatalystApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusCatalystApplication.class, args);
    }
}
