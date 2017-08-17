package com.gaConnecte.assistAuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaConnecte.assistAuto.daos.VilleRepository;
import com.gaConnecte.assistAuto.dto.VilleDTO;
import com.gaConnecte.assistAuto.entities.Remorqueur;
import com.gaConnecte.assistAuto.entities.Ville;
import com.gaConnecte.assistAuto.service.VilleService;

// TODO: Auto-generated Javadoc
/**
 * The Class VilleController.
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/villes")
public class VilleController {
	
	/** The ville service. */
	@Autowired
	private VilleService villeService;
	
	/** The ville repository. */
	@Autowired
	private VilleRepository villeRepository;
	
	
	/**
	 * Adds the ville.
	 *
	 * @param vDTO the v DTO
	 * @return the ville DTO
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public VilleDTO addVille(@RequestBody VilleDTO vDTO) {
		
		return villeService.addVille(vDTO);
		
	}
	
	
	@RequestMapping(value="/{id_ville}",method = RequestMethod.GET)
	public VilleDTO getVille(@PathVariable Long id_ville) {
		
		Ville ville=villeRepository.findOne(id_ville);
		return villeService.convertToDTO(ville);
		
	}
	/**
	 * Delete ville.
	 *
	 * @param id_ville the id ville
	 */
	@RequestMapping(value = "delete/{id_ville}", method = RequestMethod.DELETE)

	public void deleteVille(@PathVariable Long id_ville) {

		villeService.deleteVille(id_ville);

		}
	
	/**
	 * Gets the id ville by gouvernorat.
	 *
	 * @param id_gouvernorat the id gouvernorat
	 * @return the id ville by gouvernorat
	 */
	@RequestMapping(value = "getIdVilleByGouvernorat/{id_gouvernorat}", method = RequestMethod.GET)
	public List<VilleDTO> getIdVilleByGouvernorat(@PathVariable Long id_gouvernorat) {
		
		List<Ville> listVille = villeRepository.getIdVilleByGouvernorat(id_gouvernorat);
		List<VilleDTO> listVilleDTO = villeService.convertToDTO(listVille);

		return  listVilleDTO;

		}

}
