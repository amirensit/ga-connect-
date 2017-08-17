package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.RefCompagnie;
import com.gaconnecte.repository.RefCompagnieRepository;
import com.gaconnecte.repository.search.RefCompagnieSearchRepository;
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
 * Test class for the RefCompagnieResource REST controller.
 *
 * @see RefCompagnieResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class RefCompagnieResourceIntTest {

    private static final Long DEFAULT_NUMERO = 1L;
    private static final Long UPDATED_NUMERO = 2L;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_BLOQUE = false;
    private static final Boolean UPDATED_IS_BLOQUE = true;

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    @Autowired
    private RefCompagnieRepository refCompagnieRepository;

    @Autowired
    private RefCompagnieSearchRepository refCompagnieSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefCompagnieMockMvc;

    private RefCompagnie refCompagnie;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RefCompagnieResource refCompagnieResource = new RefCompagnieResource(refCompagnieRepository, refCompagnieSearchRepository);
        this.restRefCompagnieMockMvc = MockMvcBuilders.standaloneSetup(refCompagnieResource)
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
    public static RefCompagnie createEntity(EntityManager em) {
        RefCompagnie refCompagnie = new RefCompagnie()
            .numero(DEFAULT_NUMERO)
            .nom(DEFAULT_NOM)
            .isBloque(DEFAULT_IS_BLOQUE)
            .adresse(DEFAULT_ADRESSE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return refCompagnie;
    }

    @Before
    public void initTest() {
        refCompagnieSearchRepository.deleteAll();
        refCompagnie = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefCompagnie() throws Exception {
        int databaseSizeBeforeCreate = refCompagnieRepository.findAll().size();

        // Create the RefCompagnie
        restRefCompagnieMockMvc.perform(post("/api/ref-compagnies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCompagnie)))
            .andExpect(status().isCreated());

        // Validate the RefCompagnie in the database
        List<RefCompagnie> refCompagnieList = refCompagnieRepository.findAll();
        assertThat(refCompagnieList).hasSize(databaseSizeBeforeCreate + 1);
        RefCompagnie testRefCompagnie = refCompagnieList.get(refCompagnieList.size() - 1);
        assertThat(testRefCompagnie.getNumero()).isEqualTo(DEFAULT_NUMERO);
        assertThat(testRefCompagnie.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testRefCompagnie.isIsBloque()).isEqualTo(DEFAULT_IS_BLOQUE);
        assertThat(testRefCompagnie.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testRefCompagnie.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testRefCompagnie.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);

        // Validate the RefCompagnie in Elasticsearch
        RefCompagnie refCompagnieEs = refCompagnieSearchRepository.findOne(testRefCompagnie.getId());
        assertThat(refCompagnieEs).isEqualToComparingFieldByField(testRefCompagnie);
    }

    @Test
    @Transactional
    public void createRefCompagnieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refCompagnieRepository.findAll().size();

        // Create the RefCompagnie with an existing ID
        refCompagnie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefCompagnieMockMvc.perform(post("/api/ref-compagnies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCompagnie)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RefCompagnie> refCompagnieList = refCompagnieRepository.findAll();
        assertThat(refCompagnieList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNumeroIsRequired() throws Exception {
        int databaseSizeBeforeTest = refCompagnieRepository.findAll().size();
        // set the field null
        refCompagnie.setNumero(null);

        // Create the RefCompagnie, which fails.

        restRefCompagnieMockMvc.perform(post("/api/ref-compagnies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCompagnie)))
            .andExpect(status().isBadRequest());

        List<RefCompagnie> refCompagnieList = refCompagnieRepository.findAll();
        assertThat(refCompagnieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = refCompagnieRepository.findAll().size();
        // set the field null
        refCompagnie.setNom(null);

        // Create the RefCompagnie, which fails.

        restRefCompagnieMockMvc.perform(post("/api/ref-compagnies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCompagnie)))
            .andExpect(status().isBadRequest());

        List<RefCompagnie> refCompagnieList = refCompagnieRepository.findAll();
        assertThat(refCompagnieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = refCompagnieRepository.findAll().size();
        // set the field null
        refCompagnie.setAdresse(null);

        // Create the RefCompagnie, which fails.

        restRefCompagnieMockMvc.perform(post("/api/ref-compagnies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCompagnie)))
            .andExpect(status().isBadRequest());

        List<RefCompagnie> refCompagnieList = refCompagnieRepository.findAll();
        assertThat(refCompagnieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefCompagnies() throws Exception {
        // Initialize the database
        refCompagnieRepository.saveAndFlush(refCompagnie);

        // Get all the refCompagnieList
        restRefCompagnieMockMvc.perform(get("/api/ref-compagnies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refCompagnie.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].isBloque").value(hasItem(DEFAULT_IS_BLOQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.toString())));
    }

    @Test
    @Transactional
    public void getRefCompagnie() throws Exception {
        // Initialize the database
        refCompagnieRepository.saveAndFlush(refCompagnie);

        // Get the refCompagnie
        restRefCompagnieMockMvc.perform(get("/api/ref-compagnies/{id}", refCompagnie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refCompagnie.getId().intValue()))
            .andExpect(jsonPath("$.numero").value(DEFAULT_NUMERO.intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.isBloque").value(DEFAULT_IS_BLOQUE.booleanValue()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.toString()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefCompagnie() throws Exception {
        // Get the refCompagnie
        restRefCompagnieMockMvc.perform(get("/api/ref-compagnies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefCompagnie() throws Exception {
        // Initialize the database
        refCompagnieRepository.saveAndFlush(refCompagnie);
        refCompagnieSearchRepository.save(refCompagnie);
        int databaseSizeBeforeUpdate = refCompagnieRepository.findAll().size();

        // Update the refCompagnie
        RefCompagnie updatedRefCompagnie = refCompagnieRepository.findOne(refCompagnie.getId());
        updatedRefCompagnie
            .numero(UPDATED_NUMERO)
            .nom(UPDATED_NOM)
            .isBloque(UPDATED_IS_BLOQUE)
            .adresse(UPDATED_ADRESSE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);

        restRefCompagnieMockMvc.perform(put("/api/ref-compagnies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefCompagnie)))
            .andExpect(status().isOk());

        // Validate the RefCompagnie in the database
        List<RefCompagnie> refCompagnieList = refCompagnieRepository.findAll();
        assertThat(refCompagnieList).hasSize(databaseSizeBeforeUpdate);
        RefCompagnie testRefCompagnie = refCompagnieList.get(refCompagnieList.size() - 1);
        assertThat(testRefCompagnie.getNumero()).isEqualTo(UPDATED_NUMERO);
        assertThat(testRefCompagnie.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testRefCompagnie.isIsBloque()).isEqualTo(UPDATED_IS_BLOQUE);
        assertThat(testRefCompagnie.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testRefCompagnie.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testRefCompagnie.getLongitude()).isEqualTo(UPDATED_LONGITUDE);

        // Validate the RefCompagnie in Elasticsearch
        RefCompagnie refCompagnieEs = refCompagnieSearchRepository.findOne(testRefCompagnie.getId());
        assertThat(refCompagnieEs).isEqualToComparingFieldByField(testRefCompagnie);
    }

    @Test
    @Transactional
    public void updateNonExistingRefCompagnie() throws Exception {
        int databaseSizeBeforeUpdate = refCompagnieRepository.findAll().size();

        // Create the RefCompagnie

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRefCompagnieMockMvc.perform(put("/api/ref-compagnies")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refCompagnie)))
            .andExpect(status().isCreated());

        // Validate the RefCompagnie in the database
        List<RefCompagnie> refCompagnieList = refCompagnieRepository.findAll();
        assertThat(refCompagnieList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRefCompagnie() throws Exception {
        // Initialize the database
        refCompagnieRepository.saveAndFlush(refCompagnie);
        refCompagnieSearchRepository.save(refCompagnie);
        int databaseSizeBeforeDelete = refCompagnieRepository.findAll().size();

        // Get the refCompagnie
        restRefCompagnieMockMvc.perform(delete("/api/ref-compagnies/{id}", refCompagnie.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean refCompagnieExistsInEs = refCompagnieSearchRepository.exists(refCompagnie.getId());
        assertThat(refCompagnieExistsInEs).isFalse();

        // Validate the database is empty
        List<RefCompagnie> refCompagnieList = refCompagnieRepository.findAll();
        assertThat(refCompagnieList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchRefCompagnie() throws Exception {
        // Initialize the database
        refCompagnieRepository.saveAndFlush(refCompagnie);
        refCompagnieSearchRepository.save(refCompagnie);

        // Search the refCompagnie
        restRefCompagnieMockMvc.perform(get("/api/_search/ref-compagnies?query=id:" + refCompagnie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refCompagnie.getId().intValue())))
            .andExpect(jsonPath("$.[*].numero").value(hasItem(DEFAULT_NUMERO.intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].isBloque").value(hasItem(DEFAULT_IS_BLOQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefCompagnie.class);
        RefCompagnie refCompagnie1 = new RefCompagnie();
        refCompagnie1.setId(1L);
        RefCompagnie refCompagnie2 = new RefCompagnie();
        refCompagnie2.setId(refCompagnie1.getId());
        assertThat(refCompagnie1).isEqualTo(refCompagnie2);
        refCompagnie2.setId(2L);
        assertThat(refCompagnie1).isNotEqualTo(refCompagnie2);
        refCompagnie1.setId(null);
        assertThat(refCompagnie1).isNotEqualTo(refCompagnie2);
    }
}
