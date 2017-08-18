package com.gaconnecte.repository;

import com.gaconnecte.domain.RefCompagnie;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the RefCompagnie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefCompagnieRepository extends JpaRepository<RefCompagnie,Long> {
    
    @Query("select distinct ref_compagnie from RefCompagnie ref_compagnie left join fetch ref_compagnie.packs")
    List<RefCompagnie> findAllWithEagerRelationships();

    @Query("select ref_compagnie from RefCompagnie ref_compagnie left join fetch ref_compagnie.packs where ref_compagnie.id =:id")
    RefCompagnie findOneWithEagerRelationships(@Param("id") Long id);
    
}
