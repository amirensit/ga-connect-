package com.gaConnecte.assistAuto.service;

import java.util.List;

import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.VilleDTO;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.Ville;

// TODO: Auto-generated Javadoc
/**
 * The Interface VilleService.
 */
public interface VilleService {

	/**
	 * Convert to entity.
	 *
	 * @param vDTO the v DTO
	 * @return the ville
	 */
	//public VilleDTO convertToDto(Ville v);
	public Ville convertToEntity(VilleDTO vDTO);
	
	/**
	 * Convert to DTO.
	 *
	 * @param ville the ville
	 * @return the ville DTO
	 */
	public VilleDTO convertToDTO(Ville ville);
	
	/**
	 * Adds the ville.
	 *
	 * @param vDTO the v DTO
	 * @return the ville DTO
	 */
	public VilleDTO addVille(VilleDTO vDTO);
	
	/**
	 * Delete ville.
	 *
	 * @param id_ville the id ville
	 */
	public void deleteVille(Long id_ville);
	
	/**
	 * Convert to DTO.
	 *
	 * @param listVille the list ville
	 * @return the list
	 */
	public List<VilleDTO> convertToDTO(List<Ville> listVille);
	
}
