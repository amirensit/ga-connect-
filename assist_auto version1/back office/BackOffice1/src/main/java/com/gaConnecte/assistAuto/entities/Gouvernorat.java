package com.gaConnecte.assistAuto.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

// TODO: Auto-generated Javadoc
/**
 * The Class Gouvernorat.
 */
@Entity
public class Gouvernorat {
	
	/** The id gouvernorat. */
	@Id
	@GeneratedValue
	private Long id_gouvernorat;
	
	/** The nom gouvernorat. */
	private String nom_gouvernorat;
	
	/** The Villes. */
	@OneToMany(mappedBy="gouvernorat",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Ville> Villes;
	
	
	
	
	
	
	
	/**
	 * Gets the villes.
	 *
	 * @return the villes
	 */
	public List<Ville> getVilles() {
		return Villes;
	}

	/**
	 * Sets the villes.
	 *
	 * @param villes the new villes
	 */
	public void setVilles(List<Ville> villes) {
		Villes = villes;
	}

	/**
	 * Instantiates a new gouvernorat.
	 */
	public Gouvernorat() {
		super();
	}

	/**
	 * Instantiates a new gouvernorat.
	 *
	 * @param nom_gouvernorat the nom gouvernorat
	 */
	public Gouvernorat(String nom_gouvernorat) {
		super();
		this.nom_gouvernorat = nom_gouvernorat;
	}

	/**
	 * Gets the id gouvernorat.
	 *
	 * @return the id gouvernorat
	 */
	public Long getId_gouvernorat() {
		return id_gouvernorat;
	}

	/**
	 * Sets the id gouvernorat.
	 *
	 * @param id_gouvernorat the new id gouvernorat
	 */
	public void setId_gouvernorat(Long id_gouvernorat) {
		this.id_gouvernorat = id_gouvernorat;
	}

	/**
	 * Gets the nom gouvernorat.
	 *
	 * @return the nom gouvernorat
	 */
	public String getNom_gouvernorat() {
		return nom_gouvernorat;
	}

	/**
	 * Sets the nom gouvernorat.
	 *
	 * @param nom_gouvernorat the new nom gouvernorat
	 */
	public void setNom_gouvernorat(String nom_gouvernorat) {
		this.nom_gouvernorat = nom_gouvernorat;
	}
	
	

}
