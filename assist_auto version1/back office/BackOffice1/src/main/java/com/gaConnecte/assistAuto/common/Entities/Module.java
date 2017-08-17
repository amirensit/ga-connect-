package com.gaConnecte.assistAuto.common.Entities;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;




@Entity
public class Module implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="id_module")
	private Integer id;
	
	@Pattern(regexp="^[A-Za-z ]+$")
@Length(max = 50)
private String typeModule;
	
	@Cascade({ CascadeType.DELETE ,CascadeType.SAVE_UPDATE})
	 @ManyToMany(mappedBy="modules")
	private List<Role>roles;
	
	
	


	





	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	


	public String getTypeModule() {
		return typeModule;
	}





	public void setTypeModule(String typeModule) {
		this.typeModule = typeModule;
	}





	public List<Role> getRoles() {
		return roles;
	}





	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}





	

	

	
	


	
	
	
	
	
	
	
	

}