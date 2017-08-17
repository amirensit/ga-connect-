package com.gaConnecte.assistAuto.service;

import java.util.List;

import com.gaConnecte.assistAuto.dto.ClientDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.entities.Pack;

// TODO: Auto-generated Javadoc
/**
 * The Interface PackService.
 */
public interface PackService {
	
	/**
	 * Convert to DTO.
	 *
	 * @param entity the entity
	 * @return the pack DTO
	 */
	public PackDTO convertToDTO(Pack entity) ;
	
	/**
	 * Convert to entity.
	 *
	 * @param pd the pd
	 * @return the pack
	 */
	public Pack convertToEntity(PackDTO pd);
	
	 /**
 	 * Convert to DTO.
 	 *
 	 * @param listPack the list pack
 	 * @return the list
 	 */
 	public List<PackDTO> convertToDTO(List<Pack> listPack);
	 
	 /**
 	 * Convert to entity.
 	 *
 	 * @param listPackDTO the list pack DTO
 	 * @return the list
 	 */
 	public List<Pack> convertToEntity(List<PackDTO> listPackDTO);
	 
	 /**
 	 * Adds the pack.
 	 *
 	 * @param pDTO the dto
 	 * @return the pack DTO
 	 */
 	public PackDTO addPack(PackDTO pDTO);
	 
	 /**
 	 * Convert list to DTO.
 	 *
 	 * @param m the m
 	 * @return the list
 	 */
 	public List<PackDTO> convertListToDTO(List<Pack> m);
	 
	 /**
 	 * Edits the pack.
 	 *
 	 * @param pDTO the dto
 	 * @param id the id
 	 * @return the pack DTO
 	 */
 	public PackDTO editPack(PackDTO pDTO, Long id);
	 
	 /**
 	 * Delete pack.
 	 *
 	 * @param id the id
 	 */
 	public void deletePack(Long id);
	 
	 /**
 	 * Enable etat pack.
 	 *
 	 * @param id the id
 	 */
 	public void enableEtatPack(Long id);
	 
	 /**
 	 * Disable etat pack.
 	 *
 	 * @param id the id
 	 */
 	public void disableEtatPack(Long id);
	 
	 /**
 	 * List pack.
 	 *
 	 * @return the list
 	 */
 	public List<Pack> listPack();
	
	

}
