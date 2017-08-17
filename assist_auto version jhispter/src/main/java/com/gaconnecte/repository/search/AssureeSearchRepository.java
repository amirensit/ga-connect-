package com.gaconnecte.repository.search;

import com.gaconnecte.domain.Assuree;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Assuree entity.
 */
public interface AssureeSearchRepository extends ElasticsearchRepository<Assuree, Long> {
}
