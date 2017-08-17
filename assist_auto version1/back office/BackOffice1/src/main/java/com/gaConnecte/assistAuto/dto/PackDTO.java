package com.gaConnecte.assistAuto.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class PackDTO.
 */
public class PackDTO {
	
	
	/** The id pack. */
	private Long id_pack;
	
	/** The nom pack. */
	private String nom_pack;
	
	/** The description. */
	private String description;
	
	/** The nbre max service. */
	private int nbre_max_service;
	
	/** The kilometrage max. */
	private int kilometrage_max;
	
	/** The etat. */
	private Boolean etat;
	
	
	
	
	
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
	 * Gets the id pack.
	 *
	 * @return the id pack
	 */
	public Long getId_pack() {
		return id_pack;
	}
	
	/**
	 * Sets the id pack.
	 *
	 * @param id_pack the new id pack
	 */
	public void setId_pack(Long id_pack) {
		this.id_pack = id_pack;
	}
	
	/**
	 * Gets the nom pack.
	 *
	 * @return the nom pack
	 */
	public String getNom_pack() {
		return nom_pack;
	}
	
	/**
	 * Sets the nom pack.
	 *
	 * @param nom_pack the new nom pack
	 */
	public void setNom_pack(String nom_pack) {
		this.nom_pack = nom_pack;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the nbre max service.
	 *
	 * @return the nbre max service
	 */
	public int getNbre_max_service() {
		return nbre_max_service;
	}
	
	/**
	 * Sets the nbre max service.
	 *
	 * @param nbre_max_service the new nbre max service
	 */
	public void setNbre_max_service(int nbre_max_service) {
		this.nbre_max_service = nbre_max_service;
	}
	
	/**
	 * Gets the kilometrage max.
	 *
	 * @return the kilometrage max
	 */
	public int getKilometrage_max() {
		return kilometrage_max;
	}
	
	/**
	 * Sets the kilometrage max.
	 *
	 * @param kilometrage_max the new kilometrage max
	 */
	public void setKilometrage_max(int kilometrage_max) {
		this.kilometrage_max = kilometrage_max;
	}
	
	

}
