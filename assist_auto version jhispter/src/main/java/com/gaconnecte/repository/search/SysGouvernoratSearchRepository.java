package com.gaconnecte.repository.search;

import com.gaconnecte.domain.SysGouvernorat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SysGouvernorat entity.
 */
public interface SysGouvernoratSearchRepository extends ElasticsearchRepository<SysGouvernorat, Long> {
}
