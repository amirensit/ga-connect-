package com.gaConnecte.assistAuto.entities;

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
 * The Class Ville.
 */
@Entity
public class Ville {

	
	/** The id ville. */
	@Id
	@GeneratedValue
	private Long id_ville;
	
	/** The nom ville. */
	private String nom_ville;
	
	/** The contrats. */
	@OneToMany(mappedBy="ville",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Contrat> contrats;
	
	
	/** The remorqueurs. */
	@OneToMany(mappedBy="ville",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Remorqueur> remorqueurs;
	
	/** The gouvernorat. */
	@ManyToOne
	@JoinColumn(name="CODE_GOUVERNORAT")
	private Gouvernorat gouvernorat;
	
	
	
	

	/**
	 * Instantiates a new ville.
	 *
	 * @param nom_ville the nom ville
	 */
	public Ville(String nom_ville) {
		super();
		this.nom_ville = nom_ville;
	}

	/**
	 * Gets the gouvernorat.
	 *
	 * @return the gouvernorat
	 */
	public Gouvernorat getGouvernorat() {
		return gouvernorat;
	}

	/**
	 * Sets the gouvernorat.
	 *
	 * @param gouvernorat the new gouvernorat
	 */
	public void setGouvernorat(Gouvernorat gouvernorat) {
		this.gouvernorat = gouvernorat;
	}

	/**
	 * Instantiates a new ville.
	 */
	public Ville() {
		super();
	}

	/**
	 * Gets the id ville.
	 *
	 * @return the id ville
	 */
	public Long getId_ville() {
		return id_ville;
	}

	/**
	 * Sets the id ville.
	 *
	 * @param id_ville the new id ville
	 */
	public void setId_ville(Long id_ville) {
		this.id_ville = id_ville;
	}

	/**
	 * Gets the nom ville.
	 *
	 * @return the nom ville
	 */
	public String getNom_ville() {
		return nom_ville;
	}

	/**
	 * Sets the nom ville.
	 *
	 * @param nom_ville the new nom ville
	 */
	public void setNom_ville(String nom_ville) {
		this.nom_ville = nom_ville;
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

	/**
	 * Gets the remorqueurs.
	 *
	 * @return the remorqueurs
	 */
	public List<Remorqueur> getRemorqueurs() {
		return remorqueurs;
	}

	/**
	 * Sets the remorqueurs.
	 *
	 * @param remorqueurs the new remorqueurs
	 */
	public void setRemorqueurs(List<Remorqueur> remorqueurs) {
		this.remorqueurs = remorqueurs;
	}

	/**
	 * Instantiates a new ville.
	 *
	 * @param id_ville the id ville
	 * @param nom_ville the nom ville
	 * @param contrats the contrats
	 */
	public Ville(Long id_ville, String nom_ville, List<Contrat> contrats) {
		super();
		this.id_ville = id_ville;
		this.nom_ville = nom_ville;
		this.contrats = contrats;
	}

	/**
	 * Instantiates a new ville.
	 *
	 * @param id_ville the id ville
	 * @param nom_ville the nom ville
	 * @param contrats the contrats
	 * @param remorqueurs the remorqueurs
	 */
	public Ville(Long id_ville, String nom_ville, List<Contrat> contrats, List<Remorqueur> remorqueurs) {
		super();
		this.id_ville = id_ville;
		this.nom_ville = nom_ville;
		this.contrats = contrats;
		this.remorqueurs = remorqueurs;
	}

	/**
	 * Instantiates a new ville.
	 *
	 * @param id_ville the id ville
	 * @param nom_ville the nom ville
	 */
	public Ville(Long id_ville, String nom_ville) {
		super();
		this.id_ville = id_ville;
		this.nom_ville = nom_ville;
	}
	
	


	
	

	
	
	

	
}
