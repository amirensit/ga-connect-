package com.gaConnecte.assistAuto.common.Service;

import java.util.List;

import com.gaConnecte.assistAuto.common.DTO.ModuleDTO;
import com.gaConnecte.assistAuto.common.Entities.Module;

public interface ModuleService extends FwkGenericService<Module, ModuleDTO>{
	
	//public List<ModuleDTO> listModuleByRole(Integer roleId) ;
	public List<ModuleDTO> convertListToDTO(List<Module> m);
	public List<Module> convertListToEntity(List<ModuleDTO> mdto);
	List<ModuleDTO> updateModule (List<ModuleDTO> mdto, Integer id);
	public List<ModuleDTO> listModules() ;
	
}
