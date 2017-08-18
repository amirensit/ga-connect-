package com.gaconnecte.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SysZoneGeographique.
 */
@Entity
@Table(name = "sys_zone_geographique")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "syszonegeographique")
public class SysZoneGeographique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "nom", length = 60, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "zoneGeographique")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SysGouvernorat> gouvernorats = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public SysZoneGeographique nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<SysGouvernorat> getGouvernorats() {
        return gouvernorats;
    }

    public SysZoneGeographique gouvernorats(Set<SysGouvernorat> sysGouvernorats) {
        this.gouvernorats = sysGouvernorats;
        return this;
    }

    public SysZoneGeographique addGouvernorat(SysGouvernorat sysGouvernorat) {
        this.gouvernorats.add(sysGouvernorat);
        sysGouvernorat.setZoneGeographique(this);
        return this;
    }

    public SysZoneGeographique removeGouvernorat(SysGouvernorat sysGouvernorat) {
        this.gouvernorats.remove(sysGouvernorat);
        sysGouvernorat.setZoneGeographique(null);
        return this;
    }

    public void setGouvernorats(Set<SysGouvernorat> sysGouvernorats) {
        this.gouvernorats = sysGouvernorats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysZoneGeographique sysZoneGeographique = (SysZoneGeographique) o;
        if (sysZoneGeographique.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysZoneGeographique.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysZoneGeographique{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
