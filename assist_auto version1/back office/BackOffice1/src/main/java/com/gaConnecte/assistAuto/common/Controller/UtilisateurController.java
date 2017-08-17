package com.gaConnecte.assistAuto.common.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gaConnecte.assistAuto.common.DAO.UtilisateurRepository;
import com.gaConnecte.assistAuto.common.DTO.RoleDTO;
import com.gaConnecte.assistAuto.common.DTO.UtilisateurDTO;
import com.gaConnecte.assistAuto.common.Entities.Role;
import com.gaConnecte.assistAuto.common.Entities.Utilisateur;
import com.gaConnecte.assistAuto.common.Service.UtilisateurService;



@RestController
@RequestMapping(value = "utilisateurs")
@CrossOrigin(origins = "http://localhost:3000")
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	
	
	@RequestMapping( method = RequestMethod.GET)
	public List<UtilisateurDTO> listUtilisateur() {
		List<Utilisateur> u=utilisateurService.listUtilisateur();
		List<UtilisateurDTO> ud =utilisateurService.convertToDTO(u);
		return ud;
		
	}
	
	
	@RequestMapping(value="chercherUtilisateurs",method=RequestMethod.GET)
	public Page<UtilisateurDTO> chercher(
			 String mc,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="8") int size)
	{
		Page<Utilisateur> entities =utilisateurRepository.chercherUtilisateurs("%"+mc+"%", new PageRequest(page, size)) ;
		
		Page<UtilisateurDTO> dtoPage = entities.map(new Converter<Utilisateur, UtilisateurDTO>() {
		    
		    public UtilisateurDTO convert(Utilisateur entity) {
		        UtilisateurDTO dto = new UtilisateurDTO();
		        dto=utilisateurService.convertToDTO(entity);

		        return dto;
		    }
	});
		return dtoPage;
		}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public UtilisateurDTO updateUtilisateur(@RequestBody UtilisateurDTO ud,@PathVariable("id") Integer id) {
		//System.out.println(ud.getModules()+"\n" );
		//System.out.println(ud.getP().getNom_pack()+"\n" );
		return utilisateurService.updateUtilisateur(ud,id);
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public UtilisateurDTO addUtilisateur(@RequestBody UtilisateurDTO ud) {
		System.out.println(ud.getRoles()+"\n" );
		//System.out.println(ud.getP().getNom_pack()+"\n" );
		return utilisateurService.addUtilisateur(ud);
		
	}
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)

	public @ResponseBody UtilisateurDTO get(@PathVariable("id") Integer id) {
		return utilisateurService.getUtilisateurById(id);
	}
	

	@RequestMapping(method = RequestMethod.PUT, value = "/debloquer/{id}")
	public void debloquer(@PathVariable Integer id) {
		this.utilisateurService.débloquerUtilisateur(id);
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/utilisateur")
	public boolean verifEmail(@RequestParam String email) {

		return this.utilisateurService.verifByMail(email);
	} 
	
	@RequestMapping(method = RequestMethod.GET, value = "/utilisateur/password")
	public boolean verifMotDePasse(@RequestParam String motDePasse,@RequestParam String email) {

		return this.utilisateurService.verifByMotDePasse(motDePasse,email);
	} 
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/bloquer/{id}")
	public void bloquer(@PathVariable Integer id) {
		this.utilisateurService.bloquerUtilisateur(id);
	}

	

@RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
	
	public Boolean supprimerRole(@PathVariable Integer id) {

		return utilisateurService.supprimerUtilisateur(id);

	}

 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UtilisateurDTO login(@RequestBody Utilisateur us) {

		return utilisateurService.load(us);
	}
	
	
	@RequestMapping(value = "/logout/{id}", method = RequestMethod.POST)
	public boolean logout(@PathVariable Integer id) {

		return utilisateurService.logout(id);
	}


}
