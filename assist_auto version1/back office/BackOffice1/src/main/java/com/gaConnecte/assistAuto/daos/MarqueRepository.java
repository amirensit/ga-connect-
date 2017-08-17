package com.gaConnecte.assistAuto.daos;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gaConnecte.assistAuto.entities.Marque;


public interface MarqueRepository extends JpaRepository<Marque,Long>  {

	
	@Query("select p from Marque p where p.nom_marque like :x")
	public Page<Marque> chercherMarques(@Param("x") String mc,Pageable pageable);
	
	
	
	
	
}
