package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.DossierRmq;
import com.gaconnecte.repository.DossierRmqRepository;
import com.gaconnecte.repository.search.DossierRmqSearchRepository;
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
 * Test class for the DossierRmqResource REST controller.
 *
 * @see DossierRmqResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class DossierRmqResourceIntTest {

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Long DEFAULT_DISTANCE = 1L;
    private static final Long UPDATED_DISTANCE = 2L;

    private static final String DEFAULT_OBSERVATION = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATION = "BBBBBBBBBB";

    @Autowired
    private DossierRmqRepository dossierRmqRepository;

    @Autowired
    private DossierRmqSearchRepository dossierRmqSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDossierRmqMockMvc;

    private DossierRmq dossierRmq;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DossierRmqResource dossierRmqResource = new DossierRmqResource(dossierRmqRepository, dossierRmqSearchRepository);
        this.restDossierRmqMockMvc = MockMvcBuilders.standaloneSetup(dossierRmqResource)
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
    public static DossierRmq createEntity(EntityManager em) {
        DossierRmq dossierRmq = new DossierRmq()
            .reference(DEFAULT_REFERENCE)
            .distance(DEFAULT_DISTANCE)
            .observation(DEFAULT_OBSERVATION);
        return dossierRmq;
    }

    @Before
    public void initTest() {
        dossierRmqSearchRepository.deleteAll();
        dossierRmq = createEntity(em);
    }

    @Test
    @Transactional
    public void createDossierRmq() throws Exception {
        int databaseSizeBeforeCreate = dossierRmqRepository.findAll().size();

        // Create the DossierRmq
        restDossierRmqMockMvc.perform(post("/api/dossier-rmqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dossierRmq)))
            .andExpect(status().isCreated());

        // Validate the DossierRmq in the database
        List<DossierRmq> dossierRmqList = dossierRmqRepository.findAll();
        assertThat(dossierRmqList).hasSize(databaseSizeBeforeCreate + 1);
        DossierRmq testDossierRmq = dossierRmqList.get(dossierRmqList.size() - 1);
        assertThat(testDossierRmq.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testDossierRmq.getDistance()).isEqualTo(DEFAULT_DISTANCE);
        assertThat(testDossierRmq.getObservation()).isEqualTo(DEFAULT_OBSERVATION);

        // Validate the DossierRmq in Elasticsearch
        DossierRmq dossierRmqEs = dossierRmqSearchRepository.findOne(testDossierRmq.getId());
        assertThat(dossierRmqEs).isEqualToComparingFieldByField(testDossierRmq);
    }

    @Test
    @Transactional
    public void createDossierRmqWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dossierRmqRepository.findAll().size();

        // Create the DossierRmq with an existing ID
        dossierRmq.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDossierRmqMockMvc.perform(post("/api/dossier-rmqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dossierRmq)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DossierRmq> dossierRmqList = dossierRmqRepository.findAll();
        assertThat(dossierRmqList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDossierRmqs() throws Exception {
        // Initialize the database
        dossierRmqRepository.saveAndFlush(dossierRmq);

        // Get all the dossierRmqList
        restDossierRmqMockMvc.perform(get("/api/dossier-rmqs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dossierRmq.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION.toString())));
    }

    @Test
    @Transactional
    public void getDossierRmq() throws Exception {
        // Initialize the database
        dossierRmqRepository.saveAndFlush(dossierRmq);

        // Get the dossierRmq
        restDossierRmqMockMvc.perform(get("/api/dossier-rmqs/{id}", dossierRmq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dossierRmq.getId().intValue()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.distance").value(DEFAULT_DISTANCE.intValue()))
            .andExpect(jsonPath("$.observation").value(DEFAULT_OBSERVATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDossierRmq() throws Exception {
        // Get the dossierRmq
        restDossierRmqMockMvc.perform(get("/api/dossier-rmqs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDossierRmq() throws Exception {
        // Initialize the database
        dossierRmqRepository.saveAndFlush(dossierRmq);
        dossierRmqSearchRepository.save(dossierRmq);
        int databaseSizeBeforeUpdate = dossierRmqRepository.findAll().size();

        // Update the dossierRmq
        DossierRmq updatedDossierRmq = dossierRmqRepository.findOne(dossierRmq.getId());
        updatedDossierRmq
            .reference(UPDATED_REFERENCE)
            .distance(UPDATED_DISTANCE)
            .observation(UPDATED_OBSERVATION);

        restDossierRmqMockMvc.perform(put("/api/dossier-rmqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDossierRmq)))
            .andExpect(status().isOk());

        // Validate the DossierRmq in the database
        List<DossierRmq> dossierRmqList = dossierRmqRepository.findAll();
        assertThat(dossierRmqList).hasSize(databaseSizeBeforeUpdate);
        DossierRmq testDossierRmq = dossierRmqList.get(dossierRmqList.size() - 1);
        assertThat(testDossierRmq.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testDossierRmq.getDistance()).isEqualTo(UPDATED_DISTANCE);
        assertThat(testDossierRmq.getObservation()).isEqualTo(UPDATED_OBSERVATION);

        // Validate the DossierRmq in Elasticsearch
        DossierRmq dossierRmqEs = dossierRmqSearchRepository.findOne(testDossierRmq.getId());
        assertThat(dossierRmqEs).isEqualToComparingFieldByField(testDossierRmq);
    }

    @Test
    @Transactional
    public void updateNonExistingDossierRmq() throws Exception {
        int databaseSizeBeforeUpdate = dossierRmqRepository.findAll().size();

        // Create the DossierRmq

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDossierRmqMockMvc.perform(put("/api/dossier-rmqs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dossierRmq)))
            .andExpect(status().isCreated());

        // Validate the DossierRmq in the database
        List<DossierRmq> dossierRmqList = dossierRmqRepository.findAll();
        assertThat(dossierRmqList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDossierRmq() throws Exception {
        // Initialize the database
        dossierRmqRepository.saveAndFlush(dossierRmq);
        dossierRmqSearchRepository.save(dossierRmq);
        int databaseSizeBeforeDelete = dossierRmqRepository.findAll().size();

        // Get the dossierRmq
        restDossierRmqMockMvc.perform(delete("/api/dossier-rmqs/{id}", dossierRmq.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean dossierRmqExistsInEs = dossierRmqSearchRepository.exists(dossierRmq.getId());
        assertThat(dossierRmqExistsInEs).isFalse();

        // Validate the database is empty
        List<DossierRmq> dossierRmqList = dossierRmqRepository.findAll();
        assertThat(dossierRmqList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchDossierRmq() throws Exception {
        // Initialize the database
        dossierRmqRepository.saveAndFlush(dossierRmq);
        dossierRmqSearchRepository.save(dossierRmq);

        // Search the dossierRmq
        restDossierRmqMockMvc.perform(get("/api/_search/dossier-rmqs?query=id:" + dossierRmq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dossierRmq.getId().intValue())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].distance").value(hasItem(DEFAULT_DISTANCE.intValue())))
            .andExpect(jsonPath("$.[*].observation").value(hasItem(DEFAULT_OBSERVATION.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DossierRmq.class);
        DossierRmq dossierRmq1 = new DossierRmq();
        dossierRmq1.setId(1L);
        DossierRmq dossierRmq2 = new DossierRmq();
        dossierRmq2.setId(dossierRmq1.getId());
        assertThat(dossierRmq1).isEqualTo(dossierRmq2);
        dossierRmq2.setId(2L);
        assertThat(dossierRmq1).isNotEqualTo(dossierRmq2);
        dossierRmq1.setId(null);
        assertThat(dossierRmq1).isNotEqualTo(dossierRmq2);
    }
}
