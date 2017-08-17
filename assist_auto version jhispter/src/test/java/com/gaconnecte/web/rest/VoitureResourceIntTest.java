package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.Voiture;
import com.gaconnecte.repository.VoitureRepository;
import com.gaconnecte.repository.search.VoitureSearchRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VoitureResource REST controller.
 *
 * @see VoitureResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class VoitureResourceIntTest {

    private static final String DEFAULT_IMMATRICULATION = "0133TU44";
    private static final String UPDATED_IMMATRICULATION = "4075TU30";

    private static final LocalDate DEFAULT_MISE_CIRCULATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MISE_CIRCULATION = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_PUISSANCE_FISCALE = 1L;
    private static final Long UPDATED_PUISSANCE_FISCALE = 2L;

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private VoitureSearchRepository voitureSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVoitureMockMvc;

    private Voiture voiture;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        VoitureResource voitureResource = new VoitureResource(voitureRepository, voitureSearchRepository);
        this.restVoitureMockMvc = MockMvcBuilders.standaloneSetup(voitureResource)
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
    public static Voiture createEntity(EntityManager em) {
        Voiture voiture = new Voiture()
            .immatriculation(DEFAULT_IMMATRICULATION)
            .miseCirculation(DEFAULT_MISE_CIRCULATION)
            .puissanceFiscale(DEFAULT_PUISSANCE_FISCALE);
        return voiture;
    }

    @Before
    public void initTest() {
        voitureSearchRepository.deleteAll();
        voiture = createEntity(em);
    }

    @Test
    @Transactional
    public void createVoiture() throws Exception {
        int databaseSizeBeforeCreate = voitureRepository.findAll().size();

        // Create the Voiture
        restVoitureMockMvc.perform(post("/api/voitures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voiture)))
            .andExpect(status().isCreated());

        // Validate the Voiture in the database
        List<Voiture> voitureList = voitureRepository.findAll();
        assertThat(voitureList).hasSize(databaseSizeBeforeCreate + 1);
        Voiture testVoiture = voitureList.get(voitureList.size() - 1);
        assertThat(testVoiture.getImmatriculation()).isEqualTo(DEFAULT_IMMATRICULATION);
        assertThat(testVoiture.getMiseCirculation()).isEqualTo(DEFAULT_MISE_CIRCULATION);
        assertThat(testVoiture.getPuissanceFiscale()).isEqualTo(DEFAULT_PUISSANCE_FISCALE);

        // Validate the Voiture in Elasticsearch
        Voiture voitureEs = voitureSearchRepository.findOne(testVoiture.getId());
        assertThat(voitureEs).isEqualToComparingFieldByField(testVoiture);
    }

    @Test
    @Transactional
    public void createVoitureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = voitureRepository.findAll().size();

        // Create the Voiture with an existing ID
        voiture.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVoitureMockMvc.perform(post("/api/voitures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voiture)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Voiture> voitureList = voitureRepository.findAll();
        assertThat(voitureList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkImmatriculationIsRequired() throws Exception {
        int databaseSizeBeforeTest = voitureRepository.findAll().size();
        // set the field null
        voiture.setImmatriculation(null);

        // Create the Voiture, which fails.

        restVoitureMockMvc.perform(post("/api/voitures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voiture)))
            .andExpect(status().isBadRequest());

        List<Voiture> voitureList = voitureRepository.findAll();
        assertThat(voitureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMiseCirculationIsRequired() throws Exception {
        int databaseSizeBeforeTest = voitureRepository.findAll().size();
        // set the field null
        voiture.setMiseCirculation(null);

        // Create the Voiture, which fails.

        restVoitureMockMvc.perform(post("/api/voitures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voiture)))
            .andExpect(status().isBadRequest());

        List<Voiture> voitureList = voitureRepository.findAll();
        assertThat(voitureList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVoitures() throws Exception {
        // Initialize the database
        voitureRepository.saveAndFlush(voiture);

        // Get all the voitureList
        restVoitureMockMvc.perform(get("/api/voitures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voiture.getId().intValue())))
            .andExpect(jsonPath("$.[*].immatriculation").value(hasItem(DEFAULT_IMMATRICULATION.toString())))
            .andExpect(jsonPath("$.[*].miseCirculation").value(hasItem(DEFAULT_MISE_CIRCULATION.toString())))
            .andExpect(jsonPath("$.[*].puissanceFiscale").value(hasItem(DEFAULT_PUISSANCE_FISCALE.intValue())));
    }

    @Test
    @Transactional
    public void getVoiture() throws Exception {
        // Initialize the database
        voitureRepository.saveAndFlush(voiture);

        // Get the voiture
        restVoitureMockMvc.perform(get("/api/voitures/{id}", voiture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(voiture.getId().intValue()))
            .andExpect(jsonPath("$.immatriculation").value(DEFAULT_IMMATRICULATION.toString()))
            .andExpect(jsonPath("$.miseCirculation").value(DEFAULT_MISE_CIRCULATION.toString()))
            .andExpect(jsonPath("$.puissanceFiscale").value(DEFAULT_PUISSANCE_FISCALE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingVoiture() throws Exception {
        // Get the voiture
        restVoitureMockMvc.perform(get("/api/voitures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVoiture() throws Exception {
        // Initialize the database
        voitureRepository.saveAndFlush(voiture);
        voitureSearchRepository.save(voiture);
        int databaseSizeBeforeUpdate = voitureRepository.findAll().size();

        // Update the voiture
        Voiture updatedVoiture = voitureRepository.findOne(voiture.getId());
        updatedVoiture
            .immatriculation(UPDATED_IMMATRICULATION)
            .miseCirculation(UPDATED_MISE_CIRCULATION)
            .puissanceFiscale(UPDATED_PUISSANCE_FISCALE);

        restVoitureMockMvc.perform(put("/api/voitures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVoiture)))
            .andExpect(status().isOk());

        // Validate the Voiture in the database
        List<Voiture> voitureList = voitureRepository.findAll();
        assertThat(voitureList).hasSize(databaseSizeBeforeUpdate);
        Voiture testVoiture = voitureList.get(voitureList.size() - 1);
        assertThat(testVoiture.getImmatriculation()).isEqualTo(UPDATED_IMMATRICULATION);
        assertThat(testVoiture.getMiseCirculation()).isEqualTo(UPDATED_MISE_CIRCULATION);
        assertThat(testVoiture.getPuissanceFiscale()).isEqualTo(UPDATED_PUISSANCE_FISCALE);

        // Validate the Voiture in Elasticsearch
        Voiture voitureEs = voitureSearchRepository.findOne(testVoiture.getId());
        assertThat(voitureEs).isEqualToComparingFieldByField(testVoiture);
    }

    @Test
    @Transactional
    public void updateNonExistingVoiture() throws Exception {
        int databaseSizeBeforeUpdate = voitureRepository.findAll().size();

        // Create the Voiture

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVoitureMockMvc.perform(put("/api/voitures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(voiture)))
            .andExpect(status().isCreated());

        // Validate the Voiture in the database
        List<Voiture> voitureList = voitureRepository.findAll();
        assertThat(voitureList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVoiture() throws Exception {
        // Initialize the database
        voitureRepository.saveAndFlush(voiture);
        voitureSearchRepository.save(voiture);
        int databaseSizeBeforeDelete = voitureRepository.findAll().size();

        // Get the voiture
        restVoitureMockMvc.perform(delete("/api/voitures/{id}", voiture.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean voitureExistsInEs = voitureSearchRepository.exists(voiture.getId());
        assertThat(voitureExistsInEs).isFalse();

        // Validate the database is empty
        List<Voiture> voitureList = voitureRepository.findAll();
        assertThat(voitureList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchVoiture() throws Exception {
        // Initialize the database
        voitureRepository.saveAndFlush(voiture);
        voitureSearchRepository.save(voiture);

        // Search the voiture
        restVoitureMockMvc.perform(get("/api/_search/voitures?query=id:" + voiture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(voiture.getId().intValue())))
            .andExpect(jsonPath("$.[*].immatriculation").value(hasItem(DEFAULT_IMMATRICULATION.toString())))
            .andExpect(jsonPath("$.[*].miseCirculation").value(hasItem(DEFAULT_MISE_CIRCULATION.toString())))
            .andExpect(jsonPath("$.[*].puissanceFiscale").value(hasItem(DEFAULT_PUISSANCE_FISCALE.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Voiture.class);
        Voiture voiture1 = new Voiture();
        voiture1.setId(1L);
        Voiture voiture2 = new Voiture();
        voiture2.setId(voiture1.getId());
        assertThat(voiture1).isEqualTo(voiture2);
        voiture2.setId(2L);
        assertThat(voiture1).isNotEqualTo(voiture2);
        voiture1.setId(null);
        assertThat(voiture1).isNotEqualTo(voiture2);
    }
}
