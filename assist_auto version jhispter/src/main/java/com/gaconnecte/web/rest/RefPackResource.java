package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.RefPack;

import com.gaconnecte.repository.RefPackRepository;
import com.gaconnecte.repository.search.RefPackSearchRepository;
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
 * REST controller for managing RefPack.
 */
@RestController
@RequestMapping("/api")
public class RefPackResource {

    private final Logger log = LoggerFactory.getLogger(RefPackResource.class);

    private static final String ENTITY_NAME = "refPack";

    private final RefPackRepository refPackRepository;

    private final RefPackSearchRepository refPackSearchRepository;

    public RefPackResource(RefPackRepository refPackRepository, RefPackSearchRepository refPackSearchRepository) {
        this.refPackRepository = refPackRepository;
        this.refPackSearchRepository = refPackSearchRepository;
    }

    /**
     * POST  /ref-packs : Create a new refPack.
     *
     * @param refPack the refPack to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refPack, or with status 400 (Bad Request) if the refPack has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-packs")
    @Timed
    public ResponseEntity<RefPack> createRefPack(@Valid @RequestBody RefPack refPack) throws URISyntaxException {
        log.debug("REST request to save RefPack : {}", refPack);
        if (refPack.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new refPack cannot already have an ID")).body(null);
        }
        RefPack result = refPackRepository.save(refPack);
        refPackSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/ref-packs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-packs : Updates an existing refPack.
     *
     * @param refPack the refPack to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refPack,
     * or with status 400 (Bad Request) if the refPack is not valid,
     * or with status 500 (Internal Server Error) if the refPack couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-packs")
    @Timed
    public ResponseEntity<RefPack> updateRefPack(@Valid @RequestBody RefPack refPack) throws URISyntaxException {
        log.debug("REST request to update RefPack : {}", refPack);
        if (refPack.getId() == null) {
            return createRefPack(refPack);
        }
        RefPack result = refPackRepository.save(refPack);
        refPackSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refPack.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-packs : get all the refPacks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refPacks in body
     */
    @GetMapping("/ref-packs")
    @Timed
    public List<RefPack> getAllRefPacks() {
        log.debug("REST request to get all RefPacks");
        return refPackRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /ref-packs/:id : get the "id" refPack.
     *
     * @param id the id of the refPack to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refPack, or with status 404 (Not Found)
     */
    @GetMapping("/ref-packs/{id}")
    @Timed
    public ResponseEntity<RefPack> getRefPack(@PathVariable Long id) {
        log.debug("REST request to get RefPack : {}", id);
        RefPack refPack = refPackRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(refPack));
    }

    /**
     * DELETE  /ref-packs/:id : delete the "id" refPack.
     *
     * @param id the id of the refPack to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-packs/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefPack(@PathVariable Long id) {
        log.debug("REST request to delete RefPack : {}", id);
        refPackRepository.delete(id);
        refPackSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ref-packs?query=:query : search for the refPack corresponding
     * to the query.
     *
     * @param query the query of the refPack search
     * @return the result of the search
     */
    @GetMapping("/_search/ref-packs")
    @Timed
    public List<RefPack> searchRefPacks(@RequestParam String query) {
        log.debug("REST request to search RefPacks for query {}", query);
        return StreamSupport
            .stream(refPackSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
