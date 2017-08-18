package com.gaconnecte.repository.search;

import com.gaconnecte.domain.SysVille;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SysVille entity.
 */
public interface SysVilleSearchRepository extends ElasticsearchRepository<SysVille, Long> {
}
