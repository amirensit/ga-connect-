package com.gaConnecte.assistAuto.common.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;


@Entity
public class Role implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	@Id

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_role")
	private Integer id;
	
	@Pattern(regexp="^[A-Za-z ]+$")
	@Length(max = 50)
	private String libelle;
	
	
	
	@Cascade({CascadeType.SAVE_UPDATE})
	@ManyToMany
	  @JoinTable(
	      name="MODULE_ROLE",
	      joinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="id_role"),
	      inverseJoinColumns=@JoinColumn(name="MODULE_ID", referencedColumnName="id_module"))
	private List<Module>modules;
	 
	
	
	
	@Cascade({ CascadeType.DELETE ,CascadeType.SAVE_UPDATE})
	
	 @ManyToMany(mappedBy="roles")
		private List<Utilisateur>utilisateurs;
	 
	
	

	public Role(String libelle, List<Module> Modules) {
		super();
		this.libelle = libelle;
		this.modules = modules;
		
	}




	
	



	




	public Role(Integer id, String libelle) {
		super();
		this.id = id;
		this.libelle = libelle;
	}














	public Role(String libelle) {
		super();
		this.libelle = libelle;
	}














	public List<Module> getModules() {
		return modules;
	}














	public void setModules(List<Module> modules) {
		this.modules = modules;
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

	



	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	
	

	

	
	
	
	
	
	
	
	

}