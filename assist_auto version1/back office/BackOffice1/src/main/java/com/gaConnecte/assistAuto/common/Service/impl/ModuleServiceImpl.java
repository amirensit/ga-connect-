package com.gaConnecte.assistAuto.common.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.gaConnecte.assistAuto.common.DAO.ModuleRepository;
import com.gaConnecte.assistAuto.common.DTO.ModuleDTO;
import com.gaConnecte.assistAuto.common.DTO.RoleDTO;
import com.gaConnecte.assistAuto.common.Entities.Module;
import com.gaConnecte.assistAuto.common.Service.ModuleService;
import com.gaConnecte.assistAuto.common.Service.RoleService;

@Service("ModuleService")
public class ModuleServiceImpl  extends FwkGenericServiceImpl<Module, ModuleDTO, ModuleRepository>
implements ModuleService {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	private ModelMapper modelMapper = new ModelMapper();
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private RoleService roleService;
	
	public ModuleServiceImpl(ModuleRepository repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Module convertToEntity(ModuleDTO dto) {
		Module r=modelMapper.map(dto, Module.class);
		return r;
	}

	

	@Override
	public ModuleDTO convertToDTO(Module module) {
		ModuleDTO r=modelMapper.map(module, ModuleDTO.class);
		return r;

	}
	
	@Override
	public List<ModuleDTO> convertListToDTO(List<Module> m) {
		
			List<ModuleDTO> objDtoList = new ArrayList<ModuleDTO>();
			ModuleDTO objDto = null;
			for (Module entity : m) {
				
					
				ModuleDTO mdto= this.convertToDTO(entity);
				
				objDtoList.add(mdto);
			}
			return objDtoList;
		
		
	}
	
	@Override
	public List<Module> convertListToEntity(List<ModuleDTO> mdto) {
		
			List<Module> objDtoList = new ArrayList<Module>();
			//Module objDto = null;
			for (ModuleDTO entity : mdto) {
				
					
				Module m= this.convertToEntity(entity);
				
				objDtoList.add(m);
			}
			return objDtoList;
		
		
	}

	@Override
	public List<ModuleDTO> updateModule(List<ModuleDTO> mdto, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	
//	@Override
//	public List<ModuleDTO> updateModule (List<ModuleDTO> mdto, Integer id){
//		RoleDTO rdto= roleService.getRoleById(id);
//		rdto.setModules(mdto);
//			
//	}
	@Override
public List<ModuleDTO> listModules() {
	List<Module> modules = this.moduleRepository.findAll();
	List<ModuleDTO> result = convertToDTO(modules);
	return result;
}
}
