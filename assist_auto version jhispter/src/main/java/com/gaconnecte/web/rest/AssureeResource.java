package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.Assuree;

import com.gaconnecte.repository.AssureeRepository;
import com.gaconnecte.repository.search.AssureeSearchRepository;
import com.gaconnecte.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Assuree.
 */
@RestController
@RequestMapping("/api")
public class AssureeResource {

    private final Logger log = LoggerFactory.getLogger(AssureeResource.class);

    private static final String ENTITY_NAME = "assuree";

    private final AssureeRepository assureeRepository;

    private final AssureeSearchRepository assureeSearchRepository;

    public AssureeResource(AssureeRepository assureeRepository, AssureeSearchRepository assureeSearchRepository) {
        this.assureeRepository = assureeRepository;
        this.assureeSearchRepository = assureeSearchRepository;
    }

    /**
     * POST  /assurees : Create a new assuree.
     *
     * @param assuree the assuree to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assuree, or with status 400 (Bad Request) if the assuree has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/assurees")
    @Timed
    public ResponseEntity<Assuree> createAssuree(@RequestBody Assuree assuree) throws URISyntaxException {
        log.debug("REST request to save Assuree : {}", assuree);
        if (assuree.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new assuree cannot already have an ID")).body(null);
        }
        Assuree result = assureeRepository.save(assuree);
        assureeSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/assurees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /assurees : Updates an existing assuree.
     *
     * @param assuree the assuree to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated assuree,
     * or with status 400 (Bad Request) if the assuree is not valid,
     * or with status 500 (Internal Server Error) if the assuree couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/assurees")
    @Timed
    public ResponseEntity<Assuree> updateAssuree(@RequestBody Assuree assuree) throws URISyntaxException {
        log.debug("REST request to update Assuree : {}", assuree);
        if (assuree.getId() == null) {
            return createAssuree(assuree);
        }
        Assuree result = assureeRepository.save(assuree);
        assureeSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, assuree.getId().toString()))
            .body(result);
    }

    /**
     * GET  /assurees : get all the assurees.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of assurees in body
     */
    @GetMapping("/assurees")
    @Timed
    public List<Assuree> getAllAssurees() {
        log.debug("REST request to get all Assurees");
        return assureeRepository.findAll();
    }

    /**
     * GET  /assurees/:id : get the "id" assuree.
     *
     * @param id the id of the assuree to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the assuree, or with status 404 (Not Found)
     */
    @GetMapping("/assurees/{id}")
    @Timed
    public ResponseEntity<Assuree> getAssuree(@PathVariable Long id) {
        log.debug("REST request to get Assuree : {}", id);
        Assuree assuree = assureeRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(assuree));
    }

    /**
     * DELETE  /assurees/:id : delete the "id" assuree.
     *
     * @param id the id of the assuree to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/assurees/{id}")
    @Timed
    public ResponseEntity<Void> deleteAssuree(@PathVariable Long id) {
        log.debug("REST request to delete Assuree : {}", id);
        assureeRepository.delete(id);
        assureeSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/assurees?query=:query : search for the assuree corresponding
     * to the query.
     *
     * @param query the query of the assuree search
     * @return the result of the search
     */
    @GetMapping("/_search/assurees")
    @Timed
    public List<Assuree> searchAssurees(@RequestParam String query) {
        log.debug("REST request to search Assurees for query {}", query);
        return StreamSupport
            .stream(assureeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
