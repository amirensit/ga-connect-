package com.gaConnecte.assistAuto.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.gaConnecte.assistAuto.daos.GouvernoratRepository;

import com.gaConnecte.assistAuto.dto.GouvernoratDTO;
import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.entities.Gouvernorat;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.service.GouvernoratService;
// TODO: Auto-generated Javadoc

/**
 * The Class GouvernoratServiceImpl.
 */
@Transactional
@Service
public class GouvernoratServiceImpl implements GouvernoratService {
	
	
	/** The gouvernorat repository. */
	@Autowired
	private GouvernoratRepository gouvernoratRepository;
	
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

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.GouvernoratService#convertToEntity(com.gaConnecte.assistAuto.dto.GouvernoratDTO)
	 */
	@Override
	public Gouvernorat convertToEntity(GouvernoratDTO gDTO) {
		Gouvernorat gouvernorat =modelMapper.map(gDTO, Gouvernorat.class);
		
		return gouvernorat;
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.GouvernoratService#convertToDTO(com.gaConnecte.assistAuto.entities.Gouvernorat)
	 */
	@Override
	public GouvernoratDTO convertToDTO(Gouvernorat gouvernorat) {
		return modelMapper.map(gouvernorat, GouvernoratDTO.class);
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.GouvernoratService#addGouvernorat(com.gaConnecte.assistAuto.dto.GouvernoratDTO)
	 */
	@Override
	public GouvernoratDTO addGouvernorat(GouvernoratDTO gDTO) {
		Gouvernorat v=convertToEntity(gDTO);
		gouvernoratRepository.save(v);
		return gDTO;
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.GouvernoratService#deleteGouvernorat(java.lang.Long)
	 */
	@Override
	public void deleteGouvernorat(Long id_gouvernorat) {
		gouvernoratRepository.delete(id_gouvernorat);
		
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.GouvernoratService#convertToDTO(java.util.List)
	 */
	@Override
	public List<GouvernoratDTO> convertToDTO(List<Gouvernorat> listGouvernorat) {
		List<GouvernoratDTO> listGouvernoratDTO=new ArrayList<GouvernoratDTO>();
		for (Gouvernorat entity : listGouvernorat) {
			GouvernoratDTO p = convertToDTO(entity);
			listGouvernoratDTO.add(p);
		}
		return listGouvernoratDTO;
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.GouvernoratService#listGouvernorat()
	 */
	@Override
	public List<Gouvernorat> listGouvernorat() {
		 return gouvernoratRepository.findAll();
	}

}
