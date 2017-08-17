package com.gaConnecte.assistAuto.common.DTO;

import java.util.List;

public class RoleDTO {

private Integer id;
	
	
	private String libelle;
	
	 
	private List<ModuleDTO>modules;


	
	
	
	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLibelle() {
		return libelle;
	}


	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public List<ModuleDTO> getModules() {
		return modules;
	}


	public void setModules(List<ModuleDTO> modules) {
		this.modules = modules;
	}
	
	
}