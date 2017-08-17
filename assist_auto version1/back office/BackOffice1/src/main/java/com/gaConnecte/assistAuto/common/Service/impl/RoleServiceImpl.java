package com.gaConnecte.assistAuto.common.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaConnecte.assistAuto.common.DAO.ModuleRepository;
import com.gaConnecte.assistAuto.common.DAO.RoleRepository;
import com.gaConnecte.assistAuto.common.DTO.ModuleDTO;
import com.gaConnecte.assistAuto.common.DTO.RoleDTO;
import com.gaConnecte.assistAuto.common.DTO.UtilisateurDTO;
import com.gaConnecte.assistAuto.common.Entities.Module;
import com.gaConnecte.assistAuto.common.Entities.Role;
import com.gaConnecte.assistAuto.common.Entities.Utilisateur;
import com.gaConnecte.assistAuto.common.Service.ModuleService;
import com.gaConnecte.assistAuto.common.Service.RoleService;
import com.gaConnecte.assistAuto.common.Service.UtilisateurService;




@Transactional
@Service("RoleService")
public class RoleServiceImpl implements RoleService{
	
	/**
	 * 
	 */
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModuleRepository moduleRepository;

/**
 * 
 * @param repository
 */
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	private ModelMapper modelMapper = new ModelMapper();
	

	/**
	 * 
	 */
	
	@Override
public Role convertToEntity(RoleDTO roleDTO) {
		
		
		Role role =new Role(roleDTO.getId(),roleDTO.getLibelle());
		List<Module> l =new ArrayList<Module>();
		for ( ModuleDTO r: roleDTO.getModules() ) 
		{
		Module rdto=moduleService.convertToEntity(r);
		
		l.add(rdto);
		}
		role.setModules(l);
		
		
		
	    return role;
	}

//	@Override
//	public Role convertToEntityuser(RoleDTO dto) {
//		Role role =new Role(dto.getLibelle());
//		return role;
//	}
@Override
public RoleDTO addRole(RoleDTO cDTO) {
	
	//if((cDTO.getModules().size()!=0)) {
	Role p = convertToEntity(cDTO);
	roleRepository.save(p);
	RoleDTO rdto=convertToDTO(p);
	
	return rdto;
	 
}
@Override
public Role convertToEntityForEdit(RoleDTO roleDTO) {
	Role role =new Role(roleDTO.getId(),roleDTO.getLibelle());
	List<Module> l =new ArrayList<Module>();
	for ( ModuleDTO m: roleDTO.getModules() ) 
	{
	Module mdto=moduleService.convertToEntity(m);
	
	l.add(mdto);
	}
	role.setModules(l);
	
	
	
    return role;
}

@Override
public RoleDTO updateRole(RoleDTO rDTO, Integer id) {
	rDTO.setId(id);
	Role r = new Role();
	r=convertToEntityForEdit(rDTO);
	roleRepository.saveAndFlush(r);
	return rDTO;
	
}


@Override
public RoleDTO getRoleById(Integer id) {
	Role entity = roleRepository.findOne(id);
	return convertToDTO(entity);
}

/**
 * 
 */
	
	
	@Override
	public List<Role> listRole() {
		// TODO Auto-generated method stub
		return (List<Role>) roleRepository.findAll();
	}

	/**
	 * 
	 */
	

	

	@Override
	public Boolean supprimerRole(Integer id) {
		Boolean verif;
		Role role =roleRepository.findOne(id);
		List<Utilisateur> m=role.getUtilisateurs();
		System.out.println(m);
		List<UtilisateurDTO> udto=utilisateurService.convertToDTO(m);
		if (m.size() != 0) {verif=false;}
		else {roleRepository.delete(id);verif=true;}
		
		return verif;
		}
	@Override
	public RoleDTO convertToDTO(Role entity) {
//		if(entity.getModules().size()==0) {
//			
//			
//			return null;
//		}
		List<Module> m= entity.getModules();
		

		RoleDTO roleDTO = new RoleDTO();
		BeanUtils.copyProperties(entity, roleDTO);
		
		List<ModuleDTO> modDto= moduleService.convertListToDTO(m);
			roleDTO.setModules(modDto);
		
		    return roleDTO;
		}
	
	/**
	 * 
	 */
	@Override
	public boolean verifByLibelle(String libelle) {
		Role r = this.roleRepository.findByLibelle(libelle);
		//RoleDTO rdto = this.convertToDTO(r);
		if (r != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public List<RoleDTO> convertToDTO(List<Role> r) {
		
			List<RoleDTO> objDtoList = new ArrayList<RoleDTO>();
			RoleDTO objDto = null;
			for (Role entity : r) {
				List<Module> mod=entity.getModules();
				List<ModuleDTO> modDto= moduleService.convertListToDTO(mod);
				
				objDto = convertToDTO(entity);
				objDto.setModules(modDto);
				objDtoList.add(objDto);
			}
			return objDtoList;
		
		
	}
	
	@Override
	public List<Role> convertToEntity(List<RoleDTO> r) {
		
			List<Role> objDtoList = new ArrayList<Role>();
			Role objDto = null;
			for (RoleDTO entity : r) {
				
				
				objDto = convertToEntityuser(entity);
				
				objDtoList.add(objDto);
			}
			return objDtoList;
		
		
	}

	@Override
	public Page<RoleDTO> convertPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO save(RoleDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO update(RoleDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RoleDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RoleDTO getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role convertToEntityuser(RoleDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
