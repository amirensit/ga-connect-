package com.gaConnecte.assistAuto.configuration;


import java.util.List;

import org.springframework.batch.item.ItemWriter;



import com.gaConnecte.assistAuto.entities.Contrat;

public class Writer  implements ItemWriter<Contrat> {
	
private final ContratDAO contratDao;
	
	public Writer(ContratDAO contratDao) {
		this.contratDao = contratDao;
	}

	@Override
	public void write(List<? extends Contrat> contrats) throws Exception {
		contratDao.insert(contrats);

	}
}