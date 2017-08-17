package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.RefCompagnie;

import com.gaconnecte.repository.RefCompagnieRepository;
import com.gaconnecte.repository.search.RefCompagnieSearchRepository;
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
 * REST controller for managing RefCompagnie.
 */
@RestController
@RequestMapping("/api")
public class RefCompagnieResource {

    private final Logger log = LoggerFactory.getLogger(RefCompagnieResource.class);

    private static final String ENTITY_NAME = "refCompagnie";

    private final RefCompagnieRepository refCompagnieRepository;

    private final RefCompagnieSearchRepository refCompagnieSearchRepository;

    public RefCompagnieResource(RefCompagnieRepository refCompagnieRepository, RefCompagnieSearchRepository refCompagnieSearchRepository) {
        this.refCompagnieRepository = refCompagnieRepository;
        this.refCompagnieSearchRepository = refCompagnieSearchRepository;
    }

    /**
     * POST  /ref-compagnies : Create a new refCompagnie.
     *
     * @param refCompagnie the refCompagnie to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refCompagnie, or with status 400 (Bad Request) if the refCompagnie has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-compagnies")
    @Timed
    public ResponseEntity<RefCompagnie> createRefCompagnie(@Valid @RequestBody RefCompagnie refCompagnie) throws URISyntaxException {
        log.debug("REST request to save RefCompagnie : {}", refCompagnie);
        if (refCompagnie.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new refCompagnie cannot already have an ID")).body(null);
        }
        RefCompagnie result = refCompagnieRepository.save(refCompagnie);
        refCompagnieSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/ref-compagnies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-compagnies : Updates an existing refCompagnie.
     *
     * @param refCompagnie the refCompagnie to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refCompagnie,
     * or with status 400 (Bad Request) if the refCompagnie is not valid,
     * or with status 500 (Internal Server Error) if the refCompagnie couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-compagnies")
    @Timed
    public ResponseEntity<RefCompagnie> updateRefCompagnie(@Valid @RequestBody RefCompagnie refCompagnie) throws URISyntaxException {
        log.debug("REST request to update RefCompagnie : {}", refCompagnie);
        if (refCompagnie.getId() == null) {
            return createRefCompagnie(refCompagnie);
        }
        RefCompagnie result = refCompagnieRepository.save(refCompagnie);
        refCompagnieSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refCompagnie.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-compagnies : get all the refCompagnies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refCompagnies in body
     */
    @GetMapping("/ref-compagnies")
    @Timed
    public List<RefCompagnie> getAllRefCompagnies() {
        log.debug("REST request to get all RefCompagnies");
        return refCompagnieRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /ref-compagnies/:id : get the "id" refCompagnie.
     *
     * @param id the id of the refCompagnie to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refCompagnie, or with status 404 (Not Found)
     */
    @GetMapping("/ref-compagnies/{id}")
    @Timed
    public ResponseEntity<RefCompagnie> getRefCompagnie(@PathVariable Long id) {
        log.debug("REST request to get RefCompagnie : {}", id);
        RefCompagnie refCompagnie = refCompagnieRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(refCompagnie));
    }

    /**
     * DELETE  /ref-compagnies/:id : delete the "id" refCompagnie.
     *
     * @param id the id of the refCompagnie to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-compagnies/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefCompagnie(@PathVariable Long id) {
        log.debug("REST request to delete RefCompagnie : {}", id);
        refCompagnieRepository.delete(id);
        refCompagnieSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ref-compagnies?query=:query : search for the refCompagnie corresponding
     * to the query.
     *
     * @param query the query of the refCompagnie search
     * @return the result of the search
     */
    @GetMapping("/_search/ref-compagnies")
    @Timed
    public List<RefCompagnie> searchRefCompagnies(@RequestParam String query) {
        log.debug("REST request to search RefCompagnies for query {}", query);
        return StreamSupport
            .stream(refCompagnieSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
