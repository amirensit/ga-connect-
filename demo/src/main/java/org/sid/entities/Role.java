package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Role implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	private String typeRole;
	@ManyToMany(mappedBy="roles")
	private List<Utilisateur>utilisateurs;
	
	
	public Role(String typeRole, List<Utilisateur> utilisateurs) {
		super();
		this.typeRole = typeRole;
		this.utilisateurs = utilisateurs;
	}




	public List<Utilisateur> getUtilisateurs() {
		return utilisateurs;
	}




	public void setUtilisateurs(List<Utilisateur> utilisateurs) {
		this.utilisateurs = utilisateurs;
	}




	public Role() {
		super();
	}




	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	



	public String getTypeRole() {
		return typeRole;
	}


	public void setTypeRole(String typeRole) {
		this.typeRole = typeRole;
	}



	

	

	
	
	
	
	
	
	
	

}
