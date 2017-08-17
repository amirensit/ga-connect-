package com.gaConnecte.assistAuto.configuration;



import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import com.gaConnecte.assistAuto.entities.Contrat;
import com.gaConnecte.assistAuto.entities.Marque;
import com.gaConnecte.assistAuto.entities.Pack;
import com.gaConnecte.assistAuto.entities.Ville;

@Configuration
public class MyCoolConfiguration  extends RepositoryRestConfigurerAdapter  {
	
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		// TODO Auto-generated method stub
		super.configureRepositoryRestConfiguration(config);
		config.exposeIdsFor(Contrat.class);
		config.exposeIdsFor(Ville.class);
		config.exposeIdsFor(Marque.class);
		config.exposeIdsFor(Pack.class);
	}
	
}