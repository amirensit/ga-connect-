package com.gaconnecte.repository.search;

import com.gaconnecte.domain.RefTypeService;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RefTypeService entity.
 */
public interface RefTypeServiceSearchRepository extends ElasticsearchRepository<RefTypeService, Long> {
}
