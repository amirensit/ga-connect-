package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.Contrat;

import com.gaconnecte.repository.ContratRepository;
import com.gaconnecte.repository.search.ContratSearchRepository;
import com.gaconnecte.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Contrat.
 */
@RestController
@RequestMapping("/api")
public class ContratResource {

    private final Logger log = LoggerFactory.getLogger(ContratResource.class);

    private static final String ENTITY_NAME = "contrat";

    private final ContratRepository contratRepository;

    private final ContratSearchRepository contratSearchRepository;

    public ContratResource(ContratRepository contratRepository, ContratSearchRepository contratSearchRepository) {
        this.contratRepository = contratRepository;
        this.contratSearchRepository = contratSearchRepository;
    }

    /**
     * POST  /contrats : Create a new contrat.
     *
     * @param contrat the contrat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contrat, or with status 400 (Bad Request) if the contrat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/contrats")
    @Timed
    public ResponseEntity<Contrat> createContrat(@Valid @RequestBody Contrat contrat) throws URISyntaxException {
        log.debug("REST request to save Contrat : {}", contrat);
        if (contrat.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new contrat cannot already have an ID")).body(null);
        }
        Contrat result = contratRepository.save(contrat);
        contratSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/contrats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contrats : Updates an existing contrat.
     *
     * @param contrat the contrat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contrat,
     * or with status 400 (Bad Request) if the contrat is not valid,
     * or with status 500 (Internal Server Error) if the contrat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/contrats")
    @Timed
    public ResponseEntity<Contrat> updateContrat(@Valid @RequestBody Contrat contrat) throws URISyntaxException {
        log.debug("REST request to update Contrat : {}", contrat);
        if (contrat.getId() == null) {
            return createContrat(contrat);
        }
        Contrat result = contratRepository.save(contrat);
        contratSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, contrat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contrats : get all the contrats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contrats in body
     */
    @GetMapping("/contrats")
    @Timed
    public List<Contrat> getAllContrats() {
        log.debug("REST request to get all Contrats");
        return contratRepository.findAll();
    }

    /**
     * GET  /contrats/:id : get the "id" contrat.
     *
     * @param id the id of the contrat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contrat, or with status 404 (Not Found)
     */
    @GetMapping("/contrats/{id}")
    @Timed
    public ResponseEntity<Contrat> getContrat(@PathVariable Long id) {
        log.debug("REST request to get Contrat : {}", id);
        Contrat contrat = contratRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(contrat));
    }

    /**
     * DELETE  /contrats/:id : delete the "id" contrat.
     *
     * @param id the id of the contrat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/contrats/{id}")
    @Timed
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        log.debug("REST request to delete Contrat : {}", id);
        contratRepository.delete(id);
        contratSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/contrats?query=:query : search for the contrat corresponding
     * to the query.
     *
     * @param query the query of the contrat search
     * @return the result of the search
     */
    @GetMapping("/_search/contrats")
    @Timed
    public List<Contrat> searchContrats(@RequestParam String query) {
        log.debug("REST request to search Contrats for query {}", query);
        return StreamSupport
            .stream(contratSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
