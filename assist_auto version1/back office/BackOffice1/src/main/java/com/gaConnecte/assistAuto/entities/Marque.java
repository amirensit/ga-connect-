package com.gaConnecte.assistAuto.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


// TODO: Auto-generated Javadoc
/**
 * The Class Marque.
 */
@Entity
public class Marque implements Serializable {
	
	/** The id marque. */
	@Id @GeneratedValue
	private Long id_marque;
	
	/** The nom marque. */
	private String nom_marque;
	
	/** The contrats. */
	@OneToMany(mappedBy="marque",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Contrat> contrats ;


	/**
	 * Instantiates a new marque.
	 */
	public Marque() {
		super();
	}


	/**
	 * Instantiates a new marque.
	 *
	 * @param id_marque the id marque
	 * @param nom_marque the nom marque
	 */
	public Marque(Long id_marque, String nom_marque) {
		super();
		this.id_marque = id_marque;
		this.nom_marque = nom_marque;
	}


	/**
	 * Instantiates a new marque.
	 *
	 * @param nom_marque the nom marque
	 */
	public Marque(String nom_marque) {
		super();
		this.nom_marque = nom_marque;
	}


	/**
	 * Gets the id marque.
	 *
	 * @return the id marque
	 */
	public Long getId_marque() {
		return id_marque;
	}


	/**
	 * Sets the id marque.
	 *
	 * @param id_marque the new id marque
	 */
	public void setId_marque(Long id_marque) {
		this.id_marque = id_marque;
	}


	/**
	 * Gets the nom marque.
	 *
	 * @return the nom marque
	 */
	public String getNom_marque() {
		return nom_marque;
	}


	/**
	 * Sets the nom marque.
	 *
	 * @param nom_marque the new nom marque
	 */
	public void setNom_marque(String nom_marque) {
		this.nom_marque = nom_marque;
	}
	
	/**
	 * Gets the contrats.
	 *
	 * @return the contrats
	 */
	public List<Contrat> getContrats() {
		return contrats;
	}


	/**
	 * Sets the contrats.
	 *
	 * @param contrats the new contrats
	 */
	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}
	
	

}
