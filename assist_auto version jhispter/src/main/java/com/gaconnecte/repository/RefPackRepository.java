package com.gaconnecte.repository;

import com.gaconnecte.domain.RefPack;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the RefPack entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RefPackRepository extends JpaRepository<RefPack,Long> {
    
    @Query("select distinct ref_pack from RefPack ref_pack left join fetch ref_pack.typeServices")
    List<RefPack> findAllWithEagerRelationships();

    @Query("select ref_pack from RefPack ref_pack left join fetch ref_pack.typeServices where ref_pack.id =:id")
    RefPack findOneWithEagerRelationships(@Param("id") Long id);
    
}
