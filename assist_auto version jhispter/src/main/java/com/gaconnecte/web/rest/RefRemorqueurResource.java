package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.RefRemorqueur;

import com.gaconnecte.repository.RefRemorqueurRepository;
import com.gaconnecte.repository.search.RefRemorqueurSearchRepository;
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
 * REST controller for managing RefRemorqueur.
 */
@RestController
@RequestMapping("/api")
public class RefRemorqueurResource {

    private final Logger log = LoggerFactory.getLogger(RefRemorqueurResource.class);

    private static final String ENTITY_NAME = "refRemorqueur";

    private final RefRemorqueurRepository refRemorqueurRepository;

    private final RefRemorqueurSearchRepository refRemorqueurSearchRepository;

    public RefRemorqueurResource(RefRemorqueurRepository refRemorqueurRepository, RefRemorqueurSearchRepository refRemorqueurSearchRepository) {
        this.refRemorqueurRepository = refRemorqueurRepository;
        this.refRemorqueurSearchRepository = refRemorqueurSearchRepository;
    }

    /**
     * POST  /ref-remorqueurs : Create a new refRemorqueur.
     *
     * @param refRemorqueur the refRemorqueur to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refRemorqueur, or with status 400 (Bad Request) if the refRemorqueur has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-remorqueurs")
    @Timed
    public ResponseEntity<RefRemorqueur> createRefRemorqueur(@Valid @RequestBody RefRemorqueur refRemorqueur) throws URISyntaxException {
        log.debug("REST request to save RefRemorqueur : {}", refRemorqueur);
        if (refRemorqueur.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new refRemorqueur cannot already have an ID")).body(null);
        }
        RefRemorqueur result = refRemorqueurRepository.save(refRemorqueur);
        refRemorqueurSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/ref-remorqueurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-remorqueurs : Updates an existing refRemorqueur.
     *
     * @param refRemorqueur the refRemorqueur to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refRemorqueur,
     * or with status 400 (Bad Request) if the refRemorqueur is not valid,
     * or with status 500 (Internal Server Error) if the refRemorqueur couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-remorqueurs")
    @Timed
    public ResponseEntity<RefRemorqueur> updateRefRemorqueur(@Valid @RequestBody RefRemorqueur refRemorqueur) throws URISyntaxException {
        log.debug("REST request to update RefRemorqueur : {}", refRemorqueur);
        if (refRemorqueur.getId() == null) {
            return createRefRemorqueur(refRemorqueur);
        }
        RefRemorqueur result = refRemorqueurRepository.save(refRemorqueur);
        refRemorqueurSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refRemorqueur.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-remorqueurs : get all the refRemorqueurs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refRemorqueurs in body
     */
    @GetMapping("/ref-remorqueurs")
    @Timed
    public List<RefRemorqueur> getAllRefRemorqueurs() {
        log.debug("REST request to get all RefRemorqueurs");
        return refRemorqueurRepository.findAll();
    }

    /**
     * GET  /ref-remorqueurs/:id : get the "id" refRemorqueur.
     *
     * @param id the id of the refRemorqueur to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refRemorqueur, or with status 404 (Not Found)
     */
    @GetMapping("/ref-remorqueurs/{id}")
    @Timed
    public ResponseEntity<RefRemorqueur> getRefRemorqueur(@PathVariable Long id) {
        log.debug("REST request to get RefRemorqueur : {}", id);
        RefRemorqueur refRemorqueur = refRemorqueurRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(refRemorqueur));
    }

    /**
     * DELETE  /ref-remorqueurs/:id : delete the "id" refRemorqueur.
     *
     * @param id the id of the refRemorqueur to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-remorqueurs/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefRemorqueur(@PathVariable Long id) {
        log.debug("REST request to delete RefRemorqueur : {}", id);
        refRemorqueurRepository.delete(id);
        refRemorqueurSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ref-remorqueurs?query=:query : search for the refRemorqueur corresponding
     * to the query.
     *
     * @param query the query of the refRemorqueur search
     * @return the result of the search
     */
    @GetMapping("/_search/ref-remorqueurs")
    @Timed
    public List<RefRemorqueur> searchRefRemorqueurs(@RequestParam String query) {
        log.debug("REST request to search RefRemorqueurs for query {}", query);
        return StreamSupport
            .stream(refRemorqueurSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
