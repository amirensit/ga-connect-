package com.gaConnecte.assistAuto.common.DAO;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gaConnecte.assistAuto.common.Entities.Role;
import com.gaConnecte.assistAuto.common.Entities.Utilisateur;



@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
	
	@Query("select u from Utilisateur u where u.nom like :x")
	public Page<Utilisateur> chercherUtilisateurs(@Param("x") String mc,Pageable pageable);
	
	@Query(" SELECT u FROM Utilisateur u WHERE u.email = :email ")
	Utilisateur findByEmail(@Param(value = "email") String email);

	@Query(" SELECT u FROM Utilisateur u WHERE u.motDePasse = :motDePasse ")
	Utilisateur findByMotDePasse(@Param(value = "motDePasse") String motDePasse);
	
    @Query(" SELECT u FROM Utilisateur u WHERE u.email = :email and u.motDePasse = :motDePasse  ")
    Utilisateur findByLoginAndPass(@Param(value = "email") String email, @Param(value = "motDePasse") String motDePasse);
//
//	@Query(" SELECT u FROM Utilisateur u WHERE u.login = :login and u.email = :email  ")
//	List<Utilisateur> findByLoginAndEmail(@Param(value = "login") String login, @Param(value = "email") String email);
//
//	@Query(" SELECT u FROM Utilisateur u WHERE u.login = :login and u.email = :email  ")
//	Utilisateur findByLoginAndMail(@Param(value = "login") String login, @Param(value = "email") String email);
	
	
}