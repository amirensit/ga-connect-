package com.gaConnecte.assistAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaConnecte.assistAuto.daos.TypeServiceRepository;
import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.dto.TypeServiceDTO;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.TypeService;
import com.gaConnecte.assistAuto.service.TypeService_Service;

// TODO: Auto-generated Javadoc
/**
 * The Class TypeServiceController.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/typeService")
public class TypeServiceController {

	/** The type service service. */
	@Autowired
	private TypeService_Service typeService_Service;
	
	/** The type service repository. */
	@Autowired
	private TypeServiceRepository typeServiceRepository;
	
	
	/**
	 * Adds the type service.
	 *
	 * @param pDTO the dto
	 * @return the type service
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public TypeService addTypeService(@RequestBody TypeService pDTO) {
		
		return typeService_Service.addTypeService(pDTO);
	}
	
	/**
	 * Delete type service.
	 *
	 * @param id_user the id user
	 */
	@RequestMapping(value = "delete/{id_user}", method = RequestMethod.DELETE)

	public void deleteTypeService(@PathVariable Long id_user) {

		typeService_Service.deleteTypeService(id_user);

		}
	
	/**
	 * Update.
	 *
	 * @param pDTO the dto
	 * @param id the id
	 * @return the type service
	 */
	@RequestMapping(value="edit/{id}",method=RequestMethod.PUT) 
	public TypeService update(@RequestBody   TypeService pDTO,@PathVariable Long id)
	{
		return typeService_Service.editTypeService(pDTO, id);
	}
	
	/**
	 * Gets the list type service.
	 *
	 * @return the list type service
	 */
	@RequestMapping(value = "getListTypeService", method = RequestMethod.GET)
	public List<TypeServiceDTO> getListTypeService()
	{
		List<TypeService> listTypeService=typeService_Service.listTypeService();
		List<TypeServiceDTO> typeServiceDTOList =typeService_Service.convertToDTO(listTypeService);
		return typeServiceDTOList;
	}
	
	/**
	 * Gets the type service.
	 *
	 * @param id_typeService the id type service
	 * @return the type service
	 */
	@RequestMapping(value="/{id_typeService}",method=RequestMethod.GET)
	public TypeServiceDTO getTypeService(@PathVariable Long id_typeService)
	{
		TypeService typeService=typeServiceRepository.findOne(id_typeService);
		return typeService_Service.convertToDTO(typeService);
	}
	
	
/*	@RequestMapping(value="chercherTypeService",method=RequestMethod.GET)
	public Page<TypeServiceDTO> chercher(
			 String mc,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size)
	{
		
		Page<TypeService> entities = typeServiceRepository.chercherTypeService("%"+mc+"%", new PageRequest(page, size));
		
		Page<TypeServiceDTO> dtoPage = entities.map(new Converter<TypeService, TypeServiceDTO>() {
		    
		    public TypeServiceDTO convert(TypeService entity) {
		    	TypeServiceDTO dto = new TypeServiceDTO();
		        dto=typeService_Service.convertToDTO(entity);

		        return dto;
		    }
		});
		
		return dtoPage ;
	}*/
	
	
	
	
}
