package com.gaConnecte.assistAuto.controller;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gaConnecte.assistAuto.daos.ClientRepository;
import com.gaConnecte.assistAuto.dto.ClientDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.service.ClientService;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/clients")
public class ClientController {
	
	
	@Autowired
	private ClientService clientService;
	
	
	@Autowired
	private ClientRepository clientRepository;

	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public ClientDTO addClient(@RequestBody ClientDTO ud) {
		System.out.println(ud.getPacks()+"\n" );
		//System.out.println(ud.getP().getNom_pack()+"\n" );
		return clientService.addClient(ud);
		
	}
	
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<ClientDTO> listClient() {
		List<Client> r=clientService.listClient();
		List<ClientDTO> rd =clientService.convertToDTO(r);
		return rd;
		
	}
	
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.PUT) 
	public ClientDTO update(@RequestBody   ClientDTO cDTO,@PathVariable Long id)
	{
		return clientService.editClient(cDTO, id);
	}
	
	
	
	@RequestMapping(value = "delete/{id_user}", method = RequestMethod.DELETE)

	public void deleteClient(@PathVariable Long id_user) {

		clientService.deleteClient(id_user);

		}	
	
	
	@RequestMapping(value = "enable/{id_user}", method = RequestMethod.PUT)
	public void enableClient(@PathVariable Long id_user) {

		clientService.enableEtatClient(id_user);

		}
	
	
	@RequestMapping(value = "disable/{id_user}", method = RequestMethod.PUT)
	public void disableClient(@PathVariable Long id_user) {

		clientService.disableEtatClient(id_user);

		}	
	
	
	
	@RequestMapping(value="/{id_client}",method=RequestMethod.GET)
	public ClientDTO getClient(@PathVariable Long id_client)
	{
		Client client=clientRepository.findOne(id_client);
		return clientService.convertToDTO(client);
	}
	
	
	@RequestMapping(value="/verifierDejaExist/{num_client}",method=RequestMethod.GET)
	public String verifierDejaExist(@PathVariable Long num_client)
	{
		Client c=clientRepository.verifierDejaExist(num_client);
		
		 if ( c !=null) return "existe";
		 return "cv";
		
	}
	
	
	
}
