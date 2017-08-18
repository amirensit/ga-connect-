package com.gaconnecte.repository.search;

import com.gaconnecte.domain.RefMarque;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RefMarque entity.
 */
public interface RefMarqueSearchRepository extends ElasticsearchRepository<RefMarque, Long> {
}
