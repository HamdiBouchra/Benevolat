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
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import net.minidev.json.annotate.JsonIgnore;


@Entity
public class benevole implements Serializable {
	
	@Id @GeneratedValue
	private Long id_b;
    private String nom;
	private String prenom;
	private Date date;
	private String Email;
	private String tel;
	private String sexe;
	private String situationF;
	private String login;
	private String mdp;
	private int estBenevole;
	
	@ManyToMany(mappedBy="benevoles")
	Collection<mission> missions;
	
	
	@OneToMany
	//@JsonIgnoreProperties("b")
	Collection<experience> experiences;
	
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Collection<experience> getExperiences() {
		return experiences;
	}

	@com.fasterxml.jackson.annotation.JsonIgnore
	public void setExperiences(Collection<experience> experiences) {
		this.experiences = experiences;
	}


	public benevole() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public benevole(String nom, String prenom, Date date, String email,
			String tel, String sexe, String situationF, String login,
			String mdp) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		Email = email;
		this.tel = tel;
		this.sexe = sexe;
		this.situationF = situationF;
		this.login = login;
		this.mdp = mdp;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Long getId_b() {
		return id_b;
	}
	public void setId_b(Long id_b) {
		this.id_b = id_b;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getSituationF() {
		return situationF;
	}
	public void setSituationF(String situationF) {
		this.situationF = situationF;
	}
	
	public Collection<mission> getMissions() {
		return missions;
	}
	
	public void setMissions(Collection<mission> missions) {
		this.missions = missions;
	}

	public int getEstBenevole() {
		return estBenevole;
	}

	public void setEstBenevole(int estBenevole) {
		this.estBenevole = estBenevole;
	}

	

	
	
	
	
	
	
	

}
