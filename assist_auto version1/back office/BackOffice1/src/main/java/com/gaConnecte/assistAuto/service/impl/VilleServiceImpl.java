package com.gaConnecte.assistAuto.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.gaConnecte.assistAuto.daos.VilleRepository;
import com.gaConnecte.assistAuto.dto.GouvernoratDTO;
import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.VilleDTO;
import com.gaConnecte.assistAuto.entities.Gouvernorat;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.Ville;
import com.gaConnecte.assistAuto.service.VilleService;

@Transactional
@Service
public class VilleServiceImpl implements VilleService {

	
	@Autowired
	private VilleRepository villeRepository;
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	/** The model mapper. */
	private ModelMapper modelMapper = new ModelMapper();
	
	
	@Override
	public Ville convertToEntity(VilleDTO vDTO) {
		Ville v =modelMapper.map(vDTO, Ville.class);
		//Ville v =new Ville(vDTO.getNom_ville());
		return v;
	}

		
	@Override
	public VilleDTO addVille(VilleDTO vDTO) {
		
		Ville v=convertToEntity(vDTO);
		villeRepository.save(v);
		return vDTO;
	}

	
	@Override
	public VilleDTO convertToDTO(Ville ville) {
		
		VilleDTO vDTO= modelMapper.map(ville, VilleDTO.class);
		Gouvernorat gouvernorat = ville.getGouvernorat();
		GouvernoratDTO gDTO = modelMapper.map(gouvernorat, GouvernoratDTO.class);
		vDTO.setGouvernoratDTO(gDTO);
		return vDTO;
		
	}

	
	@Override
	public void deleteVille(Long id_ville) {
		villeRepository.delete(id_ville);
		
	}


	@Override
	public List<VilleDTO> convertToDTO(List<Ville> listVille) {
		List<VilleDTO> listVilleDTO=new ArrayList<VilleDTO>();
		for (Ville entity : listVille) {
			VilleDTO p = convertToDTO(entity);
			listVilleDTO.add(p);
		}
		return listVilleDTO;
	}

}
