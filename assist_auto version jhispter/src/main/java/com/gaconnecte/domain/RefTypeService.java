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
 * A RefTypeService.
 */
@Entity
@Table(name = "ref_type_service")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "reftypeservice")
public class RefTypeService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 60)
    @Column(name = "nom", length = 60, nullable = false)
    private String nom;

    @ManyToMany(mappedBy = "typeServices")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RefPack> packs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public RefTypeService nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<RefPack> getPacks() {
        return packs;
    }

    public RefTypeService packs(Set<RefPack> refPacks) {
        this.packs = refPacks;
        return this;
    }

    public RefTypeService addPack(RefPack refPack) {
        this.packs.add(refPack);
        refPack.getTypeServices().add(this);
        return this;
    }

    public RefTypeService removePack(RefPack refPack) {
        this.packs.remove(refPack);
        refPack.getTypeServices().remove(this);
        return this;
    }

    public void setPacks(Set<RefPack> refPacks) {
        this.packs = refPacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RefTypeService refTypeService = (RefTypeService) o;
        if (refTypeService.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refTypeService.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefTypeService{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
