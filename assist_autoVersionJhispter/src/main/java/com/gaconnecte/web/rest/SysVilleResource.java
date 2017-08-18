package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.SysVille;

import com.gaconnecte.repository.SysVilleRepository;
import com.gaconnecte.repository.search.SysVilleSearchRepository;
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
 * REST controller for managing SysVille.
 */
@RestController
@RequestMapping("/api")
public class SysVilleResource {

    private final Logger log = LoggerFactory.getLogger(SysVilleResource.class);

    private static final String ENTITY_NAME = "sysVille";

    private final SysVilleRepository sysVilleRepository;

    private final SysVilleSearchRepository sysVilleSearchRepository;

    public SysVilleResource(SysVilleRepository sysVilleRepository, SysVilleSearchRepository sysVilleSearchRepository) {
        this.sysVilleRepository = sysVilleRepository;
        this.sysVilleSearchRepository = sysVilleSearchRepository;
    }

    /**
     * POST  /sys-villes : Create a new sysVille.
     *
     * @param sysVille the sysVille to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysVille, or with status 400 (Bad Request) if the sysVille has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-villes")
    @Timed
    public ResponseEntity<SysVille> createSysVille(@Valid @RequestBody SysVille sysVille) throws URISyntaxException {
        log.debug("REST request to save SysVille : {}", sysVille);
        if (sysVille.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sysVille cannot already have an ID")).body(null);
        }
        SysVille result = sysVilleRepository.save(sysVille);
        sysVilleSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sys-villes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-villes : Updates an existing sysVille.
     *
     * @param sysVille the sysVille to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysVille,
     * or with status 400 (Bad Request) if the sysVille is not valid,
     * or with status 500 (Internal Server Error) if the sysVille couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-villes")
    @Timed
    public ResponseEntity<SysVille> updateSysVille(@Valid @RequestBody SysVille sysVille) throws URISyntaxException {
        log.debug("REST request to update SysVille : {}", sysVille);
        if (sysVille.getId() == null) {
            return createSysVille(sysVille);
        }
        SysVille result = sysVilleRepository.save(sysVille);
        sysVilleSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysVille.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-villes : get all the sysVilles.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysVilles in body
     */
    @GetMapping("/sys-villes")
    @Timed
    public List<SysVille> getAllSysVilles() {
        log.debug("REST request to get all SysVilles");
        return sysVilleRepository.findAll();
    }

    /**
     * GET  /sys-villes/:id : get the "id" sysVille.
     *
     * @param id the id of the sysVille to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysVille, or with status 404 (Not Found)
     */
    @GetMapping("/sys-villes/{id}")
    @Timed
    public ResponseEntity<SysVille> getSysVille(@PathVariable Long id) {
        log.debug("REST request to get SysVille : {}", id);
        SysVille sysVille = sysVilleRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sysVille));
    }

    /**
     * DELETE  /sys-villes/:id : delete the "id" sysVille.
     *
     * @param id the id of the sysVille to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-villes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysVille(@PathVariable Long id) {
        log.debug("REST request to delete SysVille : {}", id);
        sysVilleRepository.delete(id);
        sysVilleSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sys-villes?query=:query : search for the sysVille corresponding
     * to the query.
     *
     * @param query the query of the sysVille search
     * @return the result of the search
     */
    @GetMapping("/_search/sys-villes")
    @Timed
    public List<SysVille> searchSysVilles(@RequestParam String query) {
        log.debug("REST request to search SysVilles for query {}", query);
        return StreamSupport
            .stream(sysVilleSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
