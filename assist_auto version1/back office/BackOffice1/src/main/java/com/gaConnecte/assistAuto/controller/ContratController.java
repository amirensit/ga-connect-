package com.gaConnecte.assistAuto.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;

import org.springframework.batch.core.launch.JobLauncher;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.gaConnecte.assistAuto.daos.ContratRepository;
import com.gaConnecte.assistAuto.dto.ContratDTO;
import com.gaConnecte.assistAuto.entities.Contrat;
import com.gaConnecte.assistAuto.service.ContratService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/contrats")
public class ContratController {
	
	
	
	@Autowired
	private ContratService contratService;
	
	
	@Autowired
	private ContratRepository contratRepository;
	
	

	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	
	@RequestMapping(value ="/import" , method =RequestMethod.POST)
	public void importCSV()
	{
		Logger logger = LoggerFactory.getLogger(this.getClass());
		try {
			JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(job, jobParameters);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public ContratDTO addContrat( @RequestBody ContratDTO c) {
		
		ContratDTO bat = contratService.addContrat(c);
		return bat;

	}


	
	@RequestMapping(value="edit/{id}",method=RequestMethod.PUT) 
	public ContratDTO update(@RequestBody   ContratDTO cDTO,@PathVariable Long id)
		{
			return contratService.editContrat(cDTO, id);
		}
	

	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public List<ContratDTO> listContratDTO() {
		//List<Contrat> listContrat=contratRepository.findAll();
		List<ContratDTO> listContratDTO =contratService.listContratDTO();
		
		return listContratDTO;
		
	}
	
	@RequestMapping(value="/{id_contrat}",method=RequestMethod.GET)
	public ContratDTO getContrat(@PathVariable Long id_contrat)
	{
		Contrat contrat=contratRepository.findOne(id_contrat);
		return contratService.convertToDTO(contrat);
	}

}
