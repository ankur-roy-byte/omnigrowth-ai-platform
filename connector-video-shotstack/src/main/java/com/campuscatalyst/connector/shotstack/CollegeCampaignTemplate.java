package com.campuscatalyst.connector.shotstack;

import com.campuscatalyst.connectors.model.VideoConfig;
import com.campuscatalyst.connectors.model.VideoRenderRequest;
import com.campuscatalyst.connectors.model.VideoSlide;

import java.util.Arrays;
import java.util.List;

/**
 * College campaign video template builder.
 *
 * Creates a standard college marketing video with:
 * - Intro slide: college name + tagline
 * - 3 feature slides: key selling points
 * - CTA slide: "Apply now" + URL
 */
public class CollegeCampaignTemplate {

    public static VideoRenderRequest build(String collegeName, String tagline,
                                            List<String> sellingPoints, String ctaUrl) {

        List<VideoSlide> slides = Arrays.asList(
                // Intro slide
                VideoSlide.builder()
                        .headline(collegeName)
                        .subtext(tagline)
                        .durationSeconds(5)
                        .transitionType("fade")
                        .build(),

                // Feature slides
                VideoSlide.builder()
                        .headline("World-Class Placements")
                        .subtext(sellingPoints.size() > 0 ? sellingPoints.get(0) : "")
                        .durationSeconds(5)
                        .transitionType("slideLeft")
                        .build(),

                VideoSlide.builder()
                        .headline("Expert Faculty")
                        .subtext(sellingPoints.size() > 1 ? sellingPoints.get(1) : "")
                        .durationSeconds(5)
                        .transitionType("slideLeft")
                        .build(),

                VideoSlide.builder()
                        .headline("State-of-the-Art Campus")
                        .subtext(sellingPoints.size() > 2 ? sellingPoints.get(2) : "")
                        .durationSeconds(5)
                        .transitionType("slideLeft")
                        .build(),

                // CTA slide
                VideoSlide.builder()
                        .headline("Apply Now!")
                        .subtext(ctaUrl)
                        .durationSeconds(5)
                        .transitionType("zoom")
                        .build()
        );

        VideoConfig config = VideoConfig.builder()
                .width(1920)
                .height(1080)
                .fps(30)
                .outputFormat("mp4")
                .build();

        return VideoRenderRequest.builder()
                .templateId("college-campaign")
                .title(collegeName + " - Marketing Video")
                .slides(slides)
                .config(config)
                .build();
    }
}
