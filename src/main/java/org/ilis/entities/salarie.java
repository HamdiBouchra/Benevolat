package org.ilis.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class salarie implements Serializable{

	@Id @GeneratedValue
	private Long id_sal;
	private String nom,prenom;
	private String tel,email;
	private Date date;
	
	
	@ManyToOne
	entreprise entr;
	
	@ManyToMany
	@JsonIgnoreProperties("listSalar")
	Collection<loisir> loisirs;
    
	
	
	@ManyToMany(mappedBy="listSalaries")
	//@JsonIgnoreProperties("listSalaries")
	Collection<mission> Listmissions;
	
	
	
	
	
	public salarie(String nom, String prenom, String tel, String email,
			Date date, entreprise entr, Collection<loisir> loisirs) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.tel = tel;
		this.email = email;
		this.date = date;
		this.entr = entr;
		this.loisirs = loisirs;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public salarie() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId_sal() {
		return id_sal;
	}
	public void setId_sal(Long id_sal) {
		this.id_sal = id_sal;
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

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public entreprise getEntr() {
		return entr;
	}
	public void setEntr(entreprise entr) {
		this.entr = entr;
	}
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Collection<loisir> getLoisirs() {
		return loisirs;
	}
	@com.fasterxml.jackson.annotation.JsonIgnore
	public void setLoisirs(Collection<loisir> loisirs) {
		this.loisirs = loisirs;
	}

	public Collection<mission> getListmissions() {
		return Listmissions;
	}

	public void setListmissions(Collection<mission> listmissions) {
		Listmissions = listmissions;
	}
	
	
	
		
}
