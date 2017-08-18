package com.gaconnecte.repository;

import com.gaconnecte.domain.RefMarque;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the RefMarque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefMarqueRepository extends JpaRepository<RefMarque,Long> {
    
}
