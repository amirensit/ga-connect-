package com.gaConnecte.assistAuto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import com.gaConnecte.assistAuto.daos.MarqueRepository;

import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.entities.Contrat;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.Pack;

import com.gaConnecte.assistAuto.service.MarqueService;

@Service
public class MarqueServiceImpl implements MarqueService {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	private ModelMapper modelMapper = new ModelMapper();
	
	
	
	
	
	@Autowired
	private MarqueRepository marqueRepository;
	 
	/*@Override
	public Marque convertToEntity(MarqueDTO marqueDTO) {
	
		if (marqueDTO.getContras()==null)
		{
			 return modelMapper.map(marqueDTO, Marque.class);
		}
		
		Marque marque =new Marque(marqueDTO.getId_marque(),marqueDTO.getNom_marque());
		List<Contrat> l =new ArrayList<Contrat>();
		for ( ContratDTO r: marqueDTO.getContras() ) 
		{
		Contrat rdto=contratService.convertToEntity(r);
		l.add(rdto);
		}
		marque.setContrats(l);
		return marque;
	}*/

	
	@Override
	public Marque convertToEntity(MarqueDTO marqueDTO)
	{
		return modelMapper.map(marqueDTO, Marque.class);
	}
	
	
/*	@Override
	public MarqueDTO convertToDTO(Marque marque) {
		MarqueDTO objDto = modelMapper.map(marque, MarqueDTO.class);
		if(marque.getContrats() ==null ) return objDto;
		List<ContratDTO> l =new ArrayList<ContratDTO>();
		for (Contrat r: marque.getContrats()) 
		{
			ContratDTO rdto=contratService.convertToDTO(r);
		
		l.add(rdto);
		
		}
		objDto.setContras(l);
		
		return objDto;
}*/
	
	@Override
	public MarqueDTO convertToDTO(Marque marque) {
		MarqueDTO objDto = modelMapper.map(marque, MarqueDTO.class);
		return objDto;
	}




	@Override
	public MarqueDTO addMarque(MarqueDTO pDTO) {
			
		Marque p = convertToEntity(pDTO);
		
		
		marqueRepository.save(p);
		return pDTO;
	}




	@Override
	public MarqueDTO editMarque(MarqueDTO pDTO, Long id) {
		
		pDTO.setId_marque(id);
		Marque c = new Marque();
		c=convertToEntityForEdit(pDTO);
		marqueRepository.saveAndFlush(c);
		return pDTO;
	}


/*

	@Override
	public Marque convertToEntityForEdit(MarqueDTO marqueDTO) {
		if(marqueDTO.getContras()== null) return modelMapper.map(marqueDTO, Marque.class);
		
		Marque marque =new Marque(marqueDTO.getId_marque(),marqueDTO.getNom_marque());
		List<Contrat> l =new ArrayList<Contrat>();
		for ( ContratDTO r: marqueDTO.getContras() ) 
		{
			Contrat rdto=contratService.convertToEntity(r);
			l.add(rdto);
		}
		marque.setContrats(l);
		return marque;
	}*/
	
	/* (non-Javadoc)
 * @see com.gaConnecte.assistAuto.service.MarqueService#convertToEntityForEdit(com.gaConnecte.assistAuto.dto.MarqueDTO)
 */
@Override
	public Marque convertToEntityForEdit(MarqueDTO marqueDTO) {
		Marque marque =new Marque(marqueDTO.getId_marque(),marqueDTO.getNom_marque());
		return marque;
	}


	@Override
	public List<Marque> listMarque() {
		return marqueRepository.findAll();
	}


	@Override
	public List<MarqueDTO> convertToDTO(List<Marque> listMarque) {
		List<MarqueDTO> listMarqueDTO=new ArrayList<MarqueDTO>();
		for (Marque entity : listMarque) {
			MarqueDTO p = convertToDTO(entity);
			listMarqueDTO.add(p);
		}
		return listMarqueDTO;
	}


	@Override
	public void deleteMarque(Long id) {
		marqueRepository.delete(id);
		
	}
	
	
	
}
