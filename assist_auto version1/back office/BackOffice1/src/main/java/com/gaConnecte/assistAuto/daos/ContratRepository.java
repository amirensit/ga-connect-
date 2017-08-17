package com.gaConnecte.assistAuto.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gaConnecte.assistAuto.entities.Contrat;

public interface ContratRepository extends JpaRepository<Contrat,Long> {

}
