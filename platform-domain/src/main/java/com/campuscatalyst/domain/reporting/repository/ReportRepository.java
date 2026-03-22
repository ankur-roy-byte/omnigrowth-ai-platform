package com.campuscatalyst.domain.reporting.repository;

import com.campuscatalyst.domain.reporting.Report;
import com.campuscatalyst.domain.reporting.ReportStatus;
import com.campuscatalyst.domain.reporting.ReportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    List<Report> findByCampaignId(UUID campaignId);

    List<Report> findByCampaignIdAndReportType(UUID campaignId, ReportType reportType);

    List<Report> findByStatus(ReportStatus status);
}
