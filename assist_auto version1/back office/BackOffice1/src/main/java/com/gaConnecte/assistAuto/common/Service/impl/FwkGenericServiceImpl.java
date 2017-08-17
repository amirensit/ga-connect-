package com.gaConnecte.assistAuto.common.Service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gaConnecte.assistAuto.common.Service.FwkGenericService;



/**
 * File : FwkGenericDTOServiceImpl.java
 *
 * Module : logic
 *
 * Role :
 *
 * Description : (DTO : the DTO, E : the Entity, DAO : the entity DAO)
 * 
 * @date 06/04/2017
 * @author WEVIOO - AISSA
 * @version 1.0
 * @copyright (C) 2016 WEVIOO All rights reserved.
 * 
 */
public abstract class FwkGenericServiceImpl<E extends Serializable, DTO, DAO extends CrudRepository<E, Integer>>
		implements FwkGenericService<E, DTO> {

	protected DAO repository;

	public FwkGenericServiceImpl(DAO repository) {
		this.repository = repository;
	}

	@Override
	public DTO save(DTO dto) {
		E entity = convertToEntity(dto);
		E saved = repository.save(entity);
		return convertToDTO(saved);
	}

	@Override
	public DTO update(DTO dto) {
		E entity = convertToEntity(dto);
		E updated = repository.save(entity);
		return convertToDTO(updated);
	}

	@Override
	public boolean delete(Integer id) {
		repository.delete(id);
		return true;
	}

	@Override
	public List<DTO> getAll() {
		List<E> entityList = (List<E>) repository.findAll();
		return convertToDTO(entityList);
	}

	@Override
	public DTO getById(Integer id) {
		E entity = repository.findOne(id);
		return convertToDTO(entity);
	}

	@Override
	public List<DTO> convertToDTO(List<E> entities) {
		List<DTO> objDtoList = new ArrayList<DTO>();
		DTO objDto = null;
		for (E entity : entities) {
			objDto = convertToDTO(entity);
			objDtoList.add(objDto);
		}
		return objDtoList;
	}
}
