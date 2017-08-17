package com.gaConnecte.assistAuto.common.DTO;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.gaConnecte.assistAuto.common.Entities.Role;

public class UtilisateurDTO {

private Integer id;
	
	
	private String nom;
	
	
	private String prenom;
	
	
	private String email;
	
	private String motDePasse;
	
	
	private Integer numFixe;
	
	
	private Integer numMobile;
	private Boolean status;
	private Boolean blocage;
	private Boolean connect;
	
	
	
	private List<RoleDTO>roles;



	public UtilisateurDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getMotDePasse() {
		return motDePasse;
	}



	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}



	public Integer getNumFixe() {
		return numFixe;
	}



	public void setNumFixe(Integer numFixe) {
		this.numFixe = numFixe;
	}



	public Integer getNumMobile() {
		return numMobile;
	}



	public void setNumMobile(Integer numMobile) {
		this.numMobile = numMobile;
	}



	public Boolean getStatus() {
		return status;
	}



	public void setStatus(Boolean status) {
		this.status = status;
	}



	public Boolean getBlocage() {
		return blocage;
	}



	public void setBlocage(Boolean blocage) {
		this.blocage = blocage;
	}



	public Boolean getConnect() {
		return connect;
	}



	public void setConnect(Boolean connect) {
		this.connect = connect;
	}



	public List<RoleDTO> getRoles() {
		return roles;
	}



	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	
	
	
}