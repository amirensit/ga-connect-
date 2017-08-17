package com.gaConnecte.assistAuto.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;



@Entity
public class Pack implements Serializable {
	
	
	@Id
	@GeneratedValue
	private Long id_pack;
	
	
	
	private String nom_pack;
	
	
	private String description;
	
	private int nbre_max_service;
	
	private int kilometrage_max;
	
	
	private Boolean etat;
	
	
	@OneToMany(mappedBy="pack",fetch=FetchType.LAZY)
	private List<Contrat> contrats ;
	
	
	
	
	
	public List<Contrat> getContrats() {
		return contrats;
	}

	
	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	
	public Boolean getEtat() {
		return etat;
	}

	
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}

	
	public Pack(String nom_pack, String description, int nbre_max_service, int kilometrage_max) {
		super();
		this.nom_pack = nom_pack;
		this.description = description;
		this.nbre_max_service = nbre_max_service;
		this.kilometrage_max = kilometrage_max;
		this.etat=true;
	}

	
	
	
	@ManyToMany(mappedBy="packs",cascade=CascadeType.REMOVE)
	private List<Client>clients;

	
	public Pack() {
		super();
		this.etat=true;
	}

	
	public Pack(String nom_pack, String description, int nbre_max_service, int kilometrage_max, List<Client> clients) {
		super();
		this.nom_pack = nom_pack;
		this.description = description;
		this.nbre_max_service = nbre_max_service;
		this.kilometrage_max = kilometrage_max;
		this.clients = clients;
		this.etat=true;
	}

	
	public Long getId_pack() {
		return id_pack;
	}

	
	public void setId_pack(Long id_pack) {
		this.id_pack = id_pack;
	}

	
	public String getNom_pack() {
		return nom_pack;
	}

	
	public void setNom_pack(String nom_pack) {
		this.nom_pack = nom_pack;
	}

	
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	
	public int getNbre_max_service() {
		return nbre_max_service;
	}

	
	public void setNbre_max_service(int nbre_max_service) {
		this.nbre_max_service = nbre_max_service;
	}

	
	public int getKilometrage_max() {
		return kilometrage_max;
	}

	
	public void setKilometrage_max(int kilometrage_max) {
		this.kilometrage_max = kilometrage_max;
	}

	
	public List<Client> getClients() {
		return clients;
	}

	
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	
	
	

}