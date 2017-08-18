package com.gaconnecte.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "is_gerant")
    private Boolean isGerant;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private Personne personne;

    @OneToMany(mappedBy = "contact")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RefRemorqueur> remorqueurs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isIsGerant() {
        return isGerant;
    }

    public Contact isGerant(Boolean isGerant) {
        this.isGerant = isGerant;
        return this;
    }

    public void setIsGerant(Boolean isGerant) {
        this.isGerant = isGerant;
    }

    public Personne getPersonne() {
        return personne;
    }

    public Contact personne(Personne personne) {
        this.personne = personne;
        return this;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Set<RefRemorqueur> getRemorqueurs() {
        return remorqueurs;
    }

    public Contact remorqueurs(Set<RefRemorqueur> refRemorqueurs) {
        this.remorqueurs = refRemorqueurs;
        return this;
    }

    public Contact addRemorqueur(RefRemorqueur refRemorqueur) {
        this.remorqueurs.add(refRemorqueur);
        refRemorqueur.setContact(this);
        return this;
    }

    public Contact removeRemorqueur(RefRemorqueur refRemorqueur) {
        this.remorqueurs.remove(refRemorqueur);
        refRemorqueur.setContact(null);
        return this;
    }

    public void setRemorqueurs(Set<RefRemorqueur> refRemorqueurs) {
        this.remorqueurs = refRemorqueurs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        if (contact.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            ", isGerant='" + isIsGerant() + "'" +
            "}";
    }
}
