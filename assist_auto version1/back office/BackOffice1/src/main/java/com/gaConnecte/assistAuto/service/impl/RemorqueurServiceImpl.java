package com.gaConnecte.assistAuto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaConnecte.assistAuto.daos.RemorqueurRepository;
import com.gaConnecte.assistAuto.dto.ClientDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.dto.RemorqueurDTO;
import com.gaConnecte.assistAuto.dto.VilleDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.entities.Contrat;
import com.gaConnecte.assistAuto.entities.Pack;
import com.gaConnecte.assistAuto.entities.Remorqueur;
import com.gaConnecte.assistAuto.entities.Ville;
import com.gaConnecte.assistAuto.service.RemorqueurService;
import com.gaConnecte.assistAuto.service.VilleService;


// TODO: Auto-generated Javadoc
/**
 * The Class RemorqueurServiceImpl.
 */
@Transactional
@Service
public class RemorqueurServiceImpl implements RemorqueurService {
	
	/**
	 * Model mapper.
	 *
	 * @return the model mapper
	 */
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();
	
	/** The ville service. */
	@Autowired
	private VilleService villeService;
	
	
	
	/** The remorqueur repository. */
	@Autowired
	private RemorqueurRepository remorqueurRepository;
	

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.RemorqueurService#convertToEntity(com.gaConnecte.assistAuto.dto.RemorqueurDTO)
	 */
	@Override
	public Remorqueur convertToEntity(RemorqueurDTO rDTO) {
		
		
		VilleDTO vDTO=null;
		vDTO=rDTO.getVilleDTO();
		Ville v=villeService.convertToEntity(vDTO);
		Remorqueur remorqueur = modelMapper.map(rDTO, Remorqueur.class);
		//Remorqueur r = new Remorqueur(rDTO.getRaison_social(),rDTO.getMatricule_fiscal(),rDTO.getTel(),rDTO.getNom_contact(),rDTO.getPrenom_contact() );
		remorqueur.setVille(v);
		return remorqueur;
		
		 }


	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.RemorqueurService#addRemorqueur(com.gaConnecte.assistAuto.dto.RemorqueurDTO)
	 */
	@Override
	public RemorqueurDTO addRemorqueur(RemorqueurDTO rDTO) {
		Remorqueur remorqueur = convertToEntity(rDTO);
		remorqueurRepository.save(remorqueur);
		return rDTO;
		
	}


	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.RemorqueurService#enableEtatRemorqueur(java.lang.Long)
	 */
	@Override
	public void enableEtatRemorqueur(Long id) {
		
		remorqueurRepository.enableEtatRemorqueur(id);
		
	}


	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.RemorqueurService#disableEtatRemorqueur(java.lang.Long)
	 */
	@Override
	public void disableEtatRemorqueur(Long id) {
		remorqueurRepository.disableEtatRemorqueur(id);
		
	}
	

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.RemorqueurService#deleteRemorqueur(java.lang.Long)
	 */
	@Override
	public void deleteRemorqueur(Long id) {
		remorqueurRepository.delete(id);
		
	}


	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.RemorqueurService#editRemorqueur(com.gaConnecte.assistAuto.dto.RemorqueurDTO, java.lang.Long)
	 */
	@Override
	public RemorqueurDTO editRemorqueur(RemorqueurDTO cDTO, Long id) {
		cDTO.setId_remorqueur(id);
		Remorqueur remorqueur = new Remorqueur();
		remorqueur=convertToEntity(cDTO); 
		remorqueurRepository.saveAndFlush(remorqueur);
		return cDTO;
	}


	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.RemorqueurService#listRemorqueurDTO()
	 */
	@Override
	public List<RemorqueurDTO> listRemorqueurDTO() {
		
		List<Remorqueur> listRemorqueur=remorqueurRepository.findAll();
		List<RemorqueurDTO> listRemorqueurDTO = convertlistDTO(listRemorqueur);
		return listRemorqueurDTO;
		
	}


	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.RemorqueurService#convertlistDTO(java.util.List)
	 */
	@Override
	public List<RemorqueurDTO> convertlistDTO(List<Remorqueur> listRemorqueur) {
		
		List<RemorqueurDTO> listRemorqueurDTO = new ArrayList<RemorqueurDTO>();
		RemorqueurDTO remorqueurDTO = null;
		for (Remorqueur remorqueur : listRemorqueur) {
			Ville ville=remorqueur.getVille();
			VilleDTO villeDTO= villeService.convertToDTO(ville);
			
			remorqueurDTO = convertToDTO(remorqueur);
			
			remorqueurDTO.setVilleDTO(villeDTO);
			listRemorqueurDTO.add(remorqueurDTO);
		}
		return listRemorqueurDTO;
		
		
	}


	
	@Override
	public RemorqueurDTO convertToDTO(Remorqueur remorqueur) {
		RemorqueurDTO rDTO= modelMapper.map(remorqueur, RemorqueurDTO.class);
		Ville ville = remorqueur.getVille();
		VilleDTO vDTO= villeService.convertToDTO(ville);
		rDTO.setVilleDTO(vDTO);
		return rDTO;
	}
	
	
	
	

}
