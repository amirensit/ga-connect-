package com.gaConnecte.assistAuto.common.Service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaConnecte.assistAuto.common.DAO.UtilisateurRepository;
import com.gaConnecte.assistAuto.common.DTO.ModuleDTO;
import com.gaConnecte.assistAuto.common.DTO.RoleDTO;
import com.gaConnecte.assistAuto.common.DTO.UtilisateurDTO;
import com.gaConnecte.assistAuto.common.Entities.Module;
import com.gaConnecte.assistAuto.common.Entities.Role;
import com.gaConnecte.assistAuto.common.Entities.Utilisateur;
import com.gaConnecte.assistAuto.common.Service.RoleService;
import com.gaConnecte.assistAuto.common.Service.UtilisateurService;



@Service("UtilisateurService")
public class UtilisateurServiceImpl implements UtilisateurService{
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public Utilisateur convertToEntity(UtilisateurDTO utilisateurDTO) {
			
			
		Utilisateur utilisateur =new Utilisateur(utilisateurDTO.getNom(),utilisateurDTO.getPrenom(),utilisateurDTO.getEmail(),utilisateurDTO.getMotDePasse()
				,utilisateurDTO.getNumFixe(),utilisateurDTO.getNumMobile(),utilisateurDTO.getStatus(),utilisateurDTO.getBlocage(),utilisateurDTO.getConnect());
		
			List<Role> l =new ArrayList<Role>();
			for ( RoleDTO r: utilisateurDTO.getRoles() ) 
			{
			Role rdto=roleService.convertToEntity(r);
			
			l.add(rdto);
			}
			utilisateur.setRoles(l);
			
			
			
		    return utilisateur;
		}

	
	@Override
	public UtilisateurDTO addUtilisateur(UtilisateurDTO cDTO) {
		
		
		
		Utilisateur p = convertToEntity(cDTO);
		utilisateurRepository.save(p);
		UtilisateurDTO u=this.convertToDTO(p);
		
		return u;
		 
	}
	
	@Override
	public void bloquerUtilisateur(Integer id) {
		Utilisateur u = this.utilisateurRepository.findOne(id);
		u.setBlocage(false);
		this.utilisateurRepository.save(u);

	}

	@Override
	public void débloquerUtilisateur(Integer id) {
		Utilisateur u = this.utilisateurRepository.findOne(id);
		u.setBlocage(true);
		this.utilisateurRepository.save(u);

	}
	
	@Override
	public Utilisateur convertToEntityForEdit(UtilisateurDTO utilisateurDTO) {
		
		Utilisateur utilisateur =new Utilisateur(utilisateurDTO.getId(),utilisateurDTO.getNom(),utilisateurDTO.getPrenom(),utilisateurDTO.getEmail(),utilisateurDTO.getMotDePasse()
				,utilisateurDTO.getNumFixe(),utilisateurDTO.getNumMobile(),utilisateurDTO.getStatus(),utilisateurDTO.getBlocage(),utilisateurDTO.getConnect());
		List<Role> l =new ArrayList<Role>();
		for ( RoleDTO m: utilisateurDTO.getRoles() ) 
		{
		Role mdto=roleService.convertToEntityForEdit(m);
		
		l.add(mdto);
		}
		utilisateur.setRoles(l);
		
		
		
	    return utilisateur;
	}

	@Override
	public UtilisateurDTO updateUtilisateur(UtilisateurDTO rDTO, Integer id) {
		rDTO.setId(id);
		Utilisateur r = new Utilisateur();
		r=convertToEntityForEdit(rDTO);
		utilisateurRepository.saveAndFlush(r);
		return rDTO;
		
	}


	@Override
	public UtilisateurDTO getUtilisateurById(Integer id) {
		Utilisateur entity = utilisateurRepository.findOne(id);
		return convertToDTO(entity);
	}

	/**
	 * 
	 */
		
		
		@Override
		public List<Utilisateur> listUtilisateur() {
			// TODO Auto-generated method stub
			return (List<Utilisateur>) utilisateurRepository.findAll();
		}

		/**
		 * 
		 */
		

		

		@Override
		public Boolean supprimerUtilisateur(Integer id) {
			Utilisateur utilisateur =utilisateurRepository.findOne(id);
			
			
			utilisateurRepository.delete(id);
		return true;
			}
		@Override
		public UtilisateurDTO convertToDTO(Utilisateur entity) {
//			if(entity.getRoles().size()==0) {
//				
//				
//				return null;
//			}
			List<Role> m= entity.getRoles();
			

			UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
			BeanUtils.copyProperties(entity, utilisateurDTO);
			
			List<RoleDTO> modDto= roleService.convertToDTO(m);
				utilisateurDTO.setRoles(modDto);
			
			    return utilisateurDTO;
			}
		
		@Override
		public boolean verifByMail(String email) {
			Utilisateur r = this.utilisateurRepository.findByEmail(email);
			//RoleDTO rdto = this.convertToDTO(r);
			if (r != null) {
				return true;
			} else {
				return false;
			}

		}
		@Override
		public boolean verifByMotDePasse(String motDePasse, String email) {
			Utilisateur r = this.utilisateurRepository.findByEmail(email);
			String m=r.getMotDePasse();
			//RoleDTO rdto = this.convertToDTO(r);
			if (m == motDePasse) {
				return true;
			} else {
				return false;
			}

		}

		
		/**
		 * 
		 */
//		@Override
//		public boolean verifByLibelle(String libelle) {
//			Role r = this.roleRepository.findByLibelle(libelle);
//			RoleDTO rdto = this.convertToDTO(r);
//			if (r != null) {
//				return true;
//			} else {
//				return false;
//			}
//
//		}

		@Override
		public List<UtilisateurDTO> convertToDTO(List<Utilisateur> u) {
			
				List<UtilisateurDTO> objDtoList = new ArrayList<UtilisateurDTO>();
				UtilisateurDTO objDto = null;
				for (Utilisateur entity : u) {
					
					if(entity.getRoles().size()!=0) {
					List<Role> mod=entity.getRoles();
					List<RoleDTO> modDto= roleService.convertToDTO(mod);
					
					objDto = convertToDTO(entity);
					objDto.setRoles(modDto);
					objDtoList.add(objDto);}
				}
				return objDtoList;
			
			
		}
		
		@Override
		public UtilisateurDTO load(Utilisateur us) {
			
			

			Utilisateur utilisateur = utilisateurRepository.findByLoginAndPass(us.getEmail(),us.getMotDePasse());
			utilisateur.setConnect(true);
			this.utilisateurRepository.save(utilisateur);
			UtilisateurDTO u=this.convertToDTO(utilisateur);

			
			return u;
		}
		
		@Override
		public boolean logout(Integer id) {
			
			

			Utilisateur us=utilisateurRepository.findOne(id);
			us.setConnect(false);
			this.utilisateurRepository.save(us);
			
			
			return true;
		}

	

}
