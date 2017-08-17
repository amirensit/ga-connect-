package com.gaconnecte.repository;

import com.gaconnecte.domain.SysZoneGeographique;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SysZoneGeographique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysZoneGeographiqueRepository extends JpaRepository<SysZoneGeographique,Long> {
    
}
