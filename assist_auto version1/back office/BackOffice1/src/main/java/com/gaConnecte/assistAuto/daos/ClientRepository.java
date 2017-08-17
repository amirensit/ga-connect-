package com.gaConnecte.assistAuto.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gaConnecte.assistAuto.entities.Client;


public interface ClientRepository extends JpaRepository<Client,Long>  {
	
	
	@Modifying
	@Query("update Client c set c.etat =True where c.id_client =:id")
	public void enableEtatClient(@Param("id") Long id);
	
	
	@Modifying
	@Query("update Client c set c.etat =False where c.id_client =:id")
	public void disableEtatClient(@Param("id") Long id);
	
	
	@Query("select c from Client c where c.num_client = :x")
	public Client verifierDejaExist(@Param("x") Long id );

}
