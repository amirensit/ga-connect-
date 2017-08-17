package com.gaConnecte.assistAuto.daos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.gaConnecte.assistAuto.entities.Contrat;

@RepositoryRestResource
public interface contratRepository1 extends PagingAndSortingRepository<Contrat, Long>  {

}
