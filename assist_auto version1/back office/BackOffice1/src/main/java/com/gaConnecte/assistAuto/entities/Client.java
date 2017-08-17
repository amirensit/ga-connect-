package com.gaConnecte.assistAuto.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Columns;


@Entity
public class Client {
	
	@Id
	@GeneratedValue
	private Long id_client;
	
	//@Column(unique=true)
	private Long num_client;
	
	
	//@Pattern(regexp="^[A-Za-z ]+$")
	//@Size(max = 60)
	private String nom_client;
	private Boolean etat;
	
	
	@ManyToMany
	  @JoinTable(
	      name="CLIENT_PACK",
	      joinColumns=@JoinColumn(name="CLIENT_ID", referencedColumnName="id_client"),
	      inverseJoinColumns=@JoinColumn(name="PACK_ID", referencedColumnName="id_pack"))
	private List<Pack> packs;
	/**
	 * constructeur sans paramètre
	 *
	 */

	public Client() {
		super();
	}
	

	public Client(Long id_client, Long num_client, String nom_client) {
		super();
		this.id_client = id_client;
		this.num_client = num_client;
		this.nom_client = nom_client;
		this.etat=true;
	}


	public Client(String string) {
		super();
		this.nom_client=string;
		this.etat=true;
	}
	
	
	public Client(Long num_client, String nom_client) {
		super();
		this.num_client = num_client;
		this.nom_client = nom_client;
		this.etat=true;
	}


	public Client(Long num_client, String nom_client, Boolean etat) {
		super();
		this.num_client = num_client;
		this.nom_client = nom_client;
		this.etat = etat;
	}


	public Client(Long num_client, String nom_client, List<Pack> packs) {
		super();
		this.num_client = num_client;
		this.nom_client = nom_client;
		this.packs = packs;
		this.etat=true;
	}




	public Client(Long id_client, Long num_client, String nom_client, Boolean etat) {
		super();
		this.id_client = id_client;
		this.num_client = num_client;
		this.nom_client = nom_client;
		this.etat = etat;
	}

	
	public Boolean getEtat() {
		return etat;
	}


	public void setEtat(Boolean etat) {
		this.etat = etat;
	}


	public Long getId_client() {
		return id_client;
	}


	public void setId_client(Long id_client) {
		this.id_client = id_client;
	}


	public Long getNum_client() {
		return num_client;
	}


	public void setNum_client(Long num_client) {
		this.num_client = num_client;
	}


	public String getNom_client() {
		return nom_client;
	}


	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}


	public List<Pack> getPacks() {
		return packs;
	}


	public void setPacks(List<Pack> packs) {
		this.packs = packs;
	}


	
	
	
	
	
	
	
	
}
