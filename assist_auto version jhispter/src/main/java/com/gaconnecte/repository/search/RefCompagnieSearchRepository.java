package com.gaconnecte.repository.search;

import com.gaconnecte.domain.RefCompagnie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RefCompagnie entity.
 */
public interface RefCompagnieSearchRepository extends ElasticsearchRepository<RefCompagnie, Long> {
}
