package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.SysZoneGeographique;
import com.gaconnecte.repository.SysZoneGeographiqueRepository;
import com.gaconnecte.repository.search.SysZoneGeographiqueSearchRepository;
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
 * Test class for the SysZoneGeographiqueResource REST controller.
 *
 * @see SysZoneGeographiqueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class SysZoneGeographiqueResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private SysZoneGeographiqueRepository sysZoneGeographiqueRepository;

    @Autowired
    private SysZoneGeographiqueSearchRepository sysZoneGeographiqueSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSysZoneGeographiqueMockMvc;

    private SysZoneGeographique sysZoneGeographique;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SysZoneGeographiqueResource sysZoneGeographiqueResource = new SysZoneGeographiqueResource(sysZoneGeographiqueRepository, sysZoneGeographiqueSearchRepository);
        this.restSysZoneGeographiqueMockMvc = MockMvcBuilders.standaloneSetup(sysZoneGeographiqueResource)
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
    public static SysZoneGeographique createEntity(EntityManager em) {
        SysZoneGeographique sysZoneGeographique = new SysZoneGeographique()
            .nom(DEFAULT_NOM);
        return sysZoneGeographique;
    }

    @Before
    public void initTest() {
        sysZoneGeographiqueSearchRepository.deleteAll();
        sysZoneGeographique = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysZoneGeographique() throws Exception {
        int databaseSizeBeforeCreate = sysZoneGeographiqueRepository.findAll().size();

        // Create the SysZoneGeographique
        restSysZoneGeographiqueMockMvc.perform(post("/api/sys-zone-geographiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysZoneGeographique)))
            .andExpect(status().isCreated());

        // Validate the SysZoneGeographique in the database
        List<SysZoneGeographique> sysZoneGeographiqueList = sysZoneGeographiqueRepository.findAll();
        assertThat(sysZoneGeographiqueList).hasSize(databaseSizeBeforeCreate + 1);
        SysZoneGeographique testSysZoneGeographique = sysZoneGeographiqueList.get(sysZoneGeographiqueList.size() - 1);
        assertThat(testSysZoneGeographique.getNom()).isEqualTo(DEFAULT_NOM);

        // Validate the SysZoneGeographique in Elasticsearch
        SysZoneGeographique sysZoneGeographiqueEs = sysZoneGeographiqueSearchRepository.findOne(testSysZoneGeographique.getId());
        assertThat(sysZoneGeographiqueEs).isEqualToComparingFieldByField(testSysZoneGeographique);
    }

    @Test
    @Transactional
    public void createSysZoneGeographiqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysZoneGeographiqueRepository.findAll().size();

        // Create the SysZoneGeographique with an existing ID
        sysZoneGeographique.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysZoneGeographiqueMockMvc.perform(post("/api/sys-zone-geographiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysZoneGeographique)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<SysZoneGeographique> sysZoneGeographiqueList = sysZoneGeographiqueRepository.findAll();
        assertThat(sysZoneGeographiqueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysZoneGeographiqueRepository.findAll().size();
        // set the field null
        sysZoneGeographique.setNom(null);

        // Create the SysZoneGeographique, which fails.

        restSysZoneGeographiqueMockMvc.perform(post("/api/sys-zone-geographiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysZoneGeographique)))
            .andExpect(status().isBadRequest());

        List<SysZoneGeographique> sysZoneGeographiqueList = sysZoneGeographiqueRepository.findAll();
        assertThat(sysZoneGeographiqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysZoneGeographiques() throws Exception {
        // Initialize the database
        sysZoneGeographiqueRepository.saveAndFlush(sysZoneGeographique);

        // Get all the sysZoneGeographiqueList
        restSysZoneGeographiqueMockMvc.perform(get("/api/sys-zone-geographiques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysZoneGeographique.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void getSysZoneGeographique() throws Exception {
        // Initialize the database
        sysZoneGeographiqueRepository.saveAndFlush(sysZoneGeographique);

        // Get the sysZoneGeographique
        restSysZoneGeographiqueMockMvc.perform(get("/api/sys-zone-geographiques/{id}", sysZoneGeographique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysZoneGeographique.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSysZoneGeographique() throws Exception {
        // Get the sysZoneGeographique
        restSysZoneGeographiqueMockMvc.perform(get("/api/sys-zone-geographiques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysZoneGeographique() throws Exception {
        // Initialize the database
        sysZoneGeographiqueRepository.saveAndFlush(sysZoneGeographique);
        sysZoneGeographiqueSearchRepository.save(sysZoneGeographique);
        int databaseSizeBeforeUpdate = sysZoneGeographiqueRepository.findAll().size();

        // Update the sysZoneGeographique
        SysZoneGeographique updatedSysZoneGeographique = sysZoneGeographiqueRepository.findOne(sysZoneGeographique.getId());
        updatedSysZoneGeographique
            .nom(UPDATED_NOM);

        restSysZoneGeographiqueMockMvc.perform(put("/api/sys-zone-geographiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysZoneGeographique)))
            .andExpect(status().isOk());

        // Validate the SysZoneGeographique in the database
        List<SysZoneGeographique> sysZoneGeographiqueList = sysZoneGeographiqueRepository.findAll();
        assertThat(sysZoneGeographiqueList).hasSize(databaseSizeBeforeUpdate);
        SysZoneGeographique testSysZoneGeographique = sysZoneGeographiqueList.get(sysZoneGeographiqueList.size() - 1);
        assertThat(testSysZoneGeographique.getNom()).isEqualTo(UPDATED_NOM);

        // Validate the SysZoneGeographique in Elasticsearch
        SysZoneGeographique sysZoneGeographiqueEs = sysZoneGeographiqueSearchRepository.findOne(testSysZoneGeographique.getId());
        assertThat(sysZoneGeographiqueEs).isEqualToComparingFieldByField(testSysZoneGeographique);
    }

    @Test
    @Transactional
    public void updateNonExistingSysZoneGeographique() throws Exception {
        int databaseSizeBeforeUpdate = sysZoneGeographiqueRepository.findAll().size();

        // Create the SysZoneGeographique

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSysZoneGeographiqueMockMvc.perform(put("/api/sys-zone-geographiques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysZoneGeographique)))
            .andExpect(status().isCreated());

        // Validate the SysZoneGeographique in the database
        List<SysZoneGeographique> sysZoneGeographiqueList = sysZoneGeographiqueRepository.findAll();
        assertThat(sysZoneGeographiqueList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSysZoneGeographique() throws Exception {
        // Initialize the database
        sysZoneGeographiqueRepository.saveAndFlush(sysZoneGeographique);
        sysZoneGeographiqueSearchRepository.save(sysZoneGeographique);
        int databaseSizeBeforeDelete = sysZoneGeographiqueRepository.findAll().size();

        // Get the sysZoneGeographique
        restSysZoneGeographiqueMockMvc.perform(delete("/api/sys-zone-geographiques/{id}", sysZoneGeographique.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean sysZoneGeographiqueExistsInEs = sysZoneGeographiqueSearchRepository.exists(sysZoneGeographique.getId());
        assertThat(sysZoneGeographiqueExistsInEs).isFalse();

        // Validate the database is empty
        List<SysZoneGeographique> sysZoneGeographiqueList = sysZoneGeographiqueRepository.findAll();
        assertThat(sysZoneGeographiqueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSysZoneGeographique() throws Exception {
        // Initialize the database
        sysZoneGeographiqueRepository.saveAndFlush(sysZoneGeographique);
        sysZoneGeographiqueSearchRepository.save(sysZoneGeographique);

        // Search the sysZoneGeographique
        restSysZoneGeographiqueMockMvc.perform(get("/api/_search/sys-zone-geographiques?query=id:" + sysZoneGeographique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysZoneGeographique.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysZoneGeographique.class);
        SysZoneGeographique sysZoneGeographique1 = new SysZoneGeographique();
        sysZoneGeographique1.setId(1L);
        SysZoneGeographique sysZoneGeographique2 = new SysZoneGeographique();
        sysZoneGeographique2.setId(sysZoneGeographique1.getId());
        assertThat(sysZoneGeographique1).isEqualTo(sysZoneGeographique2);
        sysZoneGeographique2.setId(2L);
        assertThat(sysZoneGeographique1).isNotEqualTo(sysZoneGeographique2);
        sysZoneGeographique1.setId(null);
        assertThat(sysZoneGeographique1).isNotEqualTo(sysZoneGeographique2);
    }
}
