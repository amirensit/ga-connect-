package com.gaConnecte.assistAuto.daos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.TypeService;

// TODO: Auto-generated Javadoc
/**
 * The Interface TypeServiceRepository.
 */
public interface TypeServiceRepository extends JpaRepository<TypeService, Long> {


	/**
	 * Chercher type service.
	 *
	 * @param mc the mc
	 * @param pageable the pageable
	 * @return the page
	 */
	@Query("select p from TypeService p where p.nom_typeService like :x")
	public Page<TypeService> chercherTypeService(@Param("x") String mc,Pageable pageable);
	
}
