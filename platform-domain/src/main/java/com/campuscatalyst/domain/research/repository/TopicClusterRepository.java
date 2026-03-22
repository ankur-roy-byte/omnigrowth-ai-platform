package com.campuscatalyst.domain.research.repository;

import com.campuscatalyst.domain.research.TopicCluster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TopicClusterRepository extends JpaRepository<TopicCluster, UUID> {

    List<TopicCluster> findByResearchJobId(UUID researchJobId);
}
