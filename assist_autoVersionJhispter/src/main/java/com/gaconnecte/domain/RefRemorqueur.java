package com.gaconnecte.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RefRemorqueur.
 */
@Entity
@Table(name = "ref_remorqueur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "refremorqueur")
public class RefRemorqueur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "raison_sociale", length = 60, nullable = false)
    private String raisonSociale;

    @Size(min = 3, max = 3)
    @Column(name = "num_etablissement", length = 3)
    private String numEtablissement;

    @Size(min = 1, max = 1)
    @Column(name = "code_categorie", length = 1)
    private String codeCategorie;

    @Size(min = 1, max = 1)
    @Column(name = "code_tva", length = 1)
    private String codeTVA;

    @NotNull
    @Size(min = 9, max = 9)
    @Column(name = "matricule_fiscal", length = 9, nullable = false)
    private String matriculeFiscal;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @ManyToOne(cascade=CascadeType.PERSIST)
    private Contact contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public RefRemorqueur raisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
        return this;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public String getNumEtablissement() {
        return numEtablissement;
    }

    public RefRemorqueur numEtablissement(String numEtablissement) {
        this.numEtablissement = numEtablissement;
        return this;
    }

    public void setNumEtablissement(String numEtablissement) {
        this.numEtablissement = numEtablissement;
    }

    public String getCodeCategorie() {
        return codeCategorie;
    }

    public RefRemorqueur codeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
        return this;
    }

    public void setCodeCategorie(String codeCategorie) {
        this.codeCategorie = codeCategorie;
    }

    public String getCodeTVA() {
        return codeTVA;
    }

    public RefRemorqueur codeTVA(String codeTVA) {
        this.codeTVA = codeTVA;
        return this;
    }

    public void setCodeTVA(String codeTVA) {
        this.codeTVA = codeTVA;
    }

    public String getMatriculeFiscal() {
        return matriculeFiscal;
    }

    public RefRemorqueur matriculeFiscal(String matriculeFiscal) {
        this.matriculeFiscal = matriculeFiscal;
        return this;
    }

    public void setMatriculeFiscal(String matriculeFiscal) {
        this.matriculeFiscal = matriculeFiscal;
    }

    public String getAdresse() {
        return adresse;
    }

    public RefRemorqueur adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public RefRemorqueur latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public RefRemorqueur longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Contact getContact() {
        return contact;
    }

    public RefRemorqueur contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RefRemorqueur refRemorqueur = (RefRemorqueur) o;
        if (refRemorqueur.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refRemorqueur.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefRemorqueur{" +
            "id=" + getId() +
            ", raisonSociale='" + getRaisonSociale() + "'" +
            ", numEtablissement='" + getNumEtablissement() + "'" +
            ", codeCategorie='" + getCodeCategorie() + "'" +
            ", codeTVA='" + getCodeTVA() + "'" +
            ", matriculeFiscal='" + getMatriculeFiscal() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }
}
