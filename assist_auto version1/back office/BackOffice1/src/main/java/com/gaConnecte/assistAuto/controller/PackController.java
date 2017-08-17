package com.gaConnecte.assistAuto.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaConnecte.assistAuto.daos.PackRepository;
import com.gaConnecte.assistAuto.dto.ClientDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.entities.Pack;
import com.gaConnecte.assistAuto.service.PackService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/packs")
public class PackController {

	
	@Autowired
	private PackService packService;
	
	
	@Autowired
	private PackRepository packRepository;
	
	
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public PackDTO addClient(@RequestBody PackDTO pDTO) {
		
		
		return packService.addPack(pDTO);
	}
	
	
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.PUT) 
	public PackDTO update(@RequestBody   PackDTO pDTO,@PathVariable Long id)
	{
		return packService.editPack(pDTO, id);
	}
	
	
	
	
	@RequestMapping(value = "delete/{id_pack}", method = RequestMethod.DELETE)

	public void deletePack(@PathVariable Long id_pack) {

		packService.deletePack(id_pack);

		}	
	
	
	
	
	@RequestMapping(value = "enable/{id_user}", method = RequestMethod.PUT)
	public void enablePack(@PathVariable Long id_user) {

		packService.enableEtatPack(id_user);

		}
	
	
	
	@RequestMapping(value = "disable/{id_user}", method = RequestMethod.PUT)
	public void disableClient(@PathVariable Long id_user) {

		packService.disableEtatPack(id_user);

		}
	
	
	
	@RequestMapping(value = "getListPack", method = RequestMethod.GET)
	public List<PackDTO> getListPack()
	{
		List<Pack> listPack=packService.listPack();
		List<PackDTO> packDTOList =packService.convertToDTO(listPack);
		
		return packDTOList;
	}
	
	
	@RequestMapping(value = "/{id_pack}", method = RequestMethod.GET)
	public PackDTO getPack(@PathVariable Long id_pack)
	{
		//Pack pack=packService.listPack();
		Pack pack=packRepository.findOne(id_pack);
		PackDTO packDTO =packService.convertToDTO(pack);
		
		return packDTO;
	}
	
	
}
