package org.sid.dao;

import org.sid.entities.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import antlr.collections.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	@Query("select p from Produit p where p.designation like :x")
	public Page<Produit> chercherProduits(@Param("x") String mc,Pageable pageable);
	
	@Query(" SELECT p FROM Produit p WHERE p.designation = :name ")
	public Produit findByName(@Param(value = "name") String name);
	
	
	
}
