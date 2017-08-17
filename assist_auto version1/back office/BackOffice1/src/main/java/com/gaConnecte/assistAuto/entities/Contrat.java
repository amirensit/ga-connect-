package com.gaConnecte.assistAuto.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class Contrat implements Serializable {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id_contrat;
	
	
	private Long num_contrat;
	
	
	private String nom;
	
	
	private String prenom;
	
	
	private String addresse;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_debut;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date date_fin;
	
	
	@ManyToOne
	@JoinColumn(name="CODE_MARQUE")
	private Marque marque;
	
	
	@ManyToOne
	@JoinColumn(name="CODE_Pack")
	private Pack pack;
	
	
	@ManyToOne
	@JoinColumn(name="CODE_VILLE")
	private Ville ville;

	
	public Contrat() {
		super();
	}

	
	public Contrat(Long id_contrat, Long num_contrat, String nom, String prenom, String addresse, Date date_debut,
			Date date_fin, Marque marque, Pack pack) {
		super();
		this.id_contrat = id_contrat;
		this.num_contrat = num_contrat;
		this.nom = nom;
		this.prenom = prenom;
		this.addresse = addresse;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.marque = marque;
		this.pack = pack;
	}

	
	public Contrat(Long num_contrat, String nom, String prenom, String addresse, Date date_debut, Date date_fin) {
		super();
		this.num_contrat = num_contrat;
		this.nom = nom;
		this.prenom = prenom;
		this.addresse = addresse;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}

	


	public Contrat(Long id_contrat, String nom, String prenom, String addresse, Date date_debut) {
		super();
		this.id_contrat = id_contrat;
		this.nom = nom;
		this.prenom = prenom;
		this.addresse = addresse;
		this.date_debut = date_debut;
	}
	



	
	public Long getId_contrat() {
		return id_contrat;
	}

	
	public void setId_contrat(Long id_contrat) {
		this.id_contrat = id_contrat;
	}

	
	public Long getNum_contrat() {
		return num_contrat;
	}

	
	public void setNum_contrat(Long num_contrat) {
		this.num_contrat = num_contrat;
	}

	
	public String getNom() {
		return nom;
	}

	
	public void setNom(String nom) {
		this.nom = nom;
	}

	
	public String getPrenom() {
		return prenom;
	}

	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	
	public String getAddresse() {
		return addresse;
	}

	
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	
	public Date getDate_debut() {
		return date_debut;
	}

	
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	
	public Date getDate_fin() {
		return date_fin;
	}

	
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	
	public Marque getMarque() {
		return marque;
	}

	
	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	
	public Pack getPack() {
		return pack;
	}

	
	public void setPack(Pack pack) {
		this.pack = pack;
	}

	
	public Ville getVille() {
		return ville;
	}

	
	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	
	
	




	
	
	

}
