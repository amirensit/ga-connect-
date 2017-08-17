package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.SysGouvernorat;
import com.gaconnecte.repository.SysGouvernoratRepository;
import com.gaconnecte.repository.search.SysGouvernoratSearchRepository;
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
 * Test class for the SysGouvernoratResource REST controller.
 *
 * @see SysGouvernoratResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class SysGouvernoratResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private SysGouvernoratRepository sysGouvernoratRepository;

    @Autowired
    private SysGouvernoratSearchRepository sysGouvernoratSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSysGouvernoratMockMvc;

    private SysGouvernorat sysGouvernorat;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SysGouvernoratResource sysGouvernoratResource = new SysGouvernoratResource(sysGouvernoratRepository, sysGouvernoratSearchRepository);
        this.restSysGouvernoratMockMvc = MockMvcBuilders.standaloneSetup(sysGouvernoratResource)
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
    public static SysGouvernorat createEntity(EntityManager em) {
        SysGouvernorat sysGouvernorat = new SysGouvernorat()
            .nom(DEFAULT_NOM);
        return sysGouvernorat;
    }

    @Before
    public void initTest() {
        sysGouvernoratSearchRepository.deleteAll();
        sysGouvernorat = createEntity(em);
    }

    @Test
    @Transactional
    public void createSysGouvernorat() throws Exception {
        int databaseSizeBeforeCreate = sysGouvernoratRepository.findAll().size();

        // Create the SysGouvernorat
        restSysGouvernoratMockMvc.perform(post("/api/sys-gouvernorats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGouvernorat)))
            .andExpect(status().isCreated());

        // Validate the SysGouvernorat in the database
        List<SysGouvernorat> sysGouvernoratList = sysGouvernoratRepository.findAll();
        assertThat(sysGouvernoratList).hasSize(databaseSizeBeforeCreate + 1);
        SysGouvernorat testSysGouvernorat = sysGouvernoratList.get(sysGouvernoratList.size() - 1);
        assertThat(testSysGouvernorat.getNom()).isEqualTo(DEFAULT_NOM);

        // Validate the SysGouvernorat in Elasticsearch
        SysGouvernorat sysGouvernoratEs = sysGouvernoratSearchRepository.findOne(testSysGouvernorat.getId());
        assertThat(sysGouvernoratEs).isEqualToComparingFieldByField(testSysGouvernorat);
    }

    @Test
    @Transactional
    public void createSysGouvernoratWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sysGouvernoratRepository.findAll().size();

        // Create the SysGouvernorat with an existing ID
        sysGouvernorat.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSysGouvernoratMockMvc.perform(post("/api/sys-gouvernorats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGouvernorat)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<SysGouvernorat> sysGouvernoratList = sysGouvernoratRepository.findAll();
        assertThat(sysGouvernoratList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = sysGouvernoratRepository.findAll().size();
        // set the field null
        sysGouvernorat.setNom(null);

        // Create the SysGouvernorat, which fails.

        restSysGouvernoratMockMvc.perform(post("/api/sys-gouvernorats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGouvernorat)))
            .andExpect(status().isBadRequest());

        List<SysGouvernorat> sysGouvernoratList = sysGouvernoratRepository.findAll();
        assertThat(sysGouvernoratList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSysGouvernorats() throws Exception {
        // Initialize the database
        sysGouvernoratRepository.saveAndFlush(sysGouvernorat);

        // Get all the sysGouvernoratList
        restSysGouvernoratMockMvc.perform(get("/api/sys-gouvernorats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysGouvernorat.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void getSysGouvernorat() throws Exception {
        // Initialize the database
        sysGouvernoratRepository.saveAndFlush(sysGouvernorat);

        // Get the sysGouvernorat
        restSysGouvernoratMockMvc.perform(get("/api/sys-gouvernorats/{id}", sysGouvernorat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sysGouvernorat.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSysGouvernorat() throws Exception {
        // Get the sysGouvernorat
        restSysGouvernoratMockMvc.perform(get("/api/sys-gouvernorats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSysGouvernorat() throws Exception {
        // Initialize the database
        sysGouvernoratRepository.saveAndFlush(sysGouvernorat);
        sysGouvernoratSearchRepository.save(sysGouvernorat);
        int databaseSizeBeforeUpdate = sysGouvernoratRepository.findAll().size();

        // Update the sysGouvernorat
        SysGouvernorat updatedSysGouvernorat = sysGouvernoratRepository.findOne(sysGouvernorat.getId());
        updatedSysGouvernorat
            .nom(UPDATED_NOM);

        restSysGouvernoratMockMvc.perform(put("/api/sys-gouvernorats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSysGouvernorat)))
            .andExpect(status().isOk());

        // Validate the SysGouvernorat in the database
        List<SysGouvernorat> sysGouvernoratList = sysGouvernoratRepository.findAll();
        assertThat(sysGouvernoratList).hasSize(databaseSizeBeforeUpdate);
        SysGouvernorat testSysGouvernorat = sysGouvernoratList.get(sysGouvernoratList.size() - 1);
        assertThat(testSysGouvernorat.getNom()).isEqualTo(UPDATED_NOM);

        // Validate the SysGouvernorat in Elasticsearch
        SysGouvernorat sysGouvernoratEs = sysGouvernoratSearchRepository.findOne(testSysGouvernorat.getId());
        assertThat(sysGouvernoratEs).isEqualToComparingFieldByField(testSysGouvernorat);
    }

    @Test
    @Transactional
    public void updateNonExistingSysGouvernorat() throws Exception {
        int databaseSizeBeforeUpdate = sysGouvernoratRepository.findAll().size();

        // Create the SysGouvernorat

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSysGouvernoratMockMvc.perform(put("/api/sys-gouvernorats")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sysGouvernorat)))
            .andExpect(status().isCreated());

        // Validate the SysGouvernorat in the database
        List<SysGouvernorat> sysGouvernoratList = sysGouvernoratRepository.findAll();
        assertThat(sysGouvernoratList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSysGouvernorat() throws Exception {
        // Initialize the database
        sysGouvernoratRepository.saveAndFlush(sysGouvernorat);
        sysGouvernoratSearchRepository.save(sysGouvernorat);
        int databaseSizeBeforeDelete = sysGouvernoratRepository.findAll().size();

        // Get the sysGouvernorat
        restSysGouvernoratMockMvc.perform(delete("/api/sys-gouvernorats/{id}", sysGouvernorat.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean sysGouvernoratExistsInEs = sysGouvernoratSearchRepository.exists(sysGouvernorat.getId());
        assertThat(sysGouvernoratExistsInEs).isFalse();

        // Validate the database is empty
        List<SysGouvernorat> sysGouvernoratList = sysGouvernoratRepository.findAll();
        assertThat(sysGouvernoratList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSysGouvernorat() throws Exception {
        // Initialize the database
        sysGouvernoratRepository.saveAndFlush(sysGouvernorat);
        sysGouvernoratSearchRepository.save(sysGouvernorat);

        // Search the sysGouvernorat
        restSysGouvernoratMockMvc.perform(get("/api/_search/sys-gouvernorats?query=id:" + sysGouvernorat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sysGouvernorat.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SysGouvernorat.class);
        SysGouvernorat sysGouvernorat1 = new SysGouvernorat();
        sysGouvernorat1.setId(1L);
        SysGouvernorat sysGouvernorat2 = new SysGouvernorat();
        sysGouvernorat2.setId(sysGouvernorat1.getId());
        assertThat(sysGouvernorat1).isEqualTo(sysGouvernorat2);
        sysGouvernorat2.setId(2L);
        assertThat(sysGouvernorat1).isNotEqualTo(sysGouvernorat2);
        sysGouvernorat1.setId(null);
        assertThat(sysGouvernorat1).isNotEqualTo(sysGouvernorat2);
    }
}
