package com.gaConnecte.assistAuto.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class VilleDTO.
 */
public class VilleDTO {

	/** The id ville. */
	private Long id_ville;
	
	
	/** The nom ville. */
	private String nom_ville;
	
	/** The gouvernorat DTO. */
	private GouvernoratDTO gouvernoratDTO;
	
	
	
	
	
	/**
	 * Gets the id ville.
	 *
	 * @return the id ville
	 */
	public Long getId_ville() {
		return id_ville;
	}
	
	/**
	 * Gets the gouvernorat DTO.
	 *
	 * @return the gouvernorat DTO
	 */
	public GouvernoratDTO getGouvernoratDTO() {
		return gouvernoratDTO;
	}
	
	/**
	 * Sets the gouvernorat DTO.
	 *
	 * @param gouvernoratDTO the new gouvernorat DTO
	 */
	public void setGouvernoratDTO(GouvernoratDTO gouvernoratDTO) {
		this.gouvernoratDTO = gouvernoratDTO;
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
	 * Instantiates a new ville DTO.
	 *
	 * @param id_ville the id ville
	 * @param nom_ville the nom ville
	 */
	public VilleDTO(Long id_ville, String nom_ville) {
		super();
		this.id_ville = id_ville;
		this.nom_ville = nom_ville;
	}
	
	/**
	 * Instantiates a new ville DTO.
	 *
	 * @param nom_ville the nom ville
	 */
	public VilleDTO(String nom_ville) {
		super();
		this.nom_ville = nom_ville;
	}
	
	/**
	 * Instantiates a new ville DTO.
	 */
	public VilleDTO() {
		super();
	}
	
}
