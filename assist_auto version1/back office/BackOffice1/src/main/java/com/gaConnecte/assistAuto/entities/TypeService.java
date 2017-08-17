package com.gaConnecte.assistAuto.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class TypeService.
 */
@Entity
public class TypeService  implements Serializable{

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The id type service. */
	@Id @GeneratedValue
	private Long id_typeService;
	
	/** The nom type service. */
	private String nom_typeService;
	
	/**
	 * Instantiates a new type service.
	 */
	public TypeService() {
		super();
	}
	
	/**
	 * Instantiates a new type service.
	 *
	 * @param nom_typeService the nom type service
	 */
	public TypeService(String nom_typeService) {
		super();
		this.nom_typeService = nom_typeService;
	}
	
	/**
	 * Gets the id type service.
	 *
	 * @return the id type service
	 */
	public Long getId_typeService() {
		return id_typeService;
	}
	
	/**
	 * Sets the id type service.
	 *
	 * @param id_typeService the new id type service
	 */
	public void setId_typeService(Long id_typeService) {
		this.id_typeService = id_typeService;
	}
	
	/**
	 * Gets the nom type service.
	 *
	 * @return the nom type service
	 */
	public String getNom_typeService() {
		return nom_typeService;
	}
	
	/**
	 * Sets the nom type service.
	 *
	 * @param nom_typeService the new nom type service
	 */
	public void setNom_typeService(String nom_typeService) {
		this.nom_typeService = nom_typeService;
	}
	
	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	


}
