package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.Voiture;

import com.gaconnecte.repository.VoitureRepository;
import com.gaconnecte.repository.search.VoitureSearchRepository;
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
 * REST controller for managing Voiture.
 */
@RestController
@RequestMapping("/api")
public class VoitureResource {

    private final Logger log = LoggerFactory.getLogger(VoitureResource.class);

    private static final String ENTITY_NAME = "voiture";

    private final VoitureRepository voitureRepository;

    private final VoitureSearchRepository voitureSearchRepository;

    public VoitureResource(VoitureRepository voitureRepository, VoitureSearchRepository voitureSearchRepository) {
        this.voitureRepository = voitureRepository;
        this.voitureSearchRepository = voitureSearchRepository;
    }

    /**
     * POST  /voitures : Create a new voiture.
     *
     * @param voiture the voiture to create
     * @return the ResponseEntity with status 201 (Created) and with body the new voiture, or with status 400 (Bad Request) if the voiture has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/voitures")
    @Timed
    public ResponseEntity<Voiture> createVoiture(@Valid @RequestBody Voiture voiture) throws URISyntaxException {
        log.debug("REST request to save Voiture : {}", voiture);
        if (voiture.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new voiture cannot already have an ID")).body(null);
        }
        Voiture result = voitureRepository.saveAndFlush(voiture);
        voitureSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/voitures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /voitures : Updates an existing voiture.
     *
     * @param voiture the voiture to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated voiture,
     * or with status 400 (Bad Request) if the voiture is not valid,
     * or with status 500 (Internal Server Error) if the voiture couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/voitures")
    @Timed
    public ResponseEntity<Voiture> updateVoiture(@Valid @RequestBody Voiture voiture) throws URISyntaxException {
        log.debug("REST request to update Voiture : {}", voiture);
        if (voiture.getId() == null) {
            return createVoiture(voiture);
        }
        Voiture result = voitureRepository.save(voiture);
        voitureSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, voiture.getId().toString()))
            .body(result);
    }

    /**
     * GET  /voitures : get all the voitures.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of voitures in body
     */
    @GetMapping("/voitures")
    @Timed
    public List<Voiture> getAllVoitures(@RequestParam(required = false) String filter) {
        if ("contrat-is-null".equals(filter)) {
            log.debug("REST request to get all Voitures where contrat is null");
            return StreamSupport
                .stream(voitureRepository.findAll().spliterator(), false)
                .filter(voiture -> voiture.getContrat() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all Voitures");
        return voitureRepository.findAll();
    }

    /**
     * GET  /voitures/:id : get the "id" voiture.
     *
     * @param id the id of the voiture to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the voiture, or with status 404 (Not Found)
     */
    @GetMapping("/voitures/{id}")
    @Timed
    public ResponseEntity<Voiture> getVoiture(@PathVariable Long id) {
        log.debug("REST request to get Voiture : {}", id);
        Voiture voiture = voitureRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(voiture));
    }

    /**
     * DELETE  /voitures/:id : delete the "id" voiture.
     *
     * @param id the id of the voiture to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/voitures/{id}")
    @Timed
    public ResponseEntity<Void> deleteVoiture(@PathVariable Long id) {
        log.debug("REST request to delete Voiture : {}", id);
        voitureRepository.delete(id);
        voitureSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/voitures?query=:query : search for the voiture corresponding
     * to the query.
     *
     * @param query the query of the voiture search
     * @return the result of the search
     */
    @GetMapping("/_search/voitures")
    @Timed
    public List<Voiture> searchVoitures(@RequestParam String query) {
        log.debug("REST request to search Voitures for query {}", query);
        return StreamSupport
            .stream(voitureSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
