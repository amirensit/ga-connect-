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
 * A RefPack.
 */
@Entity
@Table(name = "ref_pack")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "refpack")
public class RefPack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 60)
    @Pattern(regexp = "^[A-Za-z ]+$")
    @Column(name = "nom", length = 60, nullable = false)
    private String nom;

    @Size(max = 2000)
    @Column(name = "description", length = 2000)
    private String description;

    @Max(value = 90L)
    @Column(name = "max_services")
    private Long maxServices;

    @Max(value = 9999L)
    @Column(name = "max_kil")
    private Long maxKil;

    @Column(name = "is_bloque")
    private Boolean isBloque;

    @OneToMany(mappedBy = "pack")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contrat> contrats = new HashSet<>();

    @ManyToMany(cascade=CascadeType.PERSIST)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ref_pack_type_service",
               joinColumns = @JoinColumn(name="ref_packs_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="type_services_id", referencedColumnName="id"))
    private Set<RefTypeService> typeServices = new HashSet<>();

    @ManyToMany(mappedBy = "packs")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RefCompagnie> compagnies = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public RefPack nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public RefPack description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMaxServices() {
        return maxServices;
    }

    public RefPack maxServices(Long maxServices) {
        this.maxServices = maxServices;
        return this;
    }

    public void setMaxServices(Long maxServices) {
        this.maxServices = maxServices;
    }

    public Long getMaxKil() {
        return maxKil;
    }

    public RefPack maxKil(Long maxKil) {
        this.maxKil = maxKil;
        return this;
    }

    public void setMaxKil(Long maxKil) {
        this.maxKil = maxKil;
    }

    public Boolean isIsBloque() {
        return isBloque;
    }

    public RefPack isBloque(Boolean isBloque) {
        this.isBloque = isBloque;
        return this;
    }

    public void setIsBloque(Boolean isBloque) {
        this.isBloque = isBloque;
    }

    public Set<Contrat> getContrats() {
        return contrats;
    }

    public RefPack contrats(Set<Contrat> contrats) {
        this.contrats = contrats;
        return this;
    }

    public RefPack addContrat(Contrat contrat) {
        this.contrats.add(contrat);
        contrat.setPack(this);
        return this;
    }

    public RefPack removeContrat(Contrat contrat) {
        this.contrats.remove(contrat);
        contrat.setPack(null);
        return this;
    }

    public void setContrats(Set<Contrat> contrats) {
        this.contrats = contrats;
    }

    public Set<RefTypeService> getTypeServices() {
        return typeServices;
    }

    public RefPack typeServices(Set<RefTypeService> refTypeServices) {
        this.typeServices = refTypeServices;
        return this;
    }

    public RefPack addTypeService(RefTypeService refTypeService) {
        this.typeServices.add(refTypeService);
        refTypeService.getPacks().add(this);
        return this;
    }

    public RefPack removeTypeService(RefTypeService refTypeService) {
        this.typeServices.remove(refTypeService);
        refTypeService.getPacks().remove(this);
        return this;
    }

    public void setTypeServices(Set<RefTypeService> refTypeServices) {
        this.typeServices = refTypeServices;
    }

    public Set<RefCompagnie> getCompagnies() {
        return compagnies;
    }

    public RefPack compagnies(Set<RefCompagnie> refCompagnies) {
        this.compagnies = refCompagnies;
        return this;
    }

    public RefPack addCompagnie(RefCompagnie refCompagnie) {
        this.compagnies.add(refCompagnie);
        refCompagnie.getPacks().add(this);
        return this;
    }

    public RefPack removeCompagnie(RefCompagnie refCompagnie) {
        this.compagnies.remove(refCompagnie);
        refCompagnie.getPacks().remove(this);
        return this;
    }

    public void setCompagnies(Set<RefCompagnie> refCompagnies) {
        this.compagnies = refCompagnies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RefPack refPack = (RefPack) o;
        if (refPack.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refPack.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefPack{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", maxServices='" + getMaxServices() + "'" +
            ", maxKil='" + getMaxKil() + "'" +
            ", isBloque='" + isIsBloque() + "'" +
            "}";
    }
}
