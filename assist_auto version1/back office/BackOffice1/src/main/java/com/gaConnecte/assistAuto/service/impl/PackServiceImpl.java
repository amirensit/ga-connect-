package com.gaConnecte.assistAuto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaConnecte.assistAuto.daos.PackRepository;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.entities.Pack;
import com.gaConnecte.assistAuto.service.PackService;


@Transactional
@Service
public class PackServiceImpl implements PackService {
	
	
	
	@Autowired
	private PackRepository packRepository;
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	

	
	@Override
	public PackDTO convertToDTO(Pack entity) {
		PackDTO roleDTO = modelMapper.map(entity, PackDTO.class);
		return roleDTO;
	}

	

	
	@Override
	public List<PackDTO> convertToDTO(List<Pack> listPack) {
		List<PackDTO> listPackDTO=new ArrayList<PackDTO>();
		for (Pack entity : listPack) {
			PackDTO p = convertToDTO(entity);
			listPackDTO.add(p);
		}
		return listPackDTO;
	}

	
	@Override
	public List<Pack> convertToEntity(List<PackDTO> listPackDTO) {
		
		List<Pack> listPack=new ArrayList<Pack>();
		for (PackDTO entity : listPackDTO) {
			Pack p = convertToEntity(entity);
			listPack.add(p);
		}
		return listPack;
	}
	
	
	
	
	@Override
	public Pack convertToEntity(PackDTO pd) {
		
		Pack r=modelMapper.map(pd, Pack.class);
		return r;
	}



	
	@Override
	public PackDTO addPack(PackDTO pDTO) {
		Pack p = convertToEntity(pDTO);
		packRepository.save(p);
		return pDTO;
	}



	
	@Override
	public List<PackDTO> convertListToDTO(List<Pack> m) {
		
			List<PackDTO> objDtoList = new ArrayList<PackDTO>();
			PackDTO objDto = null;
			for (Pack entity : m) {
				
					
				PackDTO mdto= this.convertToDTO(entity);
				
				objDtoList.add(mdto);
			}
			return objDtoList;
		
		
	}


	@Override
	public PackDTO editPack(PackDTO pDTO, Long id) {
		pDTO.setId_pack(id);
		Pack p =new Pack();
		p=convertToEntity(pDTO);
		 packRepository.saveAndFlush(p);
		 return pDTO;
	}



	
	@Override
	public void deletePack(Long id) {
		
		packRepository.delete(id);
		
	}



	
	@Override
	public void enableEtatPack(Long id) {
		packRepository.enableEtatPack(id);
		
	}



	
	@Override
	public void disableEtatPack(Long id) {
		packRepository.disableEtatPack(id);
		
	}



	
	@Override
	public List<Pack> listPack() {
		return packRepository.findAll();
	}




	
	
	
	

}
