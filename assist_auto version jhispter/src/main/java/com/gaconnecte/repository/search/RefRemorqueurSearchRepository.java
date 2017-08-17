package com.gaconnecte.repository.search;

import com.gaconnecte.domain.RefRemorqueur;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RefRemorqueur entity.
 */
public interface RefRemorqueurSearchRepository extends ElasticsearchRepository<RefRemorqueur, Long> {
}
