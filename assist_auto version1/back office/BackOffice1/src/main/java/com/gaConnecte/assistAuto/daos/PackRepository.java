package com.gaConnecte.assistAuto.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gaConnecte.assistAuto.entities.Pack;


public interface PackRepository extends JpaRepository<Pack, Long> {

	
	@Modifying
	@Query("update Pack c set c.etat =True where c.id_pack =:id")
	public void enableEtatPack(@Param("id") Long id);
	
	
	@Modifying
	@Query("update Pack c set c.etat =False where c.id_pack =:id")
	public void disableEtatPack(@Param("id") Long id);
	
}
