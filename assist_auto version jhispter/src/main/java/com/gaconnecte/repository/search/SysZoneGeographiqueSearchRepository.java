package com.gaconnecte.repository.search;

import com.gaconnecte.domain.SysZoneGeographique;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SysZoneGeographique entity.
 */
public interface SysZoneGeographiqueSearchRepository extends ElasticsearchRepository<SysZoneGeographique, Long> {
}
