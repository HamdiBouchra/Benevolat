package org.ilis.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class entreprise implements Serializable{

	@Id @GeneratedValue
	private Long id_entr;
	private String nom,sigle;
	private String domaine_activ,secteur,tel,email;
	private String login,mdp;
	
	@OneToMany
	  private Collection<salarie> salaries;
	
	  
	  
	public entreprise() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public entreprise(String nom, String sigle,
			String domaine_activ, String secteur, String tel, String email,
			String login, String mdp) {
		super();
		this.nom = nom;
		this.sigle = sigle;
		this.domaine_activ = domaine_activ;
		this.secteur = secteur;
		this.tel = tel;
		this.email = email;
		this.login = login;
		this.mdp = mdp;
	}

	public Long getId_entr() {
		return id_entr;
	}
	public void setId_entr(Long id_entr) {
		this.id_entr = id_entr;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getSigle() {
		return sigle;
	}
	public void setSigle(String sigle) {
		this.sigle = sigle;
	}
	public String getDomaine_activ() {
		return domaine_activ;
	}
	public void setDomaine_activ(String domaine_activ) {
		this.domaine_activ = domaine_activ;
	}
	public String getSecteur() {
		return secteur;
	}
	public void setSecteur(String secteur) {
		this.secteur = secteur;
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
	@com.fasterxml.jackson.annotation.JsonIgnore
	public Collection<salarie> getSalaries() {
		return salaries;
	}
	@com.fasterxml.jackson.annotation.JsonIgnore
	public void setSalaries(Collection<salarie> salaries) {
		this.salaries = salaries;
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
	
	
	
	
    
}
