package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.DossierRmq;

import com.gaconnecte.repository.DossierRmqRepository;
import com.gaconnecte.repository.search.DossierRmqSearchRepository;
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
 * REST controller for managing DossierRmq.
 */
@RestController
@RequestMapping("/api")
public class DossierRmqResource {

    private final Logger log = LoggerFactory.getLogger(DossierRmqResource.class);

    private static final String ENTITY_NAME = "dossierRmq";

    private final DossierRmqRepository dossierRmqRepository;

    private final DossierRmqSearchRepository dossierRmqSearchRepository;

    public DossierRmqResource(DossierRmqRepository dossierRmqRepository, DossierRmqSearchRepository dossierRmqSearchRepository) {
        this.dossierRmqRepository = dossierRmqRepository;
        this.dossierRmqSearchRepository = dossierRmqSearchRepository;
    }

    /**
     * POST  /dossier-rmqs : Create a new dossierRmq.
     *
     * @param dossierRmq the dossierRmq to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dossierRmq, or with status 400 (Bad Request) if the dossierRmq has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dossier-rmqs")
    @Timed
    public ResponseEntity<DossierRmq> createDossierRmq(@Valid @RequestBody DossierRmq dossierRmq) throws URISyntaxException {
        log.debug("REST request to save DossierRmq : {}", dossierRmq);
        if (dossierRmq.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dossierRmq cannot already have an ID")).body(null);
        }
        DossierRmq result = dossierRmqRepository.save(dossierRmq);
        dossierRmqSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/dossier-rmqs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dossier-rmqs : Updates an existing dossierRmq.
     *
     * @param dossierRmq the dossierRmq to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dossierRmq,
     * or with status 400 (Bad Request) if the dossierRmq is not valid,
     * or with status 500 (Internal Server Error) if the dossierRmq couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dossier-rmqs")
    @Timed
    public ResponseEntity<DossierRmq> updateDossierRmq(@Valid @RequestBody DossierRmq dossierRmq) throws URISyntaxException {
        log.debug("REST request to update DossierRmq : {}", dossierRmq);
        if (dossierRmq.getId() == null) {
            return createDossierRmq(dossierRmq);
        }
        DossierRmq result = dossierRmqRepository.save(dossierRmq);
        dossierRmqSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dossierRmq.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dossier-rmqs : get all the dossierRmqs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of dossierRmqs in body
     */
    @GetMapping("/dossier-rmqs")
    @Timed
    public List<DossierRmq> getAllDossierRmqs() {
        log.debug("REST request to get all DossierRmqs");
        return dossierRmqRepository.findAll();
    }

    /**
     * GET  /dossier-rmqs/:id : get the "id" dossierRmq.
     *
     * @param id the id of the dossierRmq to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dossierRmq, or with status 404 (Not Found)
     */
    @GetMapping("/dossier-rmqs/{id}")
    @Timed
    public ResponseEntity<DossierRmq> getDossierRmq(@PathVariable Long id) {
        log.debug("REST request to get DossierRmq : {}", id);
        DossierRmq dossierRmq = dossierRmqRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dossierRmq));
    }

    /**
     * DELETE  /dossier-rmqs/:id : delete the "id" dossierRmq.
     *
     * @param id the id of the dossierRmq to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dossier-rmqs/{id}")
    @Timed
    public ResponseEntity<Void> deleteDossierRmq(@PathVariable Long id) {
        log.debug("REST request to delete DossierRmq : {}", id);
        dossierRmqRepository.delete(id);
        dossierRmqSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/dossier-rmqs?query=:query : search for the dossierRmq corresponding
     * to the query.
     *
     * @param query the query of the dossierRmq search
     * @return the result of the search
     */
    @GetMapping("/_search/dossier-rmqs")
    @Timed
    public List<DossierRmq> searchDossierRmqs(@RequestParam String query) {
        log.debug("REST request to search DossierRmqs for query {}", query);
        return StreamSupport
            .stream(dossierRmqSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
