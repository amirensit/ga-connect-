package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.SysGouvernorat;

import com.gaconnecte.repository.SysGouvernoratRepository;
import com.gaconnecte.repository.search.SysGouvernoratSearchRepository;
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
 * REST controller for managing SysGouvernorat.
 */
@RestController
@RequestMapping("/api")
public class SysGouvernoratResource {

    private final Logger log = LoggerFactory.getLogger(SysGouvernoratResource.class);

    private static final String ENTITY_NAME = "sysGouvernorat";

    private final SysGouvernoratRepository sysGouvernoratRepository;

    private final SysGouvernoratSearchRepository sysGouvernoratSearchRepository;

    public SysGouvernoratResource(SysGouvernoratRepository sysGouvernoratRepository, SysGouvernoratSearchRepository sysGouvernoratSearchRepository) {
        this.sysGouvernoratRepository = sysGouvernoratRepository;
        this.sysGouvernoratSearchRepository = sysGouvernoratSearchRepository;
    }

    /**
     * POST  /sys-gouvernorats : Create a new sysGouvernorat.
     *
     * @param sysGouvernorat the sysGouvernorat to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysGouvernorat, or with status 400 (Bad Request) if the sysGouvernorat has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-gouvernorats")
    @Timed
    public ResponseEntity<SysGouvernorat> createSysGouvernorat(@Valid @RequestBody SysGouvernorat sysGouvernorat) throws URISyntaxException {
        log.debug("REST request to save SysGouvernorat : {}", sysGouvernorat);
        if (sysGouvernorat.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sysGouvernorat cannot already have an ID")).body(null);
        }
        SysGouvernorat result = sysGouvernoratRepository.save(sysGouvernorat);
        sysGouvernoratSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sys-gouvernorats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-gouvernorats : Updates an existing sysGouvernorat.
     *
     * @param sysGouvernorat the sysGouvernorat to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysGouvernorat,
     * or with status 400 (Bad Request) if the sysGouvernorat is not valid,
     * or with status 500 (Internal Server Error) if the sysGouvernorat couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-gouvernorats")
    @Timed
    public ResponseEntity<SysGouvernorat> updateSysGouvernorat(@Valid @RequestBody SysGouvernorat sysGouvernorat) throws URISyntaxException {
        log.debug("REST request to update SysGouvernorat : {}", sysGouvernorat);
        if (sysGouvernorat.getId() == null) {
            return createSysGouvernorat(sysGouvernorat);
        }
        SysGouvernorat result = sysGouvernoratRepository.save(sysGouvernorat);
        sysGouvernoratSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysGouvernorat.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-gouvernorats : get all the sysGouvernorats.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysGouvernorats in body
     */
    @GetMapping("/sys-gouvernorats")
    @Timed
    public List<SysGouvernorat> getAllSysGouvernorats() {
        log.debug("REST request to get all SysGouvernorats");
        return sysGouvernoratRepository.findAll();
    }

    /**
     * GET  /sys-gouvernorats/:id : get the "id" sysGouvernorat.
     *
     * @param id the id of the sysGouvernorat to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysGouvernorat, or with status 404 (Not Found)
     */
    @GetMapping("/sys-gouvernorats/{id}")
    @Timed
    public ResponseEntity<SysGouvernorat> getSysGouvernorat(@PathVariable Long id) {
        log.debug("REST request to get SysGouvernorat : {}", id);
        SysGouvernorat sysGouvernorat = sysGouvernoratRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sysGouvernorat));
    }

    /**
     * DELETE  /sys-gouvernorats/:id : delete the "id" sysGouvernorat.
     *
     * @param id the id of the sysGouvernorat to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-gouvernorats/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysGouvernorat(@PathVariable Long id) {
        log.debug("REST request to delete SysGouvernorat : {}", id);
        sysGouvernoratRepository.delete(id);
        sysGouvernoratSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sys-gouvernorats?query=:query : search for the sysGouvernorat corresponding
     * to the query.
     *
     * @param query the query of the sysGouvernorat search
     * @return the result of the search
     */
    @GetMapping("/_search/sys-gouvernorats")
    @Timed
    public List<SysGouvernorat> searchSysGouvernorats(@RequestParam String query) {
        log.debug("REST request to search SysGouvernorats for query {}", query);
        return StreamSupport
            .stream(sysGouvernoratSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
