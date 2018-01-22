package org.ilis.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.minidev.json.annotate.JsonIgnore;

@Entity
public class mission implements Serializable {
@Id @GeneratedValue
	private Long id_m;
    private String description;
	private String nom,domaine;
	private Date date;
	private int nbrParticip;
	private String lieu;
	private String imag;
	private int nbrParticipFix;
	@ManyToMany
	@JsonIgnoreProperties("missions")
	Collection<benevole> benevoles;
	

	
	@ManyToMany
	//@JsonIgnoreProperties("Listmissions")
	Collection<salarie> listSalaries;

	public Collection<benevole> getBenevoles() {
		return benevoles;
	}
	
	public void setBenevoles(Collection<benevole> benevoles) {
		this.benevoles = benevoles;
	}

	public mission() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getNbrParticipFix() {
		return nbrParticipFix;
	}

	public void setNbrParticipFix(int nbrParticipFix) {
		this.nbrParticipFix = nbrParticipFix;
	}

	


	public mission(String description, String nom, String domaine, Date date,
			int nbrParticip, String lieu, String imag, int nbrParticipFix) {
		super();
		this.description = description;
		this.nom = nom;
		this.domaine = domaine;
		this.date = date;
		this.nbrParticip = nbrParticip;
		this.lieu = lieu;
		this.imag = imag;
		this.nbrParticipFix = nbrParticipFix;
	}

	public Long getId_m() {
		return id_m;
	}
	public void setId_m(Long id_m) {
		this.id_m = id_m;
	}
	

	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDomaine() {
		return domaine;
	}
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public int getNbrParticip() {
		return nbrParticip;
	}
	public void setNbrParticip(int nbrParticip) {
		this.nbrParticip = nbrParticip;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getLieu() {
		return lieu;
	}




	public void setLieu(String lieu) {
		this.lieu = lieu;
	}




	public String getImag() {
		return imag;
	}




	public void setImag(String imag) {
		this.imag = imag;
	}

	@com.fasterxml.jackson.annotation.JsonIgnore
	public Collection<salarie> getListSalaries() {
		return listSalaries;
	}
	@com.fasterxml.jackson.annotation.JsonIgnore
	public void setListSalaries(Collection<salarie> listSalaries) {
		this.listSalaries = listSalaries;
	}

	
	
	
	
	

}
