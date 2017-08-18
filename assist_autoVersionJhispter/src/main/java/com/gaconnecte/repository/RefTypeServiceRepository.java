package com.gaconnecte.repository;

import com.gaconnecte.domain.RefTypeService;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RefTypeService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefTypeServiceRepository extends JpaRepository<RefTypeService,Long> {
    
}
