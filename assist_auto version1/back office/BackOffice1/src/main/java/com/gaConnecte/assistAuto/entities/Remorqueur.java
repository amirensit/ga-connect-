package com.gaConnecte.assistAuto.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// TODO: Auto-generated Javadoc
/**
 * The Class Remorqueur.
 */
@Entity
public class Remorqueur implements Serializable {
	
	/** The id remorqueur. */
	@Id @GeneratedValue
	private Long id_remorqueur;
	
	/** The raison social. */
	private String raison_social;
	
	/** The matricule fiscal. */
	private String matricule_fiscal;
	
	/** The tel. */
	private String tel;
	
	/** The nom contact. */
	private String nom_contact;
	
	/** The prenom contact. */
	private String prenom_contact;
	
	/** The etat. */
	private Boolean etat;
	
	/** The ville. */
	@ManyToOne
	@JoinColumn(name="CODE_VILLE")
	private Ville ville;
	
	
	/**
	 * Instantiates a new remorqueur.
	 */
	public Remorqueur() {
		super();
	}

	/**
	 * Instantiates a new remorqueur.
	 *
	 * @param raison_social the raison social
	 * @param matricule_fiscal the matricule fiscal
	 * @param tel the tel
	 * @param nom_contact the nom contact
	 * @param prenom_contact the prenom contact
	 * @param etat the etat
	 */
	public Remorqueur(String raison_social, String matricule_fiscal, String tel, String nom_contact,
			String prenom_contact, Boolean etat) {
		super();
		this.raison_social = raison_social;
		this.matricule_fiscal = matricule_fiscal;
		this.tel = tel;
		this.nom_contact = nom_contact;
		this.prenom_contact = prenom_contact;
		this.etat = etat;
	}


























	/**
	 * Instantiates a new remorqueur.
	 *
	 * @param id_remorqueur the id remorqueur
	 * @param raison_social the raison social
	 * @param matricule_fiscal the matricule fiscal
	 * @param tel the tel
	 * @param nom_contact the nom contact
	 * @param prenom_contact the prenom contact
	 * @param etat the etat
	 */
	public Remorqueur(Long id_remorqueur, String raison_social, String matricule_fiscal, String tel, String nom_contact,
			String prenom_contact, Boolean etat) {
		super();
		this.id_remorqueur = id_remorqueur;
		this.raison_social = raison_social;
		this.matricule_fiscal = matricule_fiscal;
		this.tel = tel;
		this.nom_contact = nom_contact;
		this.prenom_contact = prenom_contact;
		this.etat = etat;
	}


























	/**
	 * Instantiates a new remorqueur.
	 *
	 * @param id_remorqueur the id remorqueur
	 * @param raison_social the raison social
	 * @param matricule_fiscal the matricule fiscal
	 * @param tel the tel
	 * @param nom_contact the nom contact
	 * @param prenom_contact the prenom contact
	 * @param etat the etat
	 * @param ville the ville
	 */
	public Remorqueur(Long id_remorqueur, String raison_social, String matricule_fiscal, String tel, String nom_contact,
			String prenom_contact, Boolean etat, Ville ville) {
		super();
		this.id_remorqueur = id_remorqueur;
		this.raison_social = raison_social;
		this.matricule_fiscal = matricule_fiscal;
		this.tel = tel;
		this.nom_contact = nom_contact;
		this.prenom_contact = prenom_contact;
		this.etat = etat;
		this.ville = ville;
	}





















	/**
	 * Gets the etat.
	 *
	 * @return the etat
	 */
	public Boolean getEtat() {
		return etat;
	}

	/**
	 * Sets the etat.
	 *
	 * @param etat the new etat
	 */
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}




	/**
	 * Gets the id remorqueur.
	 *
	 * @return the id remorqueur
	 */
	public Long getId_remorqueur() {
		return id_remorqueur;
	}



	/**
	 * Sets the id remorqueur.
	 *
	 * @param id_remorqueur the new id remorqueur
	 */
	public void setId_remorqueur(Long id_remorqueur) {
		this.id_remorqueur = id_remorqueur;
	}



	/**
	 * Gets the raison social.
	 *
	 * @return the raison social
	 */
	public String getRaison_social() {
		return raison_social;
	}



	/**
	 * Sets the raison social.
	 *
	 * @param raison_social the new raison social
	 */
	public void setRaison_social(String raison_social) {
		this.raison_social = raison_social;
	}



	/**
	 * Gets the matricule fiscal.
	 *
	 * @return the matricule fiscal
	 */
	public String getMatricule_fiscal() {
		return matricule_fiscal;
	}



	/**
	 * Sets the matricule fiscal.
	 *
	 * @param matricule_fiscal the new matricule fiscal
	 */
	public void setMatricule_fiscal(String matricule_fiscal) {
		this.matricule_fiscal = matricule_fiscal;
	}



	/**
	 * Gets the tel.
	 *
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}



	/**
	 * Sets the tel.
	 *
	 * @param tel the new tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}



	/**
	 * Gets the nom contact.
	 *
	 * @return the nom contact
	 */
	public String getNom_contact() {
		return nom_contact;
	}



	/**
	 * Sets the nom contact.
	 *
	 * @param nom_contact the new nom contact
	 */
	public void setNom_contact(String nom_contact) {
		this.nom_contact = nom_contact;
	}



	/**
	 * Gets the prenom contact.
	 *
	 * @return the prenom contact
	 */
	public String getPrenom_contact() {
		return prenom_contact;
	}



	/**
	 * Sets the prenom contact.
	 *
	 * @param prenom_contact the new prenom contact
	 */
	public void setPrenom_contact(String prenom_contact) {
		this.prenom_contact = prenom_contact;
	}



	/**
	 * Gets the ville.
	 *
	 * @return the ville
	 */
	public Ville getVille() {
		return ville;
	}



	/**
	 * Sets the ville.
	 *
	 * @param ville the new ville
	 */
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	
}
