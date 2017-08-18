package com.gaconnecte.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Assuree.
 */
@Entity
@Table(name = "assuree")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "assuree")
public class Assuree implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;
    //@Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private Personne personne;

    @OneToMany(mappedBy = "assure",cascade=CascadeType.MERGE)
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contrat> contrats = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public Assuree personne(Personne personne) {
        this.personne = personne;
        return this;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Set<Contrat> getContrats() {
        return contrats;
    }

    public Assuree contrats(Set<Contrat> contrats) {
        this.contrats = contrats;
        return this;
    }

    public Assuree addContrat(Contrat contrat) {
        this.contrats.add(contrat);
        contrat.setAssure(this);
        return this;
    }

    public Assuree removeContrat(Contrat contrat) {
        this.contrats.remove(contrat);
        contrat.setAssure(null);
        return this;
    }

    public void setContrats(Set<Contrat> contrats) {
        this.contrats = contrats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Assuree assuree = (Assuree) o;
        if (assuree.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assuree.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Assuree{" +
            "id=" + getId() +
            "}";
    }
}
