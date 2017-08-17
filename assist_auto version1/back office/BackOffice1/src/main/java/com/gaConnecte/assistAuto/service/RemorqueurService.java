package com.gaConnecte.assistAuto.service;

import java.util.List;

import com.gaConnecte.assistAuto.dto.ContratDTO;
import com.gaConnecte.assistAuto.dto.RemorqueurDTO;
import com.gaConnecte.assistAuto.entities.Remorqueur;

// TODO: Auto-generated Javadoc
/**
 * The Interface RemorqueurService.
 */
public interface RemorqueurService {
	
	
	/**
	 * Convert to entity.
	 *
	 * @param rDTO the r DTO
	 * @return the remorqueur
	 */
	public Remorqueur convertToEntity(RemorqueurDTO rDTO);
	
	/**
	 * Convert to DTO.
	 *
	 * @param remorqueur the remorqueur
	 * @return the remorqueur DTO
	 */
	public RemorqueurDTO convertToDTO(Remorqueur remorqueur);
	
	/**
	 * Convertlist DTO.
	 *
	 * @param listRemorqueur the list remorqueur
	 * @return the list
	 */
	public List<RemorqueurDTO> convertlistDTO( List<Remorqueur> listRemorqueur);
	
	/**
	 * Adds the remorqueur.
	 *
	 * @param rDTO the r DTO
	 * @return the remorqueur DTO
	 */
	public RemorqueurDTO addRemorqueur (RemorqueurDTO rDTO);
	
	/**
	 * Enable etat remorqueur.
	 *
	 * @param id the id
	 */
	public void enableEtatRemorqueur(Long id);
	
	/**
	 * Disable etat remorqueur.
	 *
	 * @param id the id
	 */
	public void disableEtatRemorqueur(Long id);
	
	/**
	 * Delete remorqueur.
	 *
	 * @param id the id
	 */
	public void deleteRemorqueur(Long id);
	
	/**
	 * Edits the remorqueur.
	 *
	 * @param cDTO the c DTO
	 * @param id the id
	 * @return the remorqueur DTO
	 */
	public RemorqueurDTO editRemorqueur (RemorqueurDTO cDTO,Long id);
	
	/**
	 * List remorqueur DTO.
	 *
	 * @return the list
	 */
	public List<RemorqueurDTO> listRemorqueurDTO();

}
