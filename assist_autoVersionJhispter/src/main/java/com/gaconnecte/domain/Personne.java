package com.gaconnecte.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Personne.
 */
@Entity
@Table(name = "personne")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "personne")
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Column(name = "tel_principal", nullable = false)
    private Long telPrincipal;

    @Column(name = "autre_tel")
    private Long autreTel;

    @Column(name = "fax")
    private Long fax;

    @Column(name = "mail_principal")
    private String mailPrincipal;

    @Column(name = "autre_mail")
    private String autreMail;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @OneToOne(cascade=CascadeType.MERGE)
    @JoinColumn(unique = true)
    private SysVille ville;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public Personne prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public Personne nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getTelPrincipal() {
        return telPrincipal;
    }

    public Personne telPrincipal(Long telPrincipal) {
        this.telPrincipal = telPrincipal;
        return this;
    }

    public void setTelPrincipal(Long telPrincipal) {
        this.telPrincipal = telPrincipal;
    }

    public Long getAutreTel() {
        return autreTel;
    }

    public Personne autreTel(Long autreTel) {
        this.autreTel = autreTel;
        return this;
    }

    public void setAutreTel(Long autreTel) {
        this.autreTel = autreTel;
    }

    public Long getFax() {
        return fax;
    }

    public Personne fax(Long fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(Long fax) {
        this.fax = fax;
    }

    public String getMailPrincipal() {
        return mailPrincipal;
    }

    public Personne mailPrincipal(String mailPrincipal) {
        this.mailPrincipal = mailPrincipal;
        return this;
    }

    public void setMailPrincipal(String mailPrincipal) {
        this.mailPrincipal = mailPrincipal;
    }

    public String getAutreMail() {
        return autreMail;
    }

    public Personne autreMail(String autreMail) {
        this.autreMail = autreMail;
        return this;
    }

    public void setAutreMail(String autreMail) {
        this.autreMail = autreMail;
    }

    public String getAdresse() {
        return adresse;
    }

    public Personne adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public Personne latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Personne longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public SysVille getVille() {
        return ville;
    }

    public Personne ville(SysVille sysVille) {
        this.ville = sysVille;
        return this;
    }

    public void setVille(SysVille sysVille) {
        this.ville = sysVille;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Personne personne = (Personne) o;
        if (personne.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personne.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Personne{" +
            "id=" + getId() +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", telPrincipal='" + getTelPrincipal() + "'" +
            ", autreTel='" + getAutreTel() + "'" +
            ", fax='" + getFax() + "'" +
            ", mailPrincipal='" + getMailPrincipal() + "'" +
            ", autreMail='" + getAutreMail() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }
}
