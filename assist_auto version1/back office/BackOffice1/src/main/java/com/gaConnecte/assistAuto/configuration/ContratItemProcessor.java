package com.gaConnecte.assistAuto.configuration;

import java.util.Date;

import org.springframework.batch.item.ItemProcessor;

import com.gaConnecte.assistAuto.entities.Contrat;

public class ContratItemProcessor  implements ItemProcessor<Contrat, Contrat>{

	@Override
	public Contrat process(Contrat contrat) throws Exception {
		
		
		return contrat;
	}

}
