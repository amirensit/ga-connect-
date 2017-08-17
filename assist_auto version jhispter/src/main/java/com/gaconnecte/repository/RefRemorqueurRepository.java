package com.gaconnecte.repository;

import com.gaconnecte.domain.RefRemorqueur;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RefRemorqueur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefRemorqueurRepository extends JpaRepository<RefRemorqueur,Long> {
    
}
