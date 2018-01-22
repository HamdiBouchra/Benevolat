package org.ilis.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class association implements Serializable{
	
	@Id @GeneratedValue
	private Long id;
	private String nom,sigle,objet_social,tel,email;
	private String login,mdp;
	private int etat;
	private String doc;
	//( cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany
	  private Collection<mission> missions;
	
	
	public association() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public association(String nom, String sigle, String objet_social,
			String tel, String email, String login, String mdp,String doc) {
		super();
		this.nom = nom;
		this.sigle = sigle;
		this.objet_social = objet_social;
		this.tel = tel;
		this.email = email;
		this.login = login;
		this.mdp = mdp;
		this.doc=doc;
	}
	
	
	
	
	public Collection<mission> getMissions() {
		return missions;
	}
	public void setMissions(Collection<mission> missions) {
		this.missions = missions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getObjet_social() {
		return objet_social;
	}
	public void setObjet_social(String objet_social) {
		this.objet_social = objet_social;
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
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	
	
	

}
