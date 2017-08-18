package com.gaconnecte.repository.search;

import com.gaconnecte.domain.Contrat;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Contrat entity.
 */
public interface ContratSearchRepository extends ElasticsearchRepository<Contrat, Long> {
}
