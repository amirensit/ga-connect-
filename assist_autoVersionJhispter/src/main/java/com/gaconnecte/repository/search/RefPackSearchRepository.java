package com.gaconnecte.repository.search;

import com.gaconnecte.domain.RefPack;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RefPack entity.
 */
public interface RefPackSearchRepository extends ElasticsearchRepository<RefPack, Long> {
}
