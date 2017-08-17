package com.gaConnecte.assistAuto.service;

import java.util.List;

import com.gaConnecte.assistAuto.dto.ClientDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.entities.Pack;

// TODO: Auto-generated Javadoc
/**
 * The Interface ClientService.
 */
public interface ClientService {
	
	/**
	 * Convert to entity.
	 *
	 * @param clientDTO the client DTO
	 * @return the client
	 */
	public Client convertToEntity (ClientDTO clientDTO);
	
	/**
	 * Convert to entity for edit.
	 *
	 * @param clientDTO the client DTO
	 * @return the client
	 */
	public Client convertToEntityForEdit(ClientDTO clientDTO);
	
	/**
	 * Convert to DTO.
	 *
	 * @param client the client
	 * @return the client DTO
	 */
	public ClientDTO convertToDTO (Client client);
	
	/**
	 * Convert to entity.
	 *
	 * @param listClientDTO the list client DTO
	 * @return the list
	 */
	//public List<ClientDTO> convertToDTO(List<Client> listClient);
	public List<Client> convertToEntity(List<ClientDTO> listClientDTO);
	
	/**
	 * Adds the client.
	 *
	 * @param cDTO the c DTO
	 * @return the client DTO
	 */
	public ClientDTO addClient(ClientDTO cDTO);
	
	/**
	 * List client.
	 *
	 * @return the list
	 */
	public List<Client>listClient();
	
	/**
	 * Convert to DTO.
	 *
	 * @param r the r
	 * @return the list
	 */
	public List<ClientDTO> convertToDTO(List<Client> r) ;
	
	/**
	 * Edits the client.
	 *
	 * @param cDTO the c DTO
	 * @param id the id
	 * @return the client DTO
	 */
	public ClientDTO editClient(ClientDTO cDTO,Long id);
	
	/**
	 * Delete client.
	 *
	 * @param id the id
	 */
	public void deleteClient(Long id);
	
	/**
	 * Enable etat client.
	 *
	 * @param id the id
	 */
	public void enableEtatClient(Long id);
	
	/**
	 * Disable etat client.
	 *
	 * @param id the id
	 */
	public void disableEtatClient(Long id);
	
}
