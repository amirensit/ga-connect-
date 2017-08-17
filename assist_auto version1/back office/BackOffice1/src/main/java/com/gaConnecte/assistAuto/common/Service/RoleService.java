package com.gaConnecte.assistAuto.common.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.gaConnecte.assistAuto.common.DTO.RoleDTO;
import com.gaConnecte.assistAuto.common.Entities.Role;

public interface RoleService extends FwkGenericService<Role, RoleDTO>{
	public List<Role> listRole() ;
	public List<RoleDTO> convertToDTO(List<Role> r);
	public RoleDTO addRole(RoleDTO rdto);
	public Boolean supprimerRole(Integer id);
	public RoleDTO updateRole(RoleDTO cDTO, Integer id);
	public RoleDTO getRoleById(Integer id);
	public Role convertToEntityForEdit(RoleDTO roleDTO);
	public Page<RoleDTO> convertPage();
	public boolean verifByLibelle(String libelle);
	public Role convertToEntityuser(RoleDTO dto);
	public List<Role> convertToEntity(List<RoleDTO> r);
}
