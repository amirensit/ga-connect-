package com.gaconnecte.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.gaconnecte.domain.Personne;

import com.gaconnecte.repository.PersonneRepository;
import com.gaconnecte.repository.search.PersonneSearchRepository;
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
 * REST controller for managing Personne.
 */
@RestController
@RequestMapping("/api")
public class PersonneResource {

    private final Logger log = LoggerFactory.getLogger(PersonneResource.class);

    private static final String ENTITY_NAME = "personne";

    private final PersonneRepository personneRepository;

    private final PersonneSearchRepository personneSearchRepository;

    public PersonneResource(PersonneRepository personneRepository, PersonneSearchRepository personneSearchRepository) {
        this.personneRepository = personneRepository;
        this.personneSearchRepository = personneSearchRepository;
    }

    /**
     * POST  /personnes : Create a new personne.
     *
     * @param personne the personne to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personne, or with status 400 (Bad Request) if the personne has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/personnes")
    @Timed
    public ResponseEntity<Personne> createPersonne(@Valid @RequestBody Personne personne) throws URISyntaxException {
        log.debug("REST request to save Personne : {}", personne);
        if (personne.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new personne cannot already have an ID")).body(null);
        }
        Personne result = personneRepository.save(personne);
        personneSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/personnes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /personnes : Updates an existing personne.
     *
     * @param personne the personne to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated personne,
     * or with status 400 (Bad Request) if the personne is not valid,
     * or with status 500 (Internal Server Error) if the personne couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/personnes")
    @Timed
    public ResponseEntity<Personne> updatePersonne(@Valid @RequestBody Personne personne) throws URISyntaxException {
        log.debug("REST request to update Personne : {}", personne);
        if (personne.getId() == null) {
            return createPersonne(personne);
        }
        Personne result = personneRepository.save(personne);
        personneSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, personne.getId().toString()))
            .body(result);
    }

    /**
     * GET  /personnes : get all the personnes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of personnes in body
     */
    @GetMapping("/personnes")
    @Timed
    public List<Personne> getAllPersonnes() {
        log.debug("REST request to get all Personnes");
        return personneRepository.findAll();
    }

    /**
     * GET  /personnes/:id : get the "id" personne.
     *
     * @param id the id of the personne to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the personne, or with status 404 (Not Found)
     */
    @GetMapping("/personnes/{id}")
    @Timed
    public ResponseEntity<Personne> getPersonne(@PathVariable Long id) {
        log.debug("REST request to get Personne : {}", id);
        Personne personne = personneRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(personne));
    }

    /**
     * DELETE  /personnes/:id : delete the "id" personne.
     *
     * @param id the id of the personne to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/personnes/{id}")
    @Timed
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        log.debug("REST request to delete Personne : {}", id);
        personneRepository.delete(id);
        personneSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/personnes?query=:query : search for the personne corresponding
     * to the query.
     *
     * @param query the query of the personne search
     * @return the result of the search
     */
    @GetMapping("/_search/personnes")
    @Timed
    public List<Personne> searchPersonnes(@RequestParam String query) {
        log.debug("REST request to search Personnes for query {}", query);
        return StreamSupport
            .stream(personneSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
