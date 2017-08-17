package com.gaconnecte.repository.search;

import com.gaconnecte.domain.Voiture;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Voiture entity.
 */
public interface VoitureSearchRepository extends ElasticsearchRepository<Voiture, Long> {
}
