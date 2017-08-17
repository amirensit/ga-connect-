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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;










@Entity
public class Utilisateur implements Serializable {
	
	




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 @Column(name="id_utilisateur")
	private Integer id;
	
	@NotNull
	@Pattern(regexp="^[A-Za-z ]+$")
	@Size(max = 50)
	private String nom;
	
	@NotNull
	@Pattern(regexp="^[A-Za-z ]+$")
	@Size(max = 50)
	private String prenom;
	
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
	private String email;
	
	private String motDePasse;
	
//	@Pattern(regexp= "[0-9]{10}")
//	@Length(max = 8, min = 8)
	private Integer numFixe;
	
//	@Pattern(regexp= "[0-9]{10}")
//	@Length(max = 8, min = 8)
	private Integer numMobile;
	private Boolean status;
	private Boolean blocage;
	private Boolean connect;
	
	@Cascade({CascadeType.SAVE_UPDATE})
	@ManyToMany
	  @JoinTable(
	      name="UTILISATEUR_ROLE",
	      joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="id_utilisateur"),
	      inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="id_role"))
	private List<Role>roles;
	
	
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Utilisateur(String nom, String prenom, String email, String motDePasse, Integer numFixe,Integer numMobile, Boolean status, Boolean blocage, Boolean connect,
			List<Role> roles) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.numFixe = numFixe;
		this.numMobile = numMobile;
		this.status = status;
		this.blocage = blocage;
		this.connect = connect;
		this.roles = roles;
	}





	public Utilisateur(Integer id, String nom, String prenom, String email, String motDePasse, Integer numFixe,
			Integer numMobile, Boolean status, Boolean blocage, Boolean connect) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.numFixe = numFixe;
		this.numMobile = numMobile;
		this.status = status;
		this.blocage = blocage;
		this.connect = connect;
	}





	public Utilisateur(String nom, String prenom, String email, String motDePasse, Integer numFixe, Integer numMobile,
			Boolean status, Boolean blocage, Boolean connect) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.numFixe = numFixe;
		this.numMobile = numMobile;
		this.status = status;
		this.blocage = blocage;
		this.connect = connect;
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





	public List<Role> getRoles() {
		return roles;
	}





	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}





	

	

	
	


	
	
	
	
	
	
	
	

}