package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.RefPack;
import com.gaconnecte.repository.RefPackRepository;
import com.gaconnecte.repository.search.RefPackSearchRepository;
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
 * Test class for the RefPackResource REST controller.
 *
 * @see RefPackResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class RefPackResourceIntTest {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Long DEFAULT_MAX_SERVICES = 90L;
    private static final Long UPDATED_MAX_SERVICES = 89L;

    private static final Long DEFAULT_MAX_KIL = 9999L;
    private static final Long UPDATED_MAX_KIL = 9998L;

    private static final Boolean DEFAULT_IS_BLOQUE = false;
    private static final Boolean UPDATED_IS_BLOQUE = true;

    @Autowired
    private RefPackRepository refPackRepository;

    @Autowired
    private RefPackSearchRepository refPackSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefPackMockMvc;

    private RefPack refPack;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RefPackResource refPackResource = new RefPackResource(refPackRepository, refPackSearchRepository);
        this.restRefPackMockMvc = MockMvcBuilders.standaloneSetup(refPackResource)
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
    public static RefPack createEntity(EntityManager em) {
        RefPack refPack = new RefPack()
            .nom(DEFAULT_NOM)
            .description(DEFAULT_DESCRIPTION)
            .maxServices(DEFAULT_MAX_SERVICES)
            .maxKil(DEFAULT_MAX_KIL)
            .isBloque(DEFAULT_IS_BLOQUE);
        return refPack;
    }

    @Before
    public void initTest() {
        refPackSearchRepository.deleteAll();
        refPack = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefPack() throws Exception {
        int databaseSizeBeforeCreate = refPackRepository.findAll().size();

        // Create the RefPack
        restRefPackMockMvc.perform(post("/api/ref-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refPack)))
            .andExpect(status().isCreated());

        // Validate the RefPack in the database
        List<RefPack> refPackList = refPackRepository.findAll();
        assertThat(refPackList).hasSize(databaseSizeBeforeCreate + 1);
        RefPack testRefPack = refPackList.get(refPackList.size() - 1);
        assertThat(testRefPack.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testRefPack.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRefPack.getMaxServices()).isEqualTo(DEFAULT_MAX_SERVICES);
        assertThat(testRefPack.getMaxKil()).isEqualTo(DEFAULT_MAX_KIL);
        assertThat(testRefPack.isIsBloque()).isEqualTo(DEFAULT_IS_BLOQUE);

        // Validate the RefPack in Elasticsearch
        RefPack refPackEs = refPackSearchRepository.findOne(testRefPack.getId());
        assertThat(refPackEs).isEqualToComparingFieldByField(testRefPack);
    }

    @Test
    @Transactional
    public void createRefPackWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refPackRepository.findAll().size();

        // Create the RefPack with an existing ID
        refPack.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefPackMockMvc.perform(post("/api/ref-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refPack)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RefPack> refPackList = refPackRepository.findAll();
        assertThat(refPackList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = refPackRepository.findAll().size();
        // set the field null
        refPack.setNom(null);

        // Create the RefPack, which fails.

        restRefPackMockMvc.perform(post("/api/ref-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refPack)))
            .andExpect(status().isBadRequest());

        List<RefPack> refPackList = refPackRepository.findAll();
        assertThat(refPackList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefPacks() throws Exception {
        // Initialize the database
        refPackRepository.saveAndFlush(refPack);

        // Get all the refPackList
        restRefPackMockMvc.perform(get("/api/ref-packs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refPack.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].maxServices").value(hasItem(DEFAULT_MAX_SERVICES.intValue())))
            .andExpect(jsonPath("$.[*].maxKil").value(hasItem(DEFAULT_MAX_KIL.intValue())))
            .andExpect(jsonPath("$.[*].isBloque").value(hasItem(DEFAULT_IS_BLOQUE.booleanValue())));
    }

    @Test
    @Transactional
    public void getRefPack() throws Exception {
        // Initialize the database
        refPackRepository.saveAndFlush(refPack);

        // Get the refPack
        restRefPackMockMvc.perform(get("/api/ref-packs/{id}", refPack.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refPack.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.maxServices").value(DEFAULT_MAX_SERVICES.intValue()))
            .andExpect(jsonPath("$.maxKil").value(DEFAULT_MAX_KIL.intValue()))
            .andExpect(jsonPath("$.isBloque").value(DEFAULT_IS_BLOQUE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRefPack() throws Exception {
        // Get the refPack
        restRefPackMockMvc.perform(get("/api/ref-packs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefPack() throws Exception {
        // Initialize the database
        refPackRepository.saveAndFlush(refPack);
        refPackSearchRepository.save(refPack);
        int databaseSizeBeforeUpdate = refPackRepository.findAll().size();

        // Update the refPack
        RefPack updatedRefPack = refPackRepository.findOne(refPack.getId());
        updatedRefPack
            .nom(UPDATED_NOM)
            .description(UPDATED_DESCRIPTION)
            .maxServices(UPDATED_MAX_SERVICES)
            .maxKil(UPDATED_MAX_KIL)
            .isBloque(UPDATED_IS_BLOQUE);

        restRefPackMockMvc.perform(put("/api/ref-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefPack)))
            .andExpect(status().isOk());

        // Validate the RefPack in the database
        List<RefPack> refPackList = refPackRepository.findAll();
        assertThat(refPackList).hasSize(databaseSizeBeforeUpdate);
        RefPack testRefPack = refPackList.get(refPackList.size() - 1);
        assertThat(testRefPack.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testRefPack.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRefPack.getMaxServices()).isEqualTo(UPDATED_MAX_SERVICES);
        assertThat(testRefPack.getMaxKil()).isEqualTo(UPDATED_MAX_KIL);
        assertThat(testRefPack.isIsBloque()).isEqualTo(UPDATED_IS_BLOQUE);

        // Validate the RefPack in Elasticsearch
        RefPack refPackEs = refPackSearchRepository.findOne(testRefPack.getId());
        assertThat(refPackEs).isEqualToComparingFieldByField(testRefPack);
    }

    @Test
    @Transactional
    public void updateNonExistingRefPack() throws Exception {
        int databaseSizeBeforeUpdate = refPackRepository.findAll().size();

        // Create the RefPack

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRefPackMockMvc.perform(put("/api/ref-packs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refPack)))
            .andExpect(status().isCreated());

        // Validate the RefPack in the database
        List<RefPack> refPackList = refPackRepository.findAll();
        assertThat(refPackList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRefPack() throws Exception {
        // Initialize the database
        refPackRepository.saveAndFlush(refPack);
        refPackSearchRepository.save(refPack);
        int databaseSizeBeforeDelete = refPackRepository.findAll().size();

        // Get the refPack
        restRefPackMockMvc.perform(delete("/api/ref-packs/{id}", refPack.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean refPackExistsInEs = refPackSearchRepository.exists(refPack.getId());
        assertThat(refPackExistsInEs).isFalse();

        // Validate the database is empty
        List<RefPack> refPackList = refPackRepository.findAll();
        assertThat(refPackList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchRefPack() throws Exception {
        // Initialize the database
        refPackRepository.saveAndFlush(refPack);
        refPackSearchRepository.save(refPack);

        // Search the refPack
        restRefPackMockMvc.perform(get("/api/_search/ref-packs?query=id:" + refPack.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refPack.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].maxServices").value(hasItem(DEFAULT_MAX_SERVICES.intValue())))
            .andExpect(jsonPath("$.[*].maxKil").value(hasItem(DEFAULT_MAX_KIL.intValue())))
            .andExpect(jsonPath("$.[*].isBloque").value(hasItem(DEFAULT_IS_BLOQUE.booleanValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefPack.class);
        RefPack refPack1 = new RefPack();
        refPack1.setId(1L);
        RefPack refPack2 = new RefPack();
        refPack2.setId(refPack1.getId());
        assertThat(refPack1).isEqualTo(refPack2);
        refPack2.setId(2L);
        assertThat(refPack1).isNotEqualTo(refPack2);
        refPack1.setId(null);
        assertThat(refPack1).isNotEqualTo(refPack2);
    }
}
