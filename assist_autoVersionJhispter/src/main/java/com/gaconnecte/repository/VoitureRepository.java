package com.gaconnecte.repository;

import com.gaconnecte.domain.Voiture;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Voiture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoitureRepository extends JpaRepository<Voiture,Long> {
    
}
