package com.gaconnecte.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "contrat")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "contrat")
public class Contrat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "numero", nullable = false)
    private Long numero;

    @NotNull
    @Column(name = "debut", nullable = false)
    private LocalDate debut;

    @NotNull
    @Column(name = "fin", nullable = false)
    private LocalDate fin;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private Voiture voiture;

    @ManyToOne(cascade=CascadeType.PERSIST)
    private Assuree assure;

    @ManyToOne
    private RefPack pack;

    @ManyToOne
    private RefCompagnie compagnie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public Contrat numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public Contrat debut(LocalDate debut) {
        this.debut = debut;
        return this;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public Contrat fin(LocalDate fin) {
        this.fin = fin;
        return this;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public Contrat voiture(Voiture voiture) {
        this.voiture = voiture;
        return this;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Assuree getAssure() {
        return assure;
    }

    public Contrat assure(Assuree assuree) {
        this.assure = assuree;
        return this;
    }

    public void setAssure(Assuree assuree) {
        this.assure = assuree;
    }

    public RefPack getPack() {
        return pack;
    }

    public Contrat pack(RefPack refPack) {
        this.pack = refPack;
        return this;
    }

    public void setPack(RefPack refPack) {
        this.pack = refPack;
    }

    public RefCompagnie getCompagnie() {
        return compagnie;
    }

    public Contrat compagnie(RefCompagnie refCompagnie) {
        this.compagnie = refCompagnie;
        return this;
    }

    public void setCompagnie(RefCompagnie refCompagnie) {
        this.compagnie = refCompagnie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contrat contrat = (Contrat) o;
        if (contrat.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contrat.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Contrat{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", debut='" + getDebut() + "'" +
            ", fin='" + getFin() + "'" +
            "}";
    }
}
