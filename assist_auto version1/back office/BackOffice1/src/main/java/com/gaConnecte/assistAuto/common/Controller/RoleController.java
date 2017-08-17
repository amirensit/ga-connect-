package com.gaConnecte.assistAuto.common.Controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaConnecte.assistAuto.common.DAO.RoleRepository;
import com.gaConnecte.assistAuto.common.DTO.RoleDTO;
import com.gaConnecte.assistAuto.common.Entities.Role;
import com.gaConnecte.assistAuto.common.Service.RoleService;



@RestController
@RequestMapping(value = "roles")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {

	
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleRepository roleRepository;
	
	

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping( method = RequestMethod.GET)
	public List<RoleDTO> listRole() {
		List<Role> r=roleService.listRole();
		List<RoleDTO> rd =roleService.convertToDTO(r);
		return rd;
		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="chercherRoles",method=RequestMethod.GET)
	public Page<RoleDTO> chercher(
			 String mc,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="8") int size)
	{
		Page<Role> entities =roleRepository.chercherRoles("%"+mc+"%", new PageRequest(page, size)) ;
		
		Page<RoleDTO> dtoPage = entities.map(new Converter<Role, RoleDTO>() {
		    
		    public RoleDTO convert(Role entity) {
		        RoleDTO dto = new RoleDTO();
		        dto=roleService.convertToDTO(entity);

		        return dto;
		    }
	});
		return dtoPage;
		}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public RoleDTO updateRole(@RequestBody RoleDTO ud,@PathVariable("id") Integer id) {
		//System.out.println(ud.getModules()+"\n" );
		//System.out.println(ud.getP().getNom_pack()+"\n" );
		return roleService.updateRole(ud,id);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public RoleDTO addClient(@RequestBody RoleDTO ud) {
		System.out.println(ud.getModules()+"\n" );
		//System.out.println(ud.getP().getNom_pack()+"\n" );
		return roleService.addRole(ud);
		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)

	public @ResponseBody RoleDTO get(@PathVariable("id") Integer id) {
		return roleService.getRoleById(id);
	}
	

	
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	
	public Boolean supprimerRole(@PathVariable Integer id) {

		return roleService.supprimerRole(id);

	}
@RequestMapping(method = RequestMethod.GET, value = "/role")
@CrossOrigin(origins = "http://localhost:3000")
public boolean verifRole(@RequestParam String libelle) {

	return this.roleService.verifByLibelle(libelle);
}
}
