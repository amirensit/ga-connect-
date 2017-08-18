package com.gaconnecte.repository;

import com.gaconnecte.domain.Assuree;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Assuree entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssureeRepository extends JpaRepository<Assuree,Long> {
    
}
