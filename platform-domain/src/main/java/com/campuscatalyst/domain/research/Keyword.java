package com.campuscatalyst.domain.research;

import com.campuscatalyst.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Keyword represents a search term or phrase discovered during research.
 *
 * Keywords are associated with topic clusters and include metadata
 * like search volume score and intent type.
 */
@Entity
@Table(name = "keywords")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Keyword extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_cluster_id", nullable = false)
    private TopicCluster topicCluster;

    @NotBlank
    @Size(max = 500)
    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Column(name = "score")
    private Double score; // Relevance/volume score

    @Enumerated(EnumType.STRING)
    @Column(name = "intent_type")
    private IntentType intentType;
}
