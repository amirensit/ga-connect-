package com.gaConnecte.assistAuto.service;


import java.util.List;

import com.gaConnecte.assistAuto.dto.ClientDTO;
import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.Pack;


// TODO: Auto-generated Javadoc
/**
 * The Interface MarqueService.
 */
public interface MarqueService {
	
	/**
	 * Convert to entity.
	 *
	 * @param marqueDTO the marque DTO
	 * @return the marque
	 */
	public Marque convertToEntity (MarqueDTO marqueDTO);
	
	/**
	 * Convert to DTO.
	 *
	 * @param marque the marque
	 * @return the marque DTO
	 */
	public MarqueDTO convertToDTO (Marque marque);
	
	/**
	 * Adds the marque.
	 *
	 * @param pDTO the dto
	 * @return the marque DTO
	 */
	public MarqueDTO addMarque(MarqueDTO pDTO);
	
	/**
	 * Edits the marque.
	 *
	 * @param pDTO the dto
	 * @param id the id
	 * @return the marque DTO
	 */
	public MarqueDTO editMarque(MarqueDTO pDTO, Long id);
	
	/**
	 * Convert to entity for edit.
	 *
	 * @param marqueDTO the marque DTO
	 * @return the marque
	 */
	public Marque convertToEntityForEdit(MarqueDTO marqueDTO);
	
	/**
	 * Convert to DTO.
	 *
	 * @param listMarque the list marque
	 * @return the list
	 */
	public List<MarqueDTO> convertToDTO(List<Marque> listMarque);
	
	/**
	 * List marque.
	 *
	 * @return the list
	 */
	public List<Marque> listMarque();
	 
 	/**
 	 * Delete marque.
 	 *
 	 * @param id the id
 	 */
 	public void deleteMarque(Long id);


}
