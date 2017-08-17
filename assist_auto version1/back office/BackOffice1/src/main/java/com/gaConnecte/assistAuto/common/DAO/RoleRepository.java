package com.gaConnecte.assistAuto.common.DAO;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gaConnecte.assistAuto.common.Entities.Role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("select r from Role r where r.libelle like :x")
	public Page<Role> chercherRoles(@Param("x") String mc,Pageable pageable);
	
	@Query(" SELECT r FROM Role r WHERE r.libelle = :libelle ")
	Role findByLibelle(@Param(value = "libelle") String libelle);
}