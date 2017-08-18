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
 * A SysGouvernorat.
 */
@Entity
@Table(name = "sys_gouvernorat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sysgouvernorat")
public class SysGouvernorat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "nom", length = 60, nullable = false)
    private String nom;

    @OneToMany(mappedBy = "gouvernorat")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SysVille> villes = new HashSet<>();

    @ManyToOne
    private SysZoneGeographique zoneGeographique;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public SysGouvernorat nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<SysVille> getVilles() {
        return villes;
    }

    public SysGouvernorat villes(Set<SysVille> sysVilles) {
        this.villes = sysVilles;
        return this;
    }

    public SysGouvernorat addVille(SysVille sysVille) {
        this.villes.add(sysVille);
        sysVille.setGouvernorat(this);
        return this;
    }

    public SysGouvernorat removeVille(SysVille sysVille) {
        this.villes.remove(sysVille);
        sysVille.setGouvernorat(null);
        return this;
    }

    public void setVilles(Set<SysVille> sysVilles) {
        this.villes = sysVilles;
    }

    public SysZoneGeographique getZoneGeographique() {
        return zoneGeographique;
    }

    public SysGouvernorat zoneGeographique(SysZoneGeographique sysZoneGeographique) {
        this.zoneGeographique = sysZoneGeographique;
        return this;
    }

    public void setZoneGeographique(SysZoneGeographique sysZoneGeographique) {
        this.zoneGeographique = sysZoneGeographique;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysGouvernorat sysGouvernorat = (SysGouvernorat) o;
        if (sysGouvernorat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysGouvernorat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysGouvernorat{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
