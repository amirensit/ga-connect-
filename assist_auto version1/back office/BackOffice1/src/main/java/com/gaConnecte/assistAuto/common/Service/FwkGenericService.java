package com.gaConnecte.assistAuto.common.Service;

import java.io.Serializable;
import java.util.List;

/**
 * File : FwkGenericService.java
 *
 * Module : logic
 *
 * Role :
 *
 * Description : ( E : the Entity, DTO : the DTO)
 * 
 * @date 06/04/2017
 * @author WEVIOO - AISSA
 * @version 1.0
 * @copyright (C) 2017 WEVIOO All rights reserved.
 * 
 */
public interface FwkGenericService<E extends Serializable, DTO> {

	/**
	 * save all properties of entity from the DTO
	 * 
	 * @date 05/04/2016
	 * @param dto
	 * @return
	 */
	DTO save(DTO dto);

	/**
	 * update all properties of entity from the DTO
	 * 
	 * @date 02/08/2016
	 * @param dto
	 * @return
	 */
	DTO update(DTO dto);

	/**
	 * delete
	 * 
	 * @date 05/04/2016
	 * @param id
	 * @return
	 */
	boolean delete(Integer id);

	/**
	 * getAll
	 * 
	 * @date 05/04/2016
	 * @return
	 * @throws ObjectCopyPropertiesException
	 */
	List<DTO> getAll();

	/**
	 * getById
	 * 
	 * @date 05/04/2016
	 * @param id
	 * @return all properties of entity in a DTO
	 * @throws UnknownEntityIdException
	 */
	DTO getById(Integer id);

	/**
	 * convertToEntity
	 * 
	 * @date 05/04/2016
	 * @param dto
	 * @return
	 */
	E convertToEntity(DTO dto);

	/**
	 * convertToDTO
	 * 
	 * @date 05/04/2016
	 * @param entity
	 * @return
	 */
	DTO convertToDTO(E entity);

	/**
	 * convertToDTO : Convert a list of Entities to a list of DTO
	 * 
	 * @date 05/04/2016
	 * @param entity
	 * @return
	 * @throws ObjectCopyPropertiesException
	 */
	List<DTO> convertToDTO(List<E> entities);
}
