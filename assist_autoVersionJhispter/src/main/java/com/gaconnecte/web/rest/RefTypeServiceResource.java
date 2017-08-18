package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.RefTypeService;

import com.gaconnecte.repository.RefTypeServiceRepository;
import com.gaconnecte.repository.search.RefTypeServiceSearchRepository;
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
 * REST controller for managing RefTypeService.
 */
@RestController
@RequestMapping("/api")
public class RefTypeServiceResource {

    private final Logger log = LoggerFactory.getLogger(RefTypeServiceResource.class);

    private static final String ENTITY_NAME = "refTypeService";

    private final RefTypeServiceRepository refTypeServiceRepository;

    private final RefTypeServiceSearchRepository refTypeServiceSearchRepository;

    public RefTypeServiceResource(RefTypeServiceRepository refTypeServiceRepository, RefTypeServiceSearchRepository refTypeServiceSearchRepository) {
        this.refTypeServiceRepository = refTypeServiceRepository;
        this.refTypeServiceSearchRepository = refTypeServiceSearchRepository;
    }

    /**
     * POST  /ref-type-services : Create a new refTypeService.
     *
     * @param refTypeService the refTypeService to create
     * @return the ResponseEntity with status 201 (Created) and with body the new refTypeService, or with status 400 (Bad Request) if the refTypeService has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ref-type-services")
    @Timed
    public ResponseEntity<RefTypeService> createRefTypeService(@Valid @RequestBody RefTypeService refTypeService) throws URISyntaxException {
        log.debug("REST request to save RefTypeService : {}", refTypeService);
        if (refTypeService.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new refTypeService cannot already have an ID")).body(null);
        }
        RefTypeService result = refTypeServiceRepository.save(refTypeService);
        refTypeServiceSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/ref-type-services/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ref-type-services : Updates an existing refTypeService.
     *
     * @param refTypeService the refTypeService to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated refTypeService,
     * or with status 400 (Bad Request) if the refTypeService is not valid,
     * or with status 500 (Internal Server Error) if the refTypeService couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ref-type-services")
    @Timed
    public ResponseEntity<RefTypeService> updateRefTypeService(@Valid @RequestBody RefTypeService refTypeService) throws URISyntaxException {
        log.debug("REST request to update RefTypeService : {}", refTypeService);
        if (refTypeService.getId() == null) {
            return createRefTypeService(refTypeService);
        }
        RefTypeService result = refTypeServiceRepository.save(refTypeService);
        refTypeServiceSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, refTypeService.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ref-type-services : get all the refTypeServices.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of refTypeServices in body
     */
    @GetMapping("/ref-type-services")
    @Timed
    public List<RefTypeService> getAllRefTypeServices() {
        log.debug("REST request to get all RefTypeServices");
        return refTypeServiceRepository.findAll();
    }

    /**
     * GET  /ref-type-services/:id : get the "id" refTypeService.
     *
     * @param id the id of the refTypeService to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the refTypeService, or with status 404 (Not Found)
     */
    @GetMapping("/ref-type-services/{id}")
    @Timed
    public ResponseEntity<RefTypeService> getRefTypeService(@PathVariable Long id) {
        log.debug("REST request to get RefTypeService : {}", id);
        RefTypeService refTypeService = refTypeServiceRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(refTypeService));
    }

    /**
     * DELETE  /ref-type-services/:id : delete the "id" refTypeService.
     *
     * @param id the id of the refTypeService to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ref-type-services/{id}")
    @Timed
    public ResponseEntity<Void> deleteRefTypeService(@PathVariable Long id) {
        log.debug("REST request to delete RefTypeService : {}", id);
        refTypeServiceRepository.delete(id);
        refTypeServiceSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ref-type-services?query=:query : search for the refTypeService corresponding
     * to the query.
     *
     * @param query the query of the refTypeService search
     * @return the result of the search
     */
    @GetMapping("/_search/ref-type-services")
    @Timed
    public List<RefTypeService> searchRefTypeServices(@RequestParam String query) {
        log.debug("REST request to search RefTypeServices for query {}", query);
        return StreamSupport
            .stream(refTypeServiceSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
