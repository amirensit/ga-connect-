package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.RefMarque;
import com.gaconnecte.repository.RefMarqueRepository;
import com.gaconnecte.repository.search.RefMarqueSearchRepository;
import com.gaconnecte.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RefMarqueResource REST controller.
 *
 * @see RefMarqueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class RefMarqueResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private RefMarqueRepository refMarqueRepository;

    @Autowired
    private RefMarqueSearchRepository refMarqueSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefMarqueMockMvc;

    private RefMarque refMarque;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RefMarqueResource refMarqueResource = new RefMarqueResource(refMarqueRepository, refMarqueSearchRepository);
        this.restRefMarqueMockMvc = MockMvcBuilders.standaloneSetup(refMarqueResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RefMarque createEntity(EntityManager em) {
        RefMarque refMarque = new RefMarque()
            .nom(DEFAULT_NOM);
        return refMarque;
    }

    @Before
    public void initTest() {
        refMarqueSearchRepository.deleteAll();
        refMarque = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefMarque() throws Exception {
        int databaseSizeBeforeCreate = refMarqueRepository.findAll().size();

        // Create the RefMarque
        restRefMarqueMockMvc.perform(post("/api/ref-marques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMarque)))
            .andExpect(status().isCreated());

        // Validate the RefMarque in the database
        List<RefMarque> refMarqueList = refMarqueRepository.findAll();
        assertThat(refMarqueList).hasSize(databaseSizeBeforeCreate + 1);
        RefMarque testRefMarque = refMarqueList.get(refMarqueList.size() - 1);
        assertThat(testRefMarque.getNom()).isEqualTo(DEFAULT_NOM);

        // Validate the RefMarque in Elasticsearch
        RefMarque refMarqueEs = refMarqueSearchRepository.findOne(testRefMarque.getId());
        assertThat(refMarqueEs).isEqualToComparingFieldByField(testRefMarque);
    }

    @Test
    @Transactional
    public void createRefMarqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refMarqueRepository.findAll().size();

        // Create the RefMarque with an existing ID
        refMarque.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefMarqueMockMvc.perform(post("/api/ref-marques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMarque)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RefMarque> refMarqueList = refMarqueRepository.findAll();
        assertThat(refMarqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = refMarqueRepository.findAll().size();
        // set the field null
        refMarque.setNom(null);

        // Create the RefMarque, which fails.

        restRefMarqueMockMvc.perform(post("/api/ref-marques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMarque)))
            .andExpect(status().isBadRequest());

        List<RefMarque> refMarqueList = refMarqueRepository.findAll();
        assertThat(refMarqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefMarques() throws Exception {
        // Initialize the database
        refMarqueRepository.saveAndFlush(refMarque);

        // Get all the refMarqueList
        restRefMarqueMockMvc.perform(get("/api/ref-marques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refMarque.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void getRefMarque() throws Exception {
        // Initialize the database
        refMarqueRepository.saveAndFlush(refMarque);

        // Get the refMarque
        restRefMarqueMockMvc.perform(get("/api/ref-marques/{id}", refMarque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refMarque.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefMarque() throws Exception {
        // Get the refMarque
        restRefMarqueMockMvc.perform(get("/api/ref-marques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefMarque() throws Exception {
        // Initialize the database
        refMarqueRepository.saveAndFlush(refMarque);
        refMarqueSearchRepository.save(refMarque);
        int databaseSizeBeforeUpdate = refMarqueRepository.findAll().size();

        // Update the refMarque
        RefMarque updatedRefMarque = refMarqueRepository.findOne(refMarque.getId());
        updatedRefMarque
            .nom(UPDATED_NOM);

        restRefMarqueMockMvc.perform(put("/api/ref-marques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefMarque)))
            .andExpect(status().isOk());

        // Validate the RefMarque in the database
        List<RefMarque> refMarqueList = refMarqueRepository.findAll();
        assertThat(refMarqueList).hasSize(databaseSizeBeforeUpdate);
        RefMarque testRefMarque = refMarqueList.get(refMarqueList.size() - 1);
        assertThat(testRefMarque.getNom()).isEqualTo(UPDATED_NOM);

        // Validate the RefMarque in Elasticsearch
        RefMarque refMarqueEs = refMarqueSearchRepository.findOne(testRefMarque.getId());
        assertThat(refMarqueEs).isEqualToComparingFieldByField(testRefMarque);
    }

    @Test
    @Transactional
    public void updateNonExistingRefMarque() throws Exception {
        int databaseSizeBeforeUpdate = refMarqueRepository.findAll().size();

        // Create the RefMarque

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRefMarqueMockMvc.perform(put("/api/ref-marques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refMarque)))
            .andExpect(status().isCreated());

        // Validate the RefMarque in the database
        List<RefMarque> refMarqueList = refMarqueRepository.findAll();
        assertThat(refMarqueList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRefMarque() throws Exception {
        // Initialize the database
        refMarqueRepository.saveAndFlush(refMarque);
        refMarqueSearchRepository.save(refMarque);
        int databaseSizeBeforeDelete = refMarqueRepository.findAll().size();

        // Get the refMarque
        restRefMarqueMockMvc.perform(delete("/api/ref-marques/{id}", refMarque.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean refMarqueExistsInEs = refMarqueSearchRepository.exists(refMarque.getId());
        assertThat(refMarqueExistsInEs).isFalse();

        // Validate the database is empty
        List<RefMarque> refMarqueList = refMarqueRepository.findAll();
        assertThat(refMarqueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchRefMarque() throws Exception {
        // Initialize the database
        refMarqueRepository.saveAndFlush(refMarque);
        refMarqueSearchRepository.save(refMarque);

        // Search the refMarque
        restRefMarqueMockMvc.perform(get("/api/_search/ref-marques?query=id:" + refMarque.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refMarque.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefMarque.class);
        RefMarque refMarque1 = new RefMarque();
        refMarque1.setId(1L);
        RefMarque refMarque2 = new RefMarque();
        refMarque2.setId(refMarque1.getId());
        assertThat(refMarque1).isEqualTo(refMarque2);
        refMarque2.setId(2L);
        assertThat(refMarque1).isNotEqualTo(refMarque2);
        refMarque1.setId(null);
        assertThat(refMarque1).isNotEqualTo(refMarque2);
    }
}
