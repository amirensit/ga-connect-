package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.RefTypeService;
import com.gaconnecte.repository.RefTypeServiceRepository;
import com.gaconnecte.repository.search.RefTypeServiceSearchRepository;
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
 * Test class for the RefTypeServiceResource REST controller.
 *
 * @see RefTypeServiceResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class RefTypeServiceResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    @Autowired
    private RefTypeServiceRepository refTypeServiceRepository;

    @Autowired
    private RefTypeServiceSearchRepository refTypeServiceSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefTypeServiceMockMvc;

    private RefTypeService refTypeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RefTypeServiceResource refTypeServiceResource = new RefTypeServiceResource(refTypeServiceRepository, refTypeServiceSearchRepository);
        this.restRefTypeServiceMockMvc = MockMvcBuilders.standaloneSetup(refTypeServiceResource)
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
    public static RefTypeService createEntity(EntityManager em) {
        RefTypeService refTypeService = new RefTypeService()
            .nom(DEFAULT_NOM);
        return refTypeService;
    }

    @Before
    public void initTest() {
        refTypeServiceSearchRepository.deleteAll();
        refTypeService = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefTypeService() throws Exception {
        int databaseSizeBeforeCreate = refTypeServiceRepository.findAll().size();

        // Create the RefTypeService
        restRefTypeServiceMockMvc.perform(post("/api/ref-type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeService)))
            .andExpect(status().isCreated());

        // Validate the RefTypeService in the database
        List<RefTypeService> refTypeServiceList = refTypeServiceRepository.findAll();
        assertThat(refTypeServiceList).hasSize(databaseSizeBeforeCreate + 1);
        RefTypeService testRefTypeService = refTypeServiceList.get(refTypeServiceList.size() - 1);
        assertThat(testRefTypeService.getNom()).isEqualTo(DEFAULT_NOM);

        // Validate the RefTypeService in Elasticsearch
        RefTypeService refTypeServiceEs = refTypeServiceSearchRepository.findOne(testRefTypeService.getId());
        assertThat(refTypeServiceEs).isEqualToComparingFieldByField(testRefTypeService);
    }

    @Test
    @Transactional
    public void createRefTypeServiceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refTypeServiceRepository.findAll().size();

        // Create the RefTypeService with an existing ID
        refTypeService.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefTypeServiceMockMvc.perform(post("/api/ref-type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeService)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RefTypeService> refTypeServiceList = refTypeServiceRepository.findAll();
        assertThat(refTypeServiceList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = refTypeServiceRepository.findAll().size();
        // set the field null
        refTypeService.setNom(null);

        // Create the RefTypeService, which fails.

        restRefTypeServiceMockMvc.perform(post("/api/ref-type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeService)))
            .andExpect(status().isBadRequest());

        List<RefTypeService> refTypeServiceList = refTypeServiceRepository.findAll();
        assertThat(refTypeServiceList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefTypeServices() throws Exception {
        // Initialize the database
        refTypeServiceRepository.saveAndFlush(refTypeService);

        // Get all the refTypeServiceList
        restRefTypeServiceMockMvc.perform(get("/api/ref-type-services?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refTypeService.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void getRefTypeService() throws Exception {
        // Initialize the database
        refTypeServiceRepository.saveAndFlush(refTypeService);

        // Get the refTypeService
        restRefTypeServiceMockMvc.perform(get("/api/ref-type-services/{id}", refTypeService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refTypeService.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefTypeService() throws Exception {
        // Get the refTypeService
        restRefTypeServiceMockMvc.perform(get("/api/ref-type-services/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefTypeService() throws Exception {
        // Initialize the database
        refTypeServiceRepository.saveAndFlush(refTypeService);
        refTypeServiceSearchRepository.save(refTypeService);
        int databaseSizeBeforeUpdate = refTypeServiceRepository.findAll().size();

        // Update the refTypeService
        RefTypeService updatedRefTypeService = refTypeServiceRepository.findOne(refTypeService.getId());
        updatedRefTypeService
            .nom(UPDATED_NOM);

        restRefTypeServiceMockMvc.perform(put("/api/ref-type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefTypeService)))
            .andExpect(status().isOk());

        // Validate the RefTypeService in the database
        List<RefTypeService> refTypeServiceList = refTypeServiceRepository.findAll();
        assertThat(refTypeServiceList).hasSize(databaseSizeBeforeUpdate);
        RefTypeService testRefTypeService = refTypeServiceList.get(refTypeServiceList.size() - 1);
        assertThat(testRefTypeService.getNom()).isEqualTo(UPDATED_NOM);

        // Validate the RefTypeService in Elasticsearch
        RefTypeService refTypeServiceEs = refTypeServiceSearchRepository.findOne(testRefTypeService.getId());
        assertThat(refTypeServiceEs).isEqualToComparingFieldByField(testRefTypeService);
    }

    @Test
    @Transactional
    public void updateNonExistingRefTypeService() throws Exception {
        int databaseSizeBeforeUpdate = refTypeServiceRepository.findAll().size();

        // Create the RefTypeService

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRefTypeServiceMockMvc.perform(put("/api/ref-type-services")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refTypeService)))
            .andExpect(status().isCreated());

        // Validate the RefTypeService in the database
        List<RefTypeService> refTypeServiceList = refTypeServiceRepository.findAll();
        assertThat(refTypeServiceList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRefTypeService() throws Exception {
        // Initialize the database
        refTypeServiceRepository.saveAndFlush(refTypeService);
        refTypeServiceSearchRepository.save(refTypeService);
        int databaseSizeBeforeDelete = refTypeServiceRepository.findAll().size();

        // Get the refTypeService
        restRefTypeServiceMockMvc.perform(delete("/api/ref-type-services/{id}", refTypeService.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean refTypeServiceExistsInEs = refTypeServiceSearchRepository.exists(refTypeService.getId());
        assertThat(refTypeServiceExistsInEs).isFalse();

        // Validate the database is empty
        List<RefTypeService> refTypeServiceList = refTypeServiceRepository.findAll();
        assertThat(refTypeServiceList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchRefTypeService() throws Exception {
        // Initialize the database
        refTypeServiceRepository.saveAndFlush(refTypeService);
        refTypeServiceSearchRepository.save(refTypeService);

        // Search the refTypeService
        restRefTypeServiceMockMvc.perform(get("/api/_search/ref-type-services?query=id:" + refTypeService.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refTypeService.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefTypeService.class);
        RefTypeService refTypeService1 = new RefTypeService();
        refTypeService1.setId(1L);
        RefTypeService refTypeService2 = new RefTypeService();
        refTypeService2.setId(refTypeService1.getId());
        assertThat(refTypeService1).isEqualTo(refTypeService2);
        refTypeService2.setId(2L);
        assertThat(refTypeService1).isNotEqualTo(refTypeService2);
        refTypeService1.setId(null);
        assertThat(refTypeService1).isNotEqualTo(refTypeService2);
    }
}
