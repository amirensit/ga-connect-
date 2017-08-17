package com.gaconnecte.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DossierRmq.
 */
@Entity
@Table(name = "dossier_rmq")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "dossierrmq")
public class DossierRmq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "distance")
    private Long distance;

    @Size(max = 2000)
    @Column(name = "observation", length = 2000)
    private String observation;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private Assuree assuree;

    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private RefTypeService typeService;

    @ManyToOne
    private SysVille lieu;

    @ManyToOne
    private SysVille destination;

    @ManyToOne
    private RefRemorqueur remorqueur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public DossierRmq reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Long getDistance() {
        return distance;
    }

    public DossierRmq distance(Long distance) {
        this.distance = distance;
        return this;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public String getObservation() {
        return observation;
    }

    public DossierRmq observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Assuree getAssuree() {
        return assuree;
    }

    public DossierRmq assuree(Assuree assuree) {
        this.assuree = assuree;
        return this;
    }

    public void setAssuree(Assuree assuree) {
        this.assuree = assuree;
    }

    public RefTypeService getTypeService() {
        return typeService;
    }

    public DossierRmq typeService(RefTypeService refTypeService) {
        this.typeService = refTypeService;
        return this;
    }

    public void setTypeService(RefTypeService refTypeService) {
        this.typeService = refTypeService;
    }

    public SysVille getLieu() {
        return lieu;
    }

    public DossierRmq lieu(SysVille sysVille) {
        this.lieu = sysVille;
        return this;
    }

    public void setLieu(SysVille sysVille) {
        this.lieu = sysVille;
    }

    public SysVille getDestination() {
        return destination;
    }

    public DossierRmq destination(SysVille sysVille) {
        this.destination = sysVille;
        return this;
    }

    public void setDestination(SysVille sysVille) {
        this.destination = sysVille;
    }

    public RefRemorqueur getRemorqueur() {
        return remorqueur;
    }

    public DossierRmq remorqueur(RefRemorqueur refRemorqueur) {
        this.remorqueur = refRemorqueur;
        return this;
    }

    public void setRemorqueur(RefRemorqueur refRemorqueur) {
        this.remorqueur = refRemorqueur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DossierRmq dossierRmq = (DossierRmq) o;
        if (dossierRmq.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dossierRmq.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DossierRmq{" +
            "id=" + getId() +
            ", reference='" + getReference() + "'" +
            ", distance='" + getDistance() + "'" +
            ", observation='" + getObservation() + "'" +
            "}";
    }
}
