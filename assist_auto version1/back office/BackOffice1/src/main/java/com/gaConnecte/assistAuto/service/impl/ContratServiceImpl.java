package com.gaConnecte.assistAuto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.gaConnecte.assistAuto.daos.ContratRepository;
import com.gaConnecte.assistAuto.daos.MarqueRepository;
import com.gaConnecte.assistAuto.daos.PackRepository;
import com.gaConnecte.assistAuto.dto.ContratDTO;
import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.dto.RemorqueurDTO;
import com.gaConnecte.assistAuto.dto.VilleDTO;
import com.gaConnecte.assistAuto.entities.Contrat;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.Pack;
import com.gaConnecte.assistAuto.entities.Remorqueur;
import com.gaConnecte.assistAuto.entities.Ville;
import com.gaConnecte.assistAuto.service.ContratService;
import com.gaConnecte.assistAuto.service.MarqueService;
import com.gaConnecte.assistAuto.service.PackService;
import com.gaConnecte.assistAuto.service.VilleService;


@Service
public class ContratServiceImpl implements ContratService {
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	@Autowired
	private PackService packService;
	
	
	@Autowired
	private MarqueService marqueService;
	
	
	@Autowired
	private ContratRepository contratRepository;
	
	
	@Autowired
	private PackRepository  packRepository;

	
	@Autowired
	private VilleService villeService;
	
	
	@Autowired
	private MarqueRepository marqueRepository;
	
	
	
	
	
	@Override
	public ContratDTO convertToDTO(Contrat c) {
		
		ContratDTO cDTO = modelMapper.map(c, ContratDTO.class);
		Ville v=c.getVille();
		Marque m = c.getMarque();
		Pack p = c.getPack();
		VilleDTO vDTO=villeService.convertToDTO(v);
		MarqueDTO mDTO=marqueService.convertToDTO(m);
		PackDTO pDTO=packService.convertToDTO(p);
		cDTO.setVilleDTO(vDTO);
		cDTO.setMarqueDTO(mDTO);
		cDTO.setPackDTO(pDTO);
		
		return cDTO;
	}
	
	
	
	@Override
	public Contrat convertToEntity(ContratDTO cDTO) {
		PackDTO pDTO=null;
		pDTO=cDTO.getPackDTO();
		MarqueDTO mDTO=null;
		mDTO=cDTO.getMarqueDTO();
		VilleDTO vDTO=null;
		 vDTO=cDTO.getVilleDTO();
		Ville v=villeService.convertToEntity(vDTO);
		Pack p = packService.convertToEntity(pDTO) ;
		Marque m= marqueService.convertToEntity(mDTO);
		Contrat r=modelMapper.map(cDTO, Contrat.class);
		r.setPack(p);
		r.setMarque(m);
		r.setVille(v);
		
		return r;
	}


	
	@Override
	public List<Contrat> convertListToEntity(List<ContratDTO> listContratDTO) {
		List<Contrat> listContrat=new ArrayList<Contrat>();
		for (ContratDTO entity : listContratDTO) {
			Contrat p = convertToEntity(entity);
			listContrat.add(p);
		}
		return listContrat;
	}


	
	@Override
	public List<ContratDTO> convertListToDTO(List<Contrat> listContrat) {
		
		
		List<ContratDTO> listContratDTO = new ArrayList<ContratDTO>();
		ContratDTO contratDTO = null;
		for (Contrat contrat : listContrat) {
			Ville ville=contrat.getVille();
			VilleDTO villeDTO= villeService.convertToDTO(ville);
			Pack pack=contrat.getPack();
			Marque marque=contrat.getMarque();
			PackDTO packDTO=packService.convertToDTO(pack);
			
			MarqueDTO marqueDTO=marqueService.convertToDTO(marque);
			
			contratDTO = convertToDTO(contrat);
			
			contratDTO.setVilleDTO(villeDTO);
			contratDTO.setMarqueDTO(marqueDTO);
			contratDTO.setPackDTO(packDTO);
			listContratDTO.add(contratDTO);
		}
		return listContratDTO;
				
		
	}


	
	@Override
	public ContratDTO addContrat(ContratDTO cDTO) {
		
		Contrat c=convertToEntity(cDTO);
		
		contratRepository.save(c);
	
		return cDTO;
		}

	@Override
	public ContratDTO editContrat(ContratDTO cDTO, Long id) {
		cDTO.setId_contrat(id);
		Contrat c = new Contrat();
		c= convertToEntity(cDTO);
		contratRepository.saveAndFlush(c);
		return cDTO;
	}


	@Override
	public List<ContratDTO> listContratDTO() {
		List<Contrat> listContrat=contratRepository.findAll();
		List<ContratDTO> listContratDTO = convertListToDTO(listContrat);
		return listContratDTO;
	}


	

	


}
