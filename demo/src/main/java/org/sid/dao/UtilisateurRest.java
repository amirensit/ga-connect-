package org.sid.dao;



import java.util.List;

import org.sid.dto.UtilisateurDTO;
import org.sid.entities.Utilisateur;
import org.sid.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/users")

public class UtilisateurRest {

	@Autowired
	private UtilisateurService userService;

	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public Utilisateur addUser(@RequestBody UtilisateurDTO ud) {
		//System.out.println(ud.getRole().getId());
		Utilisateur u=userService.convertToEntity(ud);
		return  userService.saveUser(u);
	}

	/*
	@RequestMapping(method = RequestMethod.PUT)
	public Boolean updateUser(@RequestBody Utilisateur us) {
		
		return true;
	}*/

	
	@RequestMapping(value = "delete/{id_user}", method = RequestMethod.DELETE)

	public void deleteUser(@PathVariable Integer id_user) {

		userService.deleteUser(id_user);


	}
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UtilisateurDTO> listUser() 
	{
		List<Utilisateur> u=userService.listUser();
		List<UtilisateurDTO> ud =userService.convertToDTO(u);
		return ud;
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UtilisateurDTO utiliId(@PathVariable Integer id) {
		return userService.getUser(id);
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UtilisateurDTO login(@RequestBody Utilisateur us) {

		Utilisateur u= userService.load(us);

		UtilisateurDTO ud= userService.convertToDTO(u);
		return ud;
	}
	
	

}