package com.gaConnecte.assistAuto;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gaConnecte.assistAuto.daos.ClientRepository;

import com.gaConnecte.assistAuto.daos.PackRepository;
import com.gaConnecte.assistAuto.entities.Client;

import com.gaConnecte.assistAuto.entities.Pack;
import com.gaConnecte.assistAuto.entities.Ville;

@SpringBootApplication
public class StageApplication  implements CommandLineRunner{
	
	

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PackRepository packRepository;
	
	public static void main(String[] args)  {
		SpringApplication.run(StageApplication.class, args);
	}
	
	
	
	
	
	
	
	@Override
	public void run(String... arg0) throws Exception {
			
		
		
	}
	
	
}
