package com.gaConnecte.assistAuto.service;

import java.util.List;

import com.gaConnecte.assistAuto.dto.ContratDTO;
import com.gaConnecte.assistAuto.entities.Contrat;


public interface ContratService {
	
	
	public Contrat convertToEntity(ContratDTO cDTO);
	
	
	public ContratDTO convertToDTO(Contrat c);
	
	
	public List<Contrat> convertListToEntity(List<ContratDTO> listContratDTO);
	
	
	public List<ContratDTO> convertListToDTO(List<Contrat> listContrat);
	
	
	public ContratDTO addContrat(ContratDTO cDTO);
	
	
	public ContratDTO editContrat (ContratDTO cDTO,Long id);
	
	
	public List<ContratDTO> listContratDTO();
	
}
