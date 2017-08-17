package com.gaConnecte.assistAuto.service;

import java.util.List;

import com.gaConnecte.assistAuto.dto.GouvernoratDTO;
import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.entities.Gouvernorat;
import com.gaConnecte.assistAuto.entities.Marque;

// TODO: Auto-generated Javadoc
/**
 * The Interface GouvernoratService.
 */
public interface GouvernoratService {
	
	/**
	 * Convert to entity.
	 *
	 * @param gDTO the g DTO
	 * @return the gouvernorat
	 */
	public Gouvernorat convertToEntity(GouvernoratDTO gDTO);
	
	/**
	 * Convert to DTO.
	 *
	 * @param gouvernorat the gouvernorat
	 * @return the gouvernorat DTO
	 */
	public GouvernoratDTO convertToDTO(Gouvernorat gouvernorat);
	
	/**
	 * Adds the gouvernorat.
	 *
	 * @param gDTO the g DTO
	 * @return the gouvernorat DTO
	 */
	public GouvernoratDTO addGouvernorat(GouvernoratDTO gDTO);
	
	/**
	 * Delete gouvernorat.
	 *
	 * @param id_gouvernorat the id gouvernorat
	 */
	public void deleteGouvernorat(Long id_gouvernorat);
	
	/**
	 * Convert to DTO.
	 *
	 * @param listGouvernorat the list gouvernorat
	 * @return the list
	 */
	public List<GouvernoratDTO> convertToDTO(List<Gouvernorat> listGouvernorat);
	
	/**
	 * List gouvernorat.
	 *
	 * @return the list
	 */
	public List<Gouvernorat> listGouvernorat() ;
}


