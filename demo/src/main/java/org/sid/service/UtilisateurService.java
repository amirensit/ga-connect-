package org.sid.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.sid.dao.UtilisateurRepository;
import org.sid.dto.RoleDTO;
import org.sid.dto.UtilisateurDTO;
import org.sid.entities.Role;
import org.sid.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
public class UtilisateurService {
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public UtilisateurDTO convertToDTO(Utilisateur entity) {
		
		UtilisateurDTO objDto = modelMapper.map(entity, UtilisateurDTO.class);
		for (Role r: entity.getRoles()) 
		{
		RoleDTO rdto=convertToDTO(r);
		objDto.setRole(rdto);
		}
		
		return objDto;
		}
	
	public Utilisateur convertToEntity(UtilisateurDTO ud) {
		
		Utilisateur utilisateur = modelMapper.map(ud, Utilisateur.class);
		
		    return utilisateur;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	public List<UtilisateurDTO> convertToDTO(List<Utilisateur> u) {
		
		List<UtilisateurDTO> objDtoList = new ArrayList<UtilisateurDTO>();
		UtilisateurDTO objDto = null;
		for (Utilisateur entity : u) {
			for (Role r: entity.getRoles()) 
				{
				RoleDTO rdto=convertToDTO(r);
				objDto = convertToDTO(entity);
				objDto.setRole(rdto);
				objDtoList.add(objDto);
				}
			}
		return objDtoList;
	
		}
	
	
	

	public List<Utilisateur> convertToEntity(List<UtilisateurDTO> ud) {
		
		List<Utilisateur> objList = new ArrayList<Utilisateur>();
		Utilisateur obj = null;
		for (UtilisateurDTO u : ud) {
			RoleDTO rdto= u.getRole();
			Role r=convertToEntity(rdto);
			obj = convertToEntity(u);
			obj.getRoles().add(r);
			objList.add(obj);
			}
		return objList;
	
		}
	
	
	
	
	
	
	public RoleDTO convertToDTO(Role entity) {
		
		RoleDTO roleDTO = modelMapper.map(entity, RoleDTO.class);
		return roleDTO;
		}
	
	public Role convertToEntity(RoleDTO rd)
	{
		Role r=modelMapper.map(rd, Role.class);
		return r;
	}
	
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	

	public Utilisateur saveUser(Utilisateur us) {
		
		return utilisateurRepository.save(us);
	}
	
	
	
	public List<Utilisateur> listUser() {
		
		return (List<Utilisateur>) utilisateurRepository.findAll();
	}
	
	
	public UtilisateurDTO getUser(Integer id) {
		
		 Utilisateur u= utilisateurRepository.findOne(id);
		 return (UtilisateurDTO) convertToDTO(u);
	}
	
	
	
	public void deleteUser(Integer id_user) {
		
		
		utilisateurRepository.delete(id_user);

	}
	
	
	public Utilisateur load(Utilisateur us) {

		Utilisateur user = utilisateurRepository.findByLoginAndPass(us.getLogin(), us.getPassword());

		return user;
	}
	
	
	
	
	

}
