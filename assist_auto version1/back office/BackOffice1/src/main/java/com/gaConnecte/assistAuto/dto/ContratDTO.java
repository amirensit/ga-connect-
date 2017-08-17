package com.gaConnecte.assistAuto.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ContratDTO {

	
	
	private Long id_contrat;
	
	
	private Long num_contrat;
	
	
	private String nom;
	
	
	private String prenom;
	
	
	private String addresse;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date date_debut;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date date_fin;
	
	
	private PackDTO packDTO;
	
	
	private MarqueDTO marqueDTO;
	
	
	private VilleDTO villeDTO;
	
	
	
	
	
	
	public VilleDTO getVilleDTO() {
		return villeDTO;
	}
	
	
	public void setVilleDTO(VilleDTO villeDTO) {
		this.villeDTO = villeDTO;
	}
	
	
	public Long getId_contrat() {
		return id_contrat;
	}
	
	
	public void setId_contrat(Long id_contrat) {
		this.id_contrat = id_contrat;
	}

	
	public MarqueDTO getMarqueDTO() {
		return marqueDTO;
	}
	
	
	public void setMarqueDTO(MarqueDTO marqueDTO) {
		this.marqueDTO = marqueDTO;
	}
	
	
	public PackDTO getPackDTO() {
		return packDTO;
	}
	
	
	public void setPackDTO(PackDTO packDTO) {
		this.packDTO = packDTO;
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
	
	
	
}
