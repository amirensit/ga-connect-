package com.gaconnecte.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Voiture.
 */
@Entity
@Table(name = "voiture")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "voiture")
public class Voiture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = "\\d{4}TU\\d+")
    @Column(name = "immatriculation", nullable = false)
    private String immatriculation;

    @NotNull
    @Column(name = "mise_circulation", nullable = false)
    private LocalDate miseCirculation;

    @Column(name = "puissance_fiscale")
    private Long puissanceFiscale;

    @ManyToOne
    private RefMarque marque;

    @OneToOne(mappedBy = "voiture", cascade=CascadeType.ALL)
    @JsonIgnore
    private Contrat contrat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public Voiture immatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
        return this;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public LocalDate getMiseCirculation() {
        return miseCirculation;
    }

    public Voiture miseCirculation(LocalDate miseCirculation) {
        this.miseCirculation = miseCirculation;
        return this;
    }

    public void setMiseCirculation(LocalDate miseCirculation) {
        this.miseCirculation = miseCirculation;
    }

    public Long getPuissanceFiscale() {
        return puissanceFiscale;
    }

    public Voiture puissanceFiscale(Long puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
        return this;
    }

    public void setPuissanceFiscale(Long puissanceFiscale) {
        this.puissanceFiscale = puissanceFiscale;
    }

    public RefMarque getMarque() {
        return marque;
    }

    public Voiture marque(RefMarque refMarque) {
        this.marque = refMarque;
        return this;
    }

    public void setMarque(RefMarque refMarque) {
        this.marque = refMarque;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public Voiture contrat(Contrat contrat) {
        this.contrat = contrat;
        return this;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Voiture voiture = (Voiture) o;
        if (voiture.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), voiture.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Voiture{" +
            "id=" + getId() +
            ", immatriculation='" + getImmatriculation() + "'" +
            ", miseCirculation='" + getMiseCirculation() + "'" +
            ", puissanceFiscale='" + getPuissanceFiscale() + "'" +
            "}";
    }
}
