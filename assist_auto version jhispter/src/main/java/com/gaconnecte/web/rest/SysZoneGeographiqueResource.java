package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.SysZoneGeographique;

import com.gaconnecte.repository.SysZoneGeographiqueRepository;
import com.gaconnecte.repository.search.SysZoneGeographiqueSearchRepository;
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
 * REST controller for managing SysZoneGeographique.
 */
@RestController
@RequestMapping("/api")
public class SysZoneGeographiqueResource {

    private final Logger log = LoggerFactory.getLogger(SysZoneGeographiqueResource.class);

    private static final String ENTITY_NAME = "sysZoneGeographique";

    private final SysZoneGeographiqueRepository sysZoneGeographiqueRepository;

    private final SysZoneGeographiqueSearchRepository sysZoneGeographiqueSearchRepository;

    public SysZoneGeographiqueResource(SysZoneGeographiqueRepository sysZoneGeographiqueRepository, SysZoneGeographiqueSearchRepository sysZoneGeographiqueSearchRepository) {
        this.sysZoneGeographiqueRepository = sysZoneGeographiqueRepository;
        this.sysZoneGeographiqueSearchRepository = sysZoneGeographiqueSearchRepository;
    }

    /**
     * POST  /sys-zone-geographiques : Create a new sysZoneGeographique.
     *
     * @param sysZoneGeographique the sysZoneGeographique to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sysZoneGeographique, or with status 400 (Bad Request) if the sysZoneGeographique has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sys-zone-geographiques")
    @Timed
    public ResponseEntity<SysZoneGeographique> createSysZoneGeographique(@Valid @RequestBody SysZoneGeographique sysZoneGeographique) throws URISyntaxException {
        log.debug("REST request to save SysZoneGeographique : {}", sysZoneGeographique);
        if (sysZoneGeographique.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sysZoneGeographique cannot already have an ID")).body(null);
        }
        SysZoneGeographique result = sysZoneGeographiqueRepository.save(sysZoneGeographique);
        sysZoneGeographiqueSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sys-zone-geographiques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sys-zone-geographiques : Updates an existing sysZoneGeographique.
     *
     * @param sysZoneGeographique the sysZoneGeographique to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sysZoneGeographique,
     * or with status 400 (Bad Request) if the sysZoneGeographique is not valid,
     * or with status 500 (Internal Server Error) if the sysZoneGeographique couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sys-zone-geographiques")
    @Timed
    public ResponseEntity<SysZoneGeographique> updateSysZoneGeographique(@Valid @RequestBody SysZoneGeographique sysZoneGeographique) throws URISyntaxException {
        log.debug("REST request to update SysZoneGeographique : {}", sysZoneGeographique);
        if (sysZoneGeographique.getId() == null) {
            return createSysZoneGeographique(sysZoneGeographique);
        }
        SysZoneGeographique result = sysZoneGeographiqueRepository.save(sysZoneGeographique);
        sysZoneGeographiqueSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sysZoneGeographique.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sys-zone-geographiques : get all the sysZoneGeographiques.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sysZoneGeographiques in body
     */
    @GetMapping("/sys-zone-geographiques")
    @Timed
    public List<SysZoneGeographique> getAllSysZoneGeographiques() {
        log.debug("REST request to get all SysZoneGeographiques");
        return sysZoneGeographiqueRepository.findAll();
    }

    /**
     * GET  /sys-zone-geographiques/:id : get the "id" sysZoneGeographique.
     *
     * @param id the id of the sysZoneGeographique to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sysZoneGeographique, or with status 404 (Not Found)
     */
    @GetMapping("/sys-zone-geographiques/{id}")
    @Timed
    public ResponseEntity<SysZoneGeographique> getSysZoneGeographique(@PathVariable Long id) {
        log.debug("REST request to get SysZoneGeographique : {}", id);
        SysZoneGeographique sysZoneGeographique = sysZoneGeographiqueRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sysZoneGeographique));
    }

    /**
     * DELETE  /sys-zone-geographiques/:id : delete the "id" sysZoneGeographique.
     *
     * @param id the id of the sysZoneGeographique to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sys-zone-geographiques/{id}")
    @Timed
    public ResponseEntity<Void> deleteSysZoneGeographique(@PathVariable Long id) {
        log.debug("REST request to delete SysZoneGeographique : {}", id);
        sysZoneGeographiqueRepository.delete(id);
        sysZoneGeographiqueSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sys-zone-geographiques?query=:query : search for the sysZoneGeographique corresponding
     * to the query.
     *
     * @param query the query of the sysZoneGeographique search
     * @return the result of the search
     */
    @GetMapping("/_search/sys-zone-geographiques")
    @Timed
    public List<SysZoneGeographique> searchSysZoneGeographiques(@RequestParam String query) {
        log.debug("REST request to search SysZoneGeographiques for query {}", query);
        return StreamSupport
            .stream(sysZoneGeographiqueSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
