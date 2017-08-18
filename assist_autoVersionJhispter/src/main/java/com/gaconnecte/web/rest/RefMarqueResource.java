package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.RefMarque;

import com.gaconnecte.repository.RefMarqueRepository;
import com.gaconnecte.repository.search.RefMarqueSearchRepository;
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
 * REST controller for managing RefMarque.
 */
@RestController
@RequestMapping("/api")
public class RefMarqueResource {

    private final Logger log = LoggerFactory.getLogger(RefMarqueResource.class);

    private static final String ENTITY_NAME = "refMarque";

    private final RefMarqueRepository refMarqueRepository;

    private final RefMarqueSearchRepository refMarqueSearchRepository;

    public RefMarqueResource(RefMarqueRepository refMarqueRepository, RefMarqueSearchRepository refMarqueSearchRepository) {
        this.refMarqueRepository = refMarqueRepository;
        this.refMarqueSearchRepository = refMarqueSearchRepository;
    }

    /**
     * POST  /ref-marques : Create a new refMarque.
     *
     * @param refMarque the refMarque to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refMarque, or with status 400 (Bad Request) if the refMarque has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-marques")
    @Timed
    public ResponseEntity<RefMarque> createRefMarque(@Valid @RequestBody RefMarque refMarque) throws URISyntaxException {
        log.debug("REST request to save RefMarque : {}", refMarque);
        if (refMarque.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new refMarque cannot already have an ID")).body(null);
        }
        RefMarque result = refMarqueRepository.save(refMarque);
        refMarqueSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/ref-marques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-marques : Updates an existing refMarque.
     *
     * @param refMarque the refMarque to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refMarque,
     * or with status 400 (Bad Request) if the refMarque is not valid,
     * or with status 500 (Internal Server Error) if the refMarque couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-marques")
    @Timed
    public ResponseEntity<RefMarque> updateRefMarque(@Valid @RequestBody RefMarque refMarque) throws URISyntaxException {
        log.debug("REST request to update RefMarque : {}", refMarque);
        if (refMarque.getId() == null) {
            return createRefMarque(refMarque);
        }
        RefMarque result = refMarqueRepository.save(refMarque);
        refMarqueSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refMarque.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-marques : get all the refMarques.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refMarques in body
     */
    @GetMapping("/ref-marques")
    @Timed
    public List<RefMarque> getAllRefMarques() {
        log.debug("REST request to get all RefMarques");
        return refMarqueRepository.findAll();
    }

    /**
     * GET  /ref-marques/:id : get the "id" refMarque.
     *
     * @param id the id of the refMarque to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refMarque, or with status 404 (Not Found)
     */
    @GetMapping("/ref-marques/{id}")
    @Timed
    public ResponseEntity<RefMarque> getRefMarque(@PathVariable Long id) {
        log.debug("REST request to get RefMarque : {}", id);
        RefMarque refMarque = refMarqueRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(refMarque));
    }

    /**
     * DELETE  /ref-marques/:id : delete the "id" refMarque.
     *
     * @param id the id of the refMarque to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-marques/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefMarque(@PathVariable Long id) {
        log.debug("REST request to delete RefMarque : {}", id);
        refMarqueRepository.delete(id);
        refMarqueSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ref-marques?query=:query : search for the refMarque corresponding
     * to the query.
     *
     * @param query the query of the refMarque search
     * @return the result of the search
     */
    @GetMapping("/_search/ref-marques")
    @Timed
    public List<RefMarque> searchRefMarques(@RequestParam String query) {
        log.debug("REST request to search RefMarques for query {}", query);
        return StreamSupport
            .stream(refMarqueSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
