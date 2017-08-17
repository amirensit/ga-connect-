package com.gaConnecte.assistAuto.dto;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientDTO.
 */
public class ClientDTO {
	
	/** The id client. */
	private Long id_client;
	
	/** The num client. */
	private Long num_client;
	
	/** The nom client. */
	private String nom_client;
	
	/** The etat. */
	private Boolean etat;
	
	/** The packs. */
	private List<PackDTO> packs;
	
	
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
	 * Gets the id client.
	 *
	 * @return the id client
	 */
	public Long getId_client() {
		return id_client;
	}
	
	/**
	 * Sets the id client.
	 *
	 * @param id_client the new id client
	 */
	public void setId_client(Long id_client) {
		this.id_client = id_client;
	}
	
	/**
	 * Gets the num client.
	 *
	 * @return the num client
	 */
	public Long getNum_client() {
		return num_client;
	}
	
	/**
	 * Sets the num client.
	 *
	 * @param num_client the new num client
	 */
	public void setNum_client(Long num_client) {
		this.num_client = num_client;
	}
	
	/**
	 * Gets the nom client.
	 *
	 * @return the nom client
	 */
	public String getNom_client() {
		return nom_client;
	}
	
	/**
	 * Sets the nom client.
	 *
	 * @param nom_client the new nom client
	 */
	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}
	
	/**
	 * Gets the packs.
	 *
	 * @return the packs
	 */
	public List<PackDTO> getPacks() {
		return packs;
	}
	
	/**
	 * Sets the packs.
	 *
	 * @param packs the new packs
	 */
	public void setPacks(List<PackDTO> packs) {
		this.packs = packs;
	}
	
	
	
	

}
