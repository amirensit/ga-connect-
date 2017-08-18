package com.gaconnecte.repository.search;

import com.gaconnecte.domain.DossierRmq;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DossierRmq entity.
 */
public interface DossierRmqSearchRepository extends ElasticsearchRepository<DossierRmq, Long> {
}
