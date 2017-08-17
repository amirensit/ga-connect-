package com.gaConnecte.assistAuto.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gaConnecte.assistAuto.daos.MarqueRepository;
import com.gaConnecte.assistAuto.dto.ClientDTO;
import com.gaConnecte.assistAuto.dto.MarqueDTO;
import com.gaConnecte.assistAuto.dto.PackDTO;
import com.gaConnecte.assistAuto.entities.Client;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.Pack;
import com.gaConnecte.assistAuto.service.MarqueService;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/marques")
public class MarqueController {
	
	
	
	@Autowired
	private MarqueService marqueService;
	
	
	@Autowired
	private MarqueRepository marqueRepository;
	
	
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public MarqueDTO addMarque(@RequestBody MarqueDTO marqueDTO)
	{
		return marqueService.addMarque(marqueDTO);
	}
	
	
	
	@RequestMapping(value="edit/{id}",method=RequestMethod.PUT) 
	public MarqueDTO update(@RequestBody   MarqueDTO pDTO,@PathVariable Long id)
	{
		return marqueService.editMarque(pDTO, id);
	}
	
	
	@RequestMapping(value = "getListMarque", method = RequestMethod.GET)
	public List<MarqueDTO> getListMarque()
	{
		List<Marque> listMarque=marqueService.listMarque();
		List<MarqueDTO> marqueDTOList =marqueService.convertToDTO(listMarque);
		return marqueDTOList;
	}
	
	
	
	@RequestMapping(value = "delete/{id_marque}", method = RequestMethod.DELETE)

	public void deleteMarque(@PathVariable Long id_marque) {

		marqueService.deleteMarque(id_marque);

		}
	
	
	
	@RequestMapping(value="/{id_marque}",method=RequestMethod.GET)
	public MarqueDTO getMarque(@PathVariable Long id_marque)
	{
		Marque marque=marqueRepository.findOne(id_marque);
		return marqueService.convertToDTO(marque);
	}
	
	
	@RequestMapping(value="chercherMarques",method=RequestMethod.GET)
	public Page<MarqueDTO> chercher(
			 String mc,
			@RequestParam(name="page",defaultValue="0") int page,
			@RequestParam(name="size",defaultValue="5") int size)
	{
		
		Page<Marque> entities = marqueRepository.chercherMarques("%"+mc+"%", new PageRequest(page, size));
		
		Page<MarqueDTO> dtoPage = entities.map(new Converter<Marque, MarqueDTO>() {
		    
		    public MarqueDTO convert(Marque entity) {
		        MarqueDTO dto = new MarqueDTO();
		        dto=marqueService.convertToDTO(entity);

		        return dto;
		    }
		});
		
		return dtoPage ;
	}
	
	
	
	
	
	

}
