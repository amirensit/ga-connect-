package com.gaconnecte.repository;

import com.gaconnecte.domain.SysGouvernorat;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SysGouvernorat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SysGouvernoratRepository extends JpaRepository<SysGouvernorat,Long> {
    
}
