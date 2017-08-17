package com.gaConnecte.assistAuto.common.Controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaConnecte.assistAuto.common.Service.FwkGenericService;



/**
 * File : GenericRestController.java
 *
 * Module : logic
 *
 * Role : generic REST Controller used to execute basic CRUD actions
 *
 * Description :inspired from https://gist.github.com/wvuong/5673644
 * 
 * @date 06/04/2017
 * @author WEVIOO - AISSA
 * @version 1.0
 * @param <E>
 * @copyright (C) 2017 WEVIOO All rights reserved.
 * 
 */

public class GenericRestController<E extends Serializable, DTO, Service extends FwkGenericService<E, DTO>> {

	protected Service service;

	public GenericRestController(Service service) {
		this.service = service;
	}

	@RequestMapping

	public @ResponseBody List<DTO> listAll() {
		return service.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)

	public @ResponseBody DTO get(@PathVariable("id") Integer id) {
		return service.getById(id);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })

	public @ResponseBody DTO create(@RequestBody DTO json) {
		return service.save(json);
	}

	@RequestMapping(method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })

	public @ResponseBody DTO update(@RequestBody DTO json) {
		return service.update(json);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)

	public @ResponseBody boolean delete(@PathVariable Integer id) {
		return service.delete(id);
	}
}
