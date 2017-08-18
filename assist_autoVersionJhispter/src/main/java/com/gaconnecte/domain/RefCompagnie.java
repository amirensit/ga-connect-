package com.gaconnecte.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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
 * A RefCompagnie.
 */
@Entity
@Table(name = "ref_compagnie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "refcompagnie")
public class RefCompagnie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * constraint unique to add later
     */
    @NotNull
    @ApiModelProperty(value = "constraint unique to add later", required = true)
    @Column(name = "numero", nullable = false)
    private Long numero;

    @NotNull
    @Size(max = 60)
    @Pattern(regexp = "^[A-Za-z ]+$")
    @Column(name = "nom", length = 60, nullable = false)
    private String nom;

    @Column(name = "is_bloque")
    private Boolean isBloque;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @OneToOne
    @JoinColumn(unique = true)
    private SysVille ville;

    @OneToMany(mappedBy = "compagnie")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contrat> contrats = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "ref_compagnie_pack",
               joinColumns = @JoinColumn(name="ref_compagnies_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="packs_id", referencedColumnName="id"))
    private Set<RefPack> packs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public RefCompagnie numero(Long numero) {
        this.numero = numero;
        return this;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public RefCompagnie nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean isIsBloque() {
        return isBloque;
    }

    public RefCompagnie isBloque(Boolean isBloque) {
        this.isBloque = isBloque;
        return this;
    }

    public void setIsBloque(Boolean isBloque) {
        this.isBloque = isBloque;
    }

    public String getAdresse() {
        return adresse;
    }

    public RefCompagnie adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public RefCompagnie latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public RefCompagnie longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public SysVille getVille() {
        return ville;
    }

    public RefCompagnie ville(SysVille sysVille) {
        this.ville = sysVille;
        return this;
    }

    public void setVille(SysVille sysVille) {
        this.ville = sysVille;
    }

    public Set<Contrat> getContrats() {
        return contrats;
    }

    public RefCompagnie contrats(Set<Contrat> contrats) {
        this.contrats = contrats;
        return this;
    }

    public RefCompagnie addContrat(Contrat contrat) {
        this.contrats.add(contrat);
        contrat.setCompagnie(this);
        return this;
    }

    public RefCompagnie removeContrat(Contrat contrat) {
        this.contrats.remove(contrat);
        contrat.setCompagnie(null);
        return this;
    }

    public void setContrats(Set<Contrat> contrats) {
        this.contrats = contrats;
    }

    public Set<RefPack> getPacks() {
        return packs;
    }

    public RefCompagnie packs(Set<RefPack> refPacks) {
        this.packs = refPacks;
        return this;
    }

    public RefCompagnie addPack(RefPack refPack) {
        this.packs.add(refPack);
        refPack.getCompagnies().add(this);
        return this;
    }

    public RefCompagnie removePack(RefPack refPack) {
        this.packs.remove(refPack);
        refPack.getCompagnies().remove(this);
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
        RefCompagnie refCompagnie = (RefCompagnie) o;
        if (refCompagnie.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), refCompagnie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RefCompagnie{" +
            "id=" + getId() +
            ", numero='" + getNumero() + "'" +
            ", nom='" + getNom() + "'" +
            ", isBloque='" + isIsBloque() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }
}
