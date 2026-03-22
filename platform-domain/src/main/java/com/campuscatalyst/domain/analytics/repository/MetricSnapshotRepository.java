package com.campuscatalyst.domain.analytics.repository;

import com.campuscatalyst.domain.analytics.MetricSnapshot;
import com.campuscatalyst.domain.common.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface MetricSnapshotRepository extends JpaRepository<MetricSnapshot, UUID> {

    List<MetricSnapshot> findByPublicationId(UUID publicationId);

    List<MetricSnapshot> findByPublicationIdAndMetricKey(UUID publicationId, String metricKey);

    @Query("SELECT m FROM MetricSnapshot m WHERE m.publication.id = :publicationId " +
           "AND m.timestamp BETWEEN :start AND :end ORDER BY m.timestamp")
    List<MetricSnapshot> findByPublicationIdAndTimeRange(
            @Param("publicationId") UUID publicationId,
            @Param("start") Instant start,
            @Param("end") Instant end);

    List<MetricSnapshot> findByPlatformAndMetricKey(Platform platform, String metricKey);
}
