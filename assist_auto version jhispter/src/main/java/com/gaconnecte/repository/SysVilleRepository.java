package com.gaconnecte.repository;

import com.gaconnecte.domain.SysVille;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SysVille entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysVilleRepository extends JpaRepository<SysVille,Long> {
    
}
