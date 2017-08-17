package org.sid.dao;

import org.sid.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
	
	
	
	@Query(" SELECT u FROM Utilisateur u WHERE u.login = :l and u.password = :p ")
	public Utilisateur findByLoginAndPass(@Param(value = "l") String login, @Param(value = "p")String password);
	 

}
