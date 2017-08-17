package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.SysVille;
import com.gaconnecte.domain.SysGouvernorat;
import com.gaconnecte.repository.SysVilleRepository;
import com.gaconnecte.repository.search.SysVilleSearchRepository;
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
 * Test class for the SysVilleResource REST controller.
 *
 * @see SysVilleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class SysVilleResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private SysVilleRepository sysVilleRepository;

    @Autowired
    private SysVilleSearchRepository sysVilleSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSysVilleMockMvc;

    private SysVille sysVille;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SysVilleResource sysVilleResource = new SysVilleResource(sysVilleRepository, sysVilleSearchRepository);
        this.restSysVilleMockMvc = MockMvcBuilders.standaloneSetup(sysVilleResource)
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
    public static SysVille createEntity(EntityManager em) {
        SysVille sysVille = new SysVille()
            .nom(DEFAULT_NOM);
        // Add required entity
        SysGouvernorat gouvernorat = SysGouvernoratResourceIntTest.createEntity(em);
        em.persist(gouvernorat);
        em.flush();
        sysVille.setGouvernorat(gouvernorat);
        return sysVille;
    }

    @Before
    public void initTest() {
        sysVilleSearchRepository.deleteAll();
        sysVille = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysVille() throws Exception {
        int databaseSizeBeforeCreate = sysVilleRepository.findAll().size();

        // Create the SysVille
        restSysVilleMockMvc.perform(post("/api/sys-villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysVille)))
            .andExpect(status().isCreated());

        // Validate the SysVille in the database
        List<SysVille> sysVilleList = sysVilleRepository.findAll();
        assertThat(sysVilleList).hasSize(databaseSizeBeforeCreate + 1);
        SysVille testSysVille = sysVilleList.get(sysVilleList.size() - 1);
        assertThat(testSysVille.getNom()).isEqualTo(DEFAULT_NOM);

        // Validate the SysVille in Elasticsearch
        SysVille sysVilleEs = sysVilleSearchRepository.findOne(testSysVille.getId());
        assertThat(sysVilleEs).isEqualToComparingFieldByField(testSysVille);
    }

    @Test
    @Transactional
    public void createSysVilleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysVilleRepository.findAll().size();

        // Create the SysVille with an existing ID
        sysVille.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysVilleMockMvc.perform(post("/api/sys-villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysVille)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<SysVille> sysVilleList = sysVilleRepository.findAll();
        assertThat(sysVilleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysVilleRepository.findAll().size();
        // set the field null
        sysVille.setNom(null);

        // Create the SysVille, which fails.

        restSysVilleMockMvc.perform(post("/api/sys-villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysVille)))
            .andExpect(status().isBadRequest());

        List<SysVille> sysVilleList = sysVilleRepository.findAll();
        assertThat(sysVilleList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysVilles() throws Exception {
        // Initialize the database
        sysVilleRepository.saveAndFlush(sysVille);

        // Get all the sysVilleList
        restSysVilleMockMvc.perform(get("/api/sys-villes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysVille.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void getSysVille() throws Exception {
        // Initialize the database
        sysVilleRepository.saveAndFlush(sysVille);

        // Get the sysVille
        restSysVilleMockMvc.perform(get("/api/sys-villes/{id}", sysVille.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysVille.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSysVille() throws Exception {
        // Get the sysVille
        restSysVilleMockMvc.perform(get("/api/sys-villes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysVille() throws Exception {
        // Initialize the database
        sysVilleRepository.saveAndFlush(sysVille);
        sysVilleSearchRepository.save(sysVille);
        int databaseSizeBeforeUpdate = sysVilleRepository.findAll().size();

        // Update the sysVille
        SysVille updatedSysVille = sysVilleRepository.findOne(sysVille.getId());
        updatedSysVille
            .nom(UPDATED_NOM);

        restSysVilleMockMvc.perform(put("/api/sys-villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysVille)))
            .andExpect(status().isOk());

        // Validate the SysVille in the database
        List<SysVille> sysVilleList = sysVilleRepository.findAll();
        assertThat(sysVilleList).hasSize(databaseSizeBeforeUpdate);
        SysVille testSysVille = sysVilleList.get(sysVilleList.size() - 1);
        assertThat(testSysVille.getNom()).isEqualTo(UPDATED_NOM);

        // Validate the SysVille in Elasticsearch
        SysVille sysVilleEs = sysVilleSearchRepository.findOne(testSysVille.getId());
        assertThat(sysVilleEs).isEqualToComparingFieldByField(testSysVille);
    }

    @Test
    @Transactional
    public void updateNonExistingSysVille() throws Exception {
        int databaseSizeBeforeUpdate = sysVilleRepository.findAll().size();

        // Create the SysVille

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSysVilleMockMvc.perform(put("/api/sys-villes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysVille)))
            .andExpect(status().isCreated());

        // Validate the SysVille in the database
        List<SysVille> sysVilleList = sysVilleRepository.findAll();
        assertThat(sysVilleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSysVille() throws Exception {
        // Initialize the database
        sysVilleRepository.saveAndFlush(sysVille);
        sysVilleSearchRepository.save(sysVille);
        int databaseSizeBeforeDelete = sysVilleRepository.findAll().size();

        // Get the sysVille
        restSysVilleMockMvc.perform(delete("/api/sys-villes/{id}", sysVille.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean sysVilleExistsInEs = sysVilleSearchRepository.exists(sysVille.getId());
        assertThat(sysVilleExistsInEs).isFalse();

        // Validate the database is empty
        List<SysVille> sysVilleList = sysVilleRepository.findAll();
        assertThat(sysVilleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSysVille() throws Exception {
        // Initialize the database
        sysVilleRepository.saveAndFlush(sysVille);
        sysVilleSearchRepository.save(sysVille);

        // Search the sysVille
        restSysVilleMockMvc.perform(get("/api/_search/sys-villes?query=id:" + sysVille.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysVille.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysVille.class);
        SysVille sysVille1 = new SysVille();
        sysVille1.setId(1L);
        SysVille sysVille2 = new SysVille();
        sysVille2.setId(sysVille1.getId());
        assertThat(sysVille1).isEqualTo(sysVille2);
        sysVille2.setId(2L);
        assertThat(sysVille1).isNotEqualTo(sysVille2);
        sysVille1.setId(null);
        assertThat(sysVille1).isNotEqualTo(sysVille2);
    }
}
