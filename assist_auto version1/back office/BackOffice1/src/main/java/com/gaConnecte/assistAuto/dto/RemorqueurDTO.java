package com.gaConnecte.assistAuto.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class RemorqueurDTO.
 */
public class RemorqueurDTO {

	/** The id remorqueur. */
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
	
	/** The ville DTO. */
	private VilleDTO villeDTO;
	
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
	 * Gets the ville DTO.
	 *
	 * @return the ville DTO
	 */
	public VilleDTO getVilleDTO() {
		return villeDTO;
	}
	
	/**
	 * Sets the ville DTO.
	 *
	 * @param villeDTO the new ville DTO
	 */
	public void setVilleDTO(VilleDTO villeDTO) {
		this.villeDTO = villeDTO;
	}
	
	
}
