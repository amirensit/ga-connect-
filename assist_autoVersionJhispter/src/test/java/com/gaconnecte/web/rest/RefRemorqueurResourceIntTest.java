package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.RefRemorqueur;
import com.gaconnecte.repository.RefRemorqueurRepository;
import com.gaconnecte.repository.search.RefRemorqueurSearchRepository;
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
 * Test class for the RefRemorqueurResource REST controller.
 *
 * @see RefRemorqueurResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class RefRemorqueurResourceIntTest {

    private static final String DEFAULT_RAISON_SOCIALE = "AAAAAAAAAA";
    private static final String UPDATED_RAISON_SOCIALE = "BBBBBBBBBB";

    private static final String DEFAULT_NUM_ETABLISSEMENT = "AAA";
    private static final String UPDATED_NUM_ETABLISSEMENT = "BBB";

    private static final String DEFAULT_CODE_CATEGORIE = "A";
    private static final String UPDATED_CODE_CATEGORIE = "B";

    private static final String DEFAULT_CODE_TVA = "A";
    private static final String UPDATED_CODE_TVA = "B";

    private static final String DEFAULT_MATRICULE_FISCAL = "AAAAAAAAA";
    private static final String UPDATED_MATRICULE_FISCAL = "BBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    @Autowired
    private RefRemorqueurRepository refRemorqueurRepository;

    @Autowired
    private RefRemorqueurSearchRepository refRemorqueurSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRefRemorqueurMockMvc;

    private RefRemorqueur refRemorqueur;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RefRemorqueurResource refRemorqueurResource = new RefRemorqueurResource(refRemorqueurRepository, refRemorqueurSearchRepository);
        this.restRefRemorqueurMockMvc = MockMvcBuilders.standaloneSetup(refRemorqueurResource)
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
    public static RefRemorqueur createEntity(EntityManager em) {
        RefRemorqueur refRemorqueur = new RefRemorqueur()
            .raisonSociale(DEFAULT_RAISON_SOCIALE)
            .numEtablissement(DEFAULT_NUM_ETABLISSEMENT)
            .codeCategorie(DEFAULT_CODE_CATEGORIE)
            .codeTVA(DEFAULT_CODE_TVA)
            .matriculeFiscal(DEFAULT_MATRICULE_FISCAL)
            .adresse(DEFAULT_ADRESSE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return refRemorqueur;
    }

    @Before
    public void initTest() {
        refRemorqueurSearchRepository.deleteAll();
        refRemorqueur = createEntity(em);
    }

    @Test
    @Transactional
    public void createRefRemorqueur() throws Exception {
        int databaseSizeBeforeCreate = refRemorqueurRepository.findAll().size();

        // Create the RefRemorqueur
        restRefRemorqueurMockMvc.perform(post("/api/ref-remorqueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRemorqueur)))
            .andExpect(status().isCreated());

        // Validate the RefRemorqueur in the database
        List<RefRemorqueur> refRemorqueurList = refRemorqueurRepository.findAll();
        assertThat(refRemorqueurList).hasSize(databaseSizeBeforeCreate + 1);
        RefRemorqueur testRefRemorqueur = refRemorqueurList.get(refRemorqueurList.size() - 1);
        assertThat(testRefRemorqueur.getRaisonSociale()).isEqualTo(DEFAULT_RAISON_SOCIALE);
        assertThat(testRefRemorqueur.getNumEtablissement()).isEqualTo(DEFAULT_NUM_ETABLISSEMENT);
        assertThat(testRefRemorqueur.getCodeCategorie()).isEqualTo(DEFAULT_CODE_CATEGORIE);
        assertThat(testRefRemorqueur.getCodeTVA()).isEqualTo(DEFAULT_CODE_TVA);
        assertThat(testRefRemorqueur.getMatriculeFiscal()).isEqualTo(DEFAULT_MATRICULE_FISCAL);
        assertThat(testRefRemorqueur.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testRefRemorqueur.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testRefRemorqueur.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);

        // Validate the RefRemorqueur in Elasticsearch
        RefRemorqueur refRemorqueurEs = refRemorqueurSearchRepository.findOne(testRefRemorqueur.getId());
        assertThat(refRemorqueurEs).isEqualToComparingFieldByField(testRefRemorqueur);
    }

    @Test
    @Transactional
    public void createRefRemorqueurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = refRemorqueurRepository.findAll().size();

        // Create the RefRemorqueur with an existing ID
        refRemorqueur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRefRemorqueurMockMvc.perform(post("/api/ref-remorqueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRemorqueur)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<RefRemorqueur> refRemorqueurList = refRemorqueurRepository.findAll();
        assertThat(refRemorqueurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRaisonSocialeIsRequired() throws Exception {
        int databaseSizeBeforeTest = refRemorqueurRepository.findAll().size();
        // set the field null
        refRemorqueur.setRaisonSociale(null);

        // Create the RefRemorqueur, which fails.

        restRefRemorqueurMockMvc.perform(post("/api/ref-remorqueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRemorqueur)))
            .andExpect(status().isBadRequest());

        List<RefRemorqueur> refRemorqueurList = refRemorqueurRepository.findAll();
        assertThat(refRemorqueurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMatriculeFiscalIsRequired() throws Exception {
        int databaseSizeBeforeTest = refRemorqueurRepository.findAll().size();
        // set the field null
        refRemorqueur.setMatriculeFiscal(null);

        // Create the RefRemorqueur, which fails.

        restRefRemorqueurMockMvc.perform(post("/api/ref-remorqueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRemorqueur)))
            .andExpect(status().isBadRequest());

        List<RefRemorqueur> refRemorqueurList = refRemorqueurRepository.findAll();
        assertThat(refRemorqueurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = refRemorqueurRepository.findAll().size();
        // set the field null
        refRemorqueur.setAdresse(null);

        // Create the RefRemorqueur, which fails.

        restRefRemorqueurMockMvc.perform(post("/api/ref-remorqueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRemorqueur)))
            .andExpect(status().isBadRequest());

        List<RefRemorqueur> refRemorqueurList = refRemorqueurRepository.findAll();
        assertThat(refRemorqueurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRefRemorqueurs() throws Exception {
        // Initialize the database
        refRemorqueurRepository.saveAndFlush(refRemorqueur);

        // Get all the refRemorqueurList
        restRefRemorqueurMockMvc.perform(get("/api/ref-remorqueurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refRemorqueur.getId().intValue())))
            .andExpect(jsonPath("$.[*].raisonSociale").value(hasItem(DEFAULT_RAISON_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].numEtablissement").value(hasItem(DEFAULT_NUM_ETABLISSEMENT.toString())))
            .andExpect(jsonPath("$.[*].codeCategorie").value(hasItem(DEFAULT_CODE_CATEGORIE.toString())))
            .andExpect(jsonPath("$.[*].codeTVA").value(hasItem(DEFAULT_CODE_TVA.toString())))
            .andExpect(jsonPath("$.[*].matriculeFiscal").value(hasItem(DEFAULT_MATRICULE_FISCAL.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.toString())));
    }

    @Test
    @Transactional
    public void getRefRemorqueur() throws Exception {
        // Initialize the database
        refRemorqueurRepository.saveAndFlush(refRemorqueur);

        // Get the refRemorqueur
        restRefRemorqueurMockMvc.perform(get("/api/ref-remorqueurs/{id}", refRemorqueur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(refRemorqueur.getId().intValue()))
            .andExpect(jsonPath("$.raisonSociale").value(DEFAULT_RAISON_SOCIALE.toString()))
            .andExpect(jsonPath("$.numEtablissement").value(DEFAULT_NUM_ETABLISSEMENT.toString()))
            .andExpect(jsonPath("$.codeCategorie").value(DEFAULT_CODE_CATEGORIE.toString()))
            .andExpect(jsonPath("$.codeTVA").value(DEFAULT_CODE_TVA.toString()))
            .andExpect(jsonPath("$.matriculeFiscal").value(DEFAULT_MATRICULE_FISCAL.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.toString()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRefRemorqueur() throws Exception {
        // Get the refRemorqueur
        restRefRemorqueurMockMvc.perform(get("/api/ref-remorqueurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRefRemorqueur() throws Exception {
        // Initialize the database
        refRemorqueurRepository.saveAndFlush(refRemorqueur);
        refRemorqueurSearchRepository.save(refRemorqueur);
        int databaseSizeBeforeUpdate = refRemorqueurRepository.findAll().size();

        // Update the refRemorqueur
        RefRemorqueur updatedRefRemorqueur = refRemorqueurRepository.findOne(refRemorqueur.getId());
        updatedRefRemorqueur
            .raisonSociale(UPDATED_RAISON_SOCIALE)
            .numEtablissement(UPDATED_NUM_ETABLISSEMENT)
            .codeCategorie(UPDATED_CODE_CATEGORIE)
            .codeTVA(UPDATED_CODE_TVA)
            .matriculeFiscal(UPDATED_MATRICULE_FISCAL)
            .adresse(UPDATED_ADRESSE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);

        restRefRemorqueurMockMvc.perform(put("/api/ref-remorqueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRefRemorqueur)))
            .andExpect(status().isOk());

        // Validate the RefRemorqueur in the database
        List<RefRemorqueur> refRemorqueurList = refRemorqueurRepository.findAll();
        assertThat(refRemorqueurList).hasSize(databaseSizeBeforeUpdate);
        RefRemorqueur testRefRemorqueur = refRemorqueurList.get(refRemorqueurList.size() - 1);
        assertThat(testRefRemorqueur.getRaisonSociale()).isEqualTo(UPDATED_RAISON_SOCIALE);
        assertThat(testRefRemorqueur.getNumEtablissement()).isEqualTo(UPDATED_NUM_ETABLISSEMENT);
        assertThat(testRefRemorqueur.getCodeCategorie()).isEqualTo(UPDATED_CODE_CATEGORIE);
        assertThat(testRefRemorqueur.getCodeTVA()).isEqualTo(UPDATED_CODE_TVA);
        assertThat(testRefRemorqueur.getMatriculeFiscal()).isEqualTo(UPDATED_MATRICULE_FISCAL);
        assertThat(testRefRemorqueur.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testRefRemorqueur.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testRefRemorqueur.getLongitude()).isEqualTo(UPDATED_LONGITUDE);

        // Validate the RefRemorqueur in Elasticsearch
        RefRemorqueur refRemorqueurEs = refRemorqueurSearchRepository.findOne(testRefRemorqueur.getId());
        assertThat(refRemorqueurEs).isEqualToComparingFieldByField(testRefRemorqueur);
    }

    @Test
    @Transactional
    public void updateNonExistingRefRemorqueur() throws Exception {
        int databaseSizeBeforeUpdate = refRemorqueurRepository.findAll().size();

        // Create the RefRemorqueur

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRefRemorqueurMockMvc.perform(put("/api/ref-remorqueurs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(refRemorqueur)))
            .andExpect(status().isCreated());

        // Validate the RefRemorqueur in the database
        List<RefRemorqueur> refRemorqueurList = refRemorqueurRepository.findAll();
        assertThat(refRemorqueurList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRefRemorqueur() throws Exception {
        // Initialize the database
        refRemorqueurRepository.saveAndFlush(refRemorqueur);
        refRemorqueurSearchRepository.save(refRemorqueur);
        int databaseSizeBeforeDelete = refRemorqueurRepository.findAll().size();

        // Get the refRemorqueur
        restRefRemorqueurMockMvc.perform(delete("/api/ref-remorqueurs/{id}", refRemorqueur.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean refRemorqueurExistsInEs = refRemorqueurSearchRepository.exists(refRemorqueur.getId());
        assertThat(refRemorqueurExistsInEs).isFalse();

        // Validate the database is empty
        List<RefRemorqueur> refRemorqueurList = refRemorqueurRepository.findAll();
        assertThat(refRemorqueurList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchRefRemorqueur() throws Exception {
        // Initialize the database
        refRemorqueurRepository.saveAndFlush(refRemorqueur);
        refRemorqueurSearchRepository.save(refRemorqueur);

        // Search the refRemorqueur
        restRefRemorqueurMockMvc.perform(get("/api/_search/ref-remorqueurs?query=id:" + refRemorqueur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(refRemorqueur.getId().intValue())))
            .andExpect(jsonPath("$.[*].raisonSociale").value(hasItem(DEFAULT_RAISON_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].numEtablissement").value(hasItem(DEFAULT_NUM_ETABLISSEMENT.toString())))
            .andExpect(jsonPath("$.[*].codeCategorie").value(hasItem(DEFAULT_CODE_CATEGORIE.toString())))
            .andExpect(jsonPath("$.[*].codeTVA").value(hasItem(DEFAULT_CODE_TVA.toString())))
            .andExpect(jsonPath("$.[*].matriculeFiscal").value(hasItem(DEFAULT_MATRICULE_FISCAL.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RefRemorqueur.class);
        RefRemorqueur refRemorqueur1 = new RefRemorqueur();
        refRemorqueur1.setId(1L);
        RefRemorqueur refRemorqueur2 = new RefRemorqueur();
        refRemorqueur2.setId(refRemorqueur1.getId());
        assertThat(refRemorqueur1).isEqualTo(refRemorqueur2);
        refRemorqueur2.setId(2L);
        assertThat(refRemorqueur1).isNotEqualTo(refRemorqueur2);
        refRemorqueur1.setId(null);
        assertThat(refRemorqueur1).isNotEqualTo(refRemorqueur2);
    }
}
