package com.gaconnecte.web.rest;

import com.gaconnecte.AssistanceApp;

import com.gaconnecte.domain.Personne;
import com.gaconnecte.repository.PersonneRepository;
import com.gaconnecte.repository.search.PersonneSearchRepository;
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
 * Test class for the PersonneResource REST controller.
 *
 * @see PersonneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AssistanceApp.class)
public class PersonneResourceIntTest {

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final Long DEFAULT_TEL_PRINCIPAL = 1L;
    private static final Long UPDATED_TEL_PRINCIPAL = 2L;

    private static final Long DEFAULT_AUTRE_TEL = 1L;
    private static final Long UPDATED_AUTRE_TEL = 2L;

    private static final Long DEFAULT_FAX = 1L;
    private static final Long UPDATED_FAX = 2L;

    private static final String DEFAULT_MAIL_PRINCIPAL = "AAAAAAAAAA";
    private static final String UPDATED_MAIL_PRINCIPAL = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_LATITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LATITUDE = "BBBBBBBBBB";

    private static final String DEFAULT_LONGITUDE = "AAAAAAAAAA";
    private static final String UPDATED_LONGITUDE = "BBBBBBBBBB";

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private PersonneSearchRepository personneSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPersonneMockMvc;

    private Personne personne;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PersonneResource personneResource = new PersonneResource(personneRepository, personneSearchRepository);
        this.restPersonneMockMvc = MockMvcBuilders.standaloneSetup(personneResource)
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
    public static Personne createEntity(EntityManager em) {
        Personne personne = new Personne()
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .telPrincipal(DEFAULT_TEL_PRINCIPAL)
            .autreTel(DEFAULT_AUTRE_TEL)
            .fax(DEFAULT_FAX)
            .mailPrincipal(DEFAULT_MAIL_PRINCIPAL)
            .autreMail(DEFAULT_AUTRE_MAIL)
            .adresse(DEFAULT_ADRESSE)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return personne;
    }

    @Before
    public void initTest() {
        personneSearchRepository.deleteAll();
        personne = createEntity(em);
    }

    @Test
    @Transactional
    public void createPersonne() throws Exception {
        int databaseSizeBeforeCreate = personneRepository.findAll().size();

        // Create the Personne
        restPersonneMockMvc.perform(post("/api/personnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personne)))
            .andExpect(status().isCreated());

        // Validate the Personne in the database
        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeCreate + 1);
        Personne testPersonne = personneList.get(personneList.size() - 1);
        assertThat(testPersonne.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testPersonne.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testPersonne.getTelPrincipal()).isEqualTo(DEFAULT_TEL_PRINCIPAL);
        assertThat(testPersonne.getAutreTel()).isEqualTo(DEFAULT_AUTRE_TEL);
        assertThat(testPersonne.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testPersonne.getMailPrincipal()).isEqualTo(DEFAULT_MAIL_PRINCIPAL);
        assertThat(testPersonne.getAutreMail()).isEqualTo(DEFAULT_AUTRE_MAIL);
        assertThat(testPersonne.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testPersonne.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testPersonne.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);

        // Validate the Personne in Elasticsearch
        Personne personneEs = personneSearchRepository.findOne(testPersonne.getId());
        assertThat(personneEs).isEqualToComparingFieldByField(testPersonne);
    }

    @Test
    @Transactional
    public void createPersonneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personneRepository.findAll().size();

        // Create the Personne with an existing ID
        personne.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonneMockMvc.perform(post("/api/personnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personne)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkPrenomIsRequired() throws Exception {
        int databaseSizeBeforeTest = personneRepository.findAll().size();
        // set the field null
        personne.setPrenom(null);

        // Create the Personne, which fails.

        restPersonneMockMvc.perform(post("/api/personnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personne)))
            .andExpect(status().isBadRequest());

        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNomIsRequired() throws Exception {
        int databaseSizeBeforeTest = personneRepository.findAll().size();
        // set the field null
        personne.setNom(null);

        // Create the Personne, which fails.

        restPersonneMockMvc.perform(post("/api/personnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personne)))
            .andExpect(status().isBadRequest());

        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelPrincipalIsRequired() throws Exception {
        int databaseSizeBeforeTest = personneRepository.findAll().size();
        // set the field null
        personne.setTelPrincipal(null);

        // Create the Personne, which fails.

        restPersonneMockMvc.perform(post("/api/personnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personne)))
            .andExpect(status().isBadRequest());

        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAdresseIsRequired() throws Exception {
        int databaseSizeBeforeTest = personneRepository.findAll().size();
        // set the field null
        personne.setAdresse(null);

        // Create the Personne, which fails.

        restPersonneMockMvc.perform(post("/api/personnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personne)))
            .andExpect(status().isBadRequest());

        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPersonnes() throws Exception {
        // Initialize the database
        personneRepository.saveAndFlush(personne);

        // Get all the personneList
        restPersonneMockMvc.perform(get("/api/personnes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personne.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].telPrincipal").value(hasItem(DEFAULT_TEL_PRINCIPAL.intValue())))
            .andExpect(jsonPath("$.[*].autreTel").value(hasItem(DEFAULT_AUTRE_TEL.intValue())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.intValue())))
            .andExpect(jsonPath("$.[*].mailPrincipal").value(hasItem(DEFAULT_MAIL_PRINCIPAL.toString())))
            .andExpect(jsonPath("$.[*].autreMail").value(hasItem(DEFAULT_AUTRE_MAIL.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.toString())));
    }

    @Test
    @Transactional
    public void getPersonne() throws Exception {
        // Initialize the database
        personneRepository.saveAndFlush(personne);

        // Get the personne
        restPersonneMockMvc.perform(get("/api/personnes/{id}", personne.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(personne.getId().intValue()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM.toString()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM.toString()))
            .andExpect(jsonPath("$.telPrincipal").value(DEFAULT_TEL_PRINCIPAL.intValue()))
            .andExpect(jsonPath("$.autreTel").value(DEFAULT_AUTRE_TEL.intValue()))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX.intValue()))
            .andExpect(jsonPath("$.mailPrincipal").value(DEFAULT_MAIL_PRINCIPAL.toString()))
            .andExpect(jsonPath("$.autreMail").value(DEFAULT_AUTRE_MAIL.toString()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE.toString()))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.toString()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPersonne() throws Exception {
        // Get the personne
        restPersonneMockMvc.perform(get("/api/personnes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePersonne() throws Exception {
        // Initialize the database
        personneRepository.saveAndFlush(personne);
        personneSearchRepository.save(personne);
        int databaseSizeBeforeUpdate = personneRepository.findAll().size();

        // Update the personne
        Personne updatedPersonne = personneRepository.findOne(personne.getId());
        updatedPersonne
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .telPrincipal(UPDATED_TEL_PRINCIPAL)
            .autreTel(UPDATED_AUTRE_TEL)
            .fax(UPDATED_FAX)
            .mailPrincipal(UPDATED_MAIL_PRINCIPAL)
            .autreMail(UPDATED_AUTRE_MAIL)
            .adresse(UPDATED_ADRESSE)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);

        restPersonneMockMvc.perform(put("/api/personnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPersonne)))
            .andExpect(status().isOk());

        // Validate the Personne in the database
        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeUpdate);
        Personne testPersonne = personneList.get(personneList.size() - 1);
        assertThat(testPersonne.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testPersonne.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testPersonne.getTelPrincipal()).isEqualTo(UPDATED_TEL_PRINCIPAL);
        assertThat(testPersonne.getAutreTel()).isEqualTo(UPDATED_AUTRE_TEL);
        assertThat(testPersonne.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testPersonne.getMailPrincipal()).isEqualTo(UPDATED_MAIL_PRINCIPAL);
        assertThat(testPersonne.getAutreMail()).isEqualTo(UPDATED_AUTRE_MAIL);
        assertThat(testPersonne.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testPersonne.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testPersonne.getLongitude()).isEqualTo(UPDATED_LONGITUDE);

        // Validate the Personne in Elasticsearch
        Personne personneEs = personneSearchRepository.findOne(testPersonne.getId());
        assertThat(personneEs).isEqualToComparingFieldByField(testPersonne);
    }

    @Test
    @Transactional
    public void updateNonExistingPersonne() throws Exception {
        int databaseSizeBeforeUpdate = personneRepository.findAll().size();

        // Create the Personne

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPersonneMockMvc.perform(put("/api/personnes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(personne)))
            .andExpect(status().isCreated());

        // Validate the Personne in the database
        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePersonne() throws Exception {
        // Initialize the database
        personneRepository.saveAndFlush(personne);
        personneSearchRepository.save(personne);
        int databaseSizeBeforeDelete = personneRepository.findAll().size();

        // Get the personne
        restPersonneMockMvc.perform(delete("/api/personnes/{id}", personne.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean personneExistsInEs = personneSearchRepository.exists(personne.getId());
        assertThat(personneExistsInEs).isFalse();

        // Validate the database is empty
        List<Personne> personneList = personneRepository.findAll();
        assertThat(personneList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPersonne() throws Exception {
        // Initialize the database
        personneRepository.saveAndFlush(personne);
        personneSearchRepository.save(personne);

        // Search the personne
        restPersonneMockMvc.perform(get("/api/_search/personnes?query=id:" + personne.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(personne.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM.toString())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM.toString())))
            .andExpect(jsonPath("$.[*].telPrincipal").value(hasItem(DEFAULT_TEL_PRINCIPAL.intValue())))
            .andExpect(jsonPath("$.[*].autreTel").value(hasItem(DEFAULT_AUTRE_TEL.intValue())))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX.intValue())))
            .andExpect(jsonPath("$.[*].mailPrincipal").value(hasItem(DEFAULT_MAIL_PRINCIPAL.toString())))
            .andExpect(jsonPath("$.[*].autreMail").value(hasItem(DEFAULT_AUTRE_MAIL.toString())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE.toString())))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Personne.class);
        Personne personne1 = new Personne();
        personne1.setId(1L);
        Personne personne2 = new Personne();
        personne2.setId(personne1.getId());
        assertThat(personne1).isEqualTo(personne2);
        personne2.setId(2L);
        assertThat(personne1).isNotEqualTo(personne2);
        personne1.setId(null);
        assertThat(personne1).isNotEqualTo(personne2);
    }
}
