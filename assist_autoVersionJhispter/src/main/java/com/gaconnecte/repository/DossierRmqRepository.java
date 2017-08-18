package com.gaconnecte.repository;

import com.gaconnecte.domain.DossierRmq;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DossierRmq entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DossierRmqRepository extends JpaRepository<DossierRmq,Long> {
    
}
