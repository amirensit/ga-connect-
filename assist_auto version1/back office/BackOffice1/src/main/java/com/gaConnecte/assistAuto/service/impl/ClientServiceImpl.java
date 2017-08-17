package com.gaConnecte.assistAuto.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


import com.gaConnecte.assistAuto.daos.ClientRepository;
import com.gaConnecte.assistAuto.dto.ClientDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.entities.Pack;
import com.gaConnecte.assistAuto.service.ClientService;
import com.gaConnecte.assistAuto.service.PackService;


@Transactional
@Service
public class ClientServiceImpl implements ClientService {
	
	
	@Autowired
	private PackService packService;
	
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	
	
	
	
	
	@Override
	public Client convertToEntity(ClientDTO clientDTO) {
		
		
		Client client =new Client(clientDTO.getNum_client(),clientDTO.getNom_client(),clientDTO.getEtat());
		List<Pack> l =new ArrayList<Pack>();
		for ( PackDTO r: clientDTO.getPacks() ) 
		{
		Pack rdto=packService.convertToEntity(r);
		
		l.add(rdto);
		}
		client.setPacks(l);
		return client;
	}
	
	
	
	

	
	@Override
	public ClientDTO convertToDTO(Client client) {
		ClientDTO objDto = modelMapper.map(client, ClientDTO.class);
		List<PackDTO> l =new ArrayList<PackDTO>();
		for (Pack r: client.getPacks()) 
		{
		PackDTO rdto=packService.convertToDTO(r);
		
		l.add(rdto);
		
		}
		objDto.setPacks(l);
		
		return objDto;
	}
	
@Override
	public List<Client> convertToEntity(List<ClientDTO> listClientDTO) {
		
		List<Client> objDtoList = new ArrayList<Client>();
		List<Pack> p = new ArrayList<Pack>();
		Client objDto = null;
		for (ClientDTO entity : listClientDTO) {
			
			p=packService.convertToEntity(entity.getPacks());
			objDto = convertToEntity(entity);
			objDto.setPacks(p);
			objDtoList.add(objDto);
			p.clear();
			
			}
		return objDtoList;
		
		
	}
	
	@Override
	public ClientDTO addClient(ClientDTO cDTO) {
		Client p = convertToEntity(cDTO);
		clientRepository.save(p);
		return cDTO;
		
	}

	@Override
	public List<Client> listClient() {
		
		return (List<Client>) clientRepository.findAll();
		
	}
	
	
	@Override
	public List<ClientDTO> convertToDTO(List<Client> r) {
		
			List<ClientDTO> objDtoList = new ArrayList<ClientDTO>();
			ClientDTO objDto = null;
			for (Client entity : r) {
				List<Pack> mod=entity.getPacks();
				List<PackDTO> modDto= packService.convertListToDTO(mod);
				
				objDto = convertToDTO(entity);
				objDto.setPacks(modDto);
				objDtoList.add(objDto);
			}
			return objDtoList;
		
		
	}

	@Override
	public Client convertToEntityForEdit(ClientDTO clientDTO) {
		Client client =new Client(clientDTO.getId_client(),clientDTO.getNum_client(),clientDTO.getNom_client(),clientDTO.getEtat());
		List<Pack> l =new ArrayList<Pack>();
		for ( PackDTO r: clientDTO.getPacks() ) 
		{
		Pack rdto=packService.convertToEntity(r);
		
		l.add(rdto);
		}
		client.setPacks(l);
		
		
		
	    return client;
	}

	@Override
	public ClientDTO editClient(ClientDTO cDTO, Long id) {
		cDTO.setId_client(id);
		Client c = new Client();
		c=convertToEntityForEdit(cDTO);
		clientRepository.saveAndFlush(c);
		return cDTO;
		
	}

	@Override
	public void deleteClient(Long id) {
		
		clientRepository.delete(id);
	}
	
	@Override
	public void enableEtatClient(Long id) {
		clientRepository.enableEtatClient(id);
		
		
	}

	@Override
	public void disableEtatClient(Long id) {
		
		clientRepository.disableEtatClient(id);
	}





}
