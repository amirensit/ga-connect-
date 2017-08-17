package com.gaconnecte.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SysVille.
 */
@Entity
@Table(name = "sys_ville")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sysville")
public class SysVille implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "nom", length = 60, nullable = false)
    private String nom;

    @ManyToOne(optional = false)
    @NotNull
    private SysGouvernorat gouvernorat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public SysVille nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public SysGouvernorat getGouvernorat() {
        return gouvernorat;
    }

    public SysVille gouvernorat(SysGouvernorat sysGouvernorat) {
        this.gouvernorat = sysGouvernorat;
        return this;
    }

    public void setGouvernorat(SysGouvernorat sysGouvernorat) {
        this.gouvernorat = sysGouvernorat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysVille sysVille = (SysVille) o;
        if (sysVille.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sysVille.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SysVille{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
