package com.gaConnecte.assistAuto.common.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaConnecte.assistAuto.common.DTO.ModuleDTO;
import com.gaConnecte.assistAuto.common.DTO.RoleDTO;
import com.gaConnecte.assistAuto.common.Entities.Module;
import com.gaConnecte.assistAuto.common.Entities.Role;
import com.gaConnecte.assistAuto.common.Service.ModuleService;
import com.gaConnecte.assistAuto.common.Service.UtilisateurService;


@RestController
@RequestMapping(value = "modules")
@CrossOrigin(origins = "http://localhost:3000")
public class ModuleController {

	
	@Autowired
	private ModuleService moduleService;
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( method = RequestMethod.GET)
	public List<ModuleDTO> listModule() {
		List<ModuleDTO> r=moduleService.listModules();
		
		return r;
		
	}

}
