package com.gaConnecte.assistAuto.configuration;

import java.util.List;

import com.gaConnecte.assistAuto.entities.Contrat;



public interface ContratDAO {
	
	public void insert(List<? extends Contrat> contrats);

}
