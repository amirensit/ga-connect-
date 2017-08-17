package com.gaConnecte.assistAuto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaConnecte.assistAuto.daos.TypeServiceRepository;
import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.TypeServiceDTO;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.TypeService;
import com.gaConnecte.assistAuto.service.TypeService_Service;

// TODO: Auto-generated Javadoc
/**
 * The Class TypeService_ServiceImpl.
 */
@Transactional
@Service
public class TypeService_ServiceImpl implements TypeService_Service {

	/** The type service repository. */
	@Autowired
	private TypeServiceRepository typeServiceRepository;
	
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
	 * @see com.gaConnecte.assistAuto.service.TypeService_Service#addTypeService(com.gaConnecte.assistAuto.entities.TypeService)
	 */
	@Override
	public TypeService addTypeService(TypeService ts) {
		
		return typeServiceRepository.save(ts);
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.TypeService_Service#deleteTypeService(java.lang.Long)
	 */
	@Override
	public void deleteTypeService(Long id) {
		typeServiceRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.TypeService_Service#editTypeService(com.gaConnecte.assistAuto.entities.TypeService, java.lang.Long)
	 */
	@Override
	public TypeService editTypeService(TypeService pDTO, Long id) {
		
		pDTO.setId_typeService(id);  
		return typeServiceRepository.saveAndFlush(pDTO);
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.TypeService_Service#convertToEntity(com.gaConnecte.assistAuto.dto.TypeServiceDTO)
	 */
	@Override
	public TypeService convertToEntity(TypeServiceDTO marqueDTO) {
		return modelMapper.map(marqueDTO, TypeService.class);
		
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.TypeService_Service#convertToDTO(com.gaConnecte.assistAuto.entities.TypeService)
	 */
	@Override
	public TypeServiceDTO convertToDTO(TypeService typeService) {
		TypeServiceDTO objDto = modelMapper.map(typeService, TypeServiceDTO.class);
		return objDto;
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.TypeService_Service#convertToDTO(java.util.List)
	 */
	@Override
	public List<TypeServiceDTO> convertToDTO(List<TypeService> listTypeService) {
		List<TypeServiceDTO> listTypeServiceDTO=new ArrayList<TypeServiceDTO>();
		for (TypeService entity : listTypeService) {
			TypeServiceDTO p = convertToDTO(entity);
			listTypeServiceDTO.add(p);
		}
		return listTypeServiceDTO;
	}

	/* (non-Javadoc)
	 * @see com.gaConnecte.assistAuto.service.TypeService_Service#listTypeService()
	 */
	@Override
	public List<TypeService> listTypeService() {
		return typeServiceRepository.findAll();
	}

}
