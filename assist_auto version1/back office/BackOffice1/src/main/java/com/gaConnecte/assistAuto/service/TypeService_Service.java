package com.gaConnecte.assistAuto.service;



import java.util.List;

import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.TypeServiceDTO;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.TypeService;

// TODO: Auto-generated Javadoc
/**
 * The Interface TypeService_Service.
 */
public interface TypeService_Service {
	
	/**
	 * Adds the type service.
	 *
	 * @param ts the ts
	 * @return the type service
	 */
	public TypeService addTypeService(TypeService ts);
	
	/**
	 * Delete type service.
	 *
	 * @param id the id
	 */
	public void deleteTypeService(Long id);
	
	/**
	 * Edits the type service.
	 *
	 * @param pDTO the dto
	 * @param id the id
	 * @return the type service
	 */
	public TypeService editTypeService(TypeService pDTO, Long id);
	
	/**
	 * Convert to entity.
	 *
	 * @param typrServiceDTO the typr service DTO
	 * @return the type service
	 */
	public TypeService convertToEntity (TypeServiceDTO typrServiceDTO);
	
	/**
	 * Convert to DTO.
	 *
	 * @param typrService the typr service
	 * @return the type service DTO
	 */
	public TypeServiceDTO convertToDTO (TypeService typrService);
	
	/**
	 * Convert to DTO.
	 *
	 * @param listTypeService the list type service
	 * @return the list
	 */
	public List<TypeServiceDTO> convertToDTO(List<TypeService> listTypeService);
	
	/**
	 * List type service.
	 *
	 * @return the list
	 */
	public List<TypeService> listTypeService();

}
