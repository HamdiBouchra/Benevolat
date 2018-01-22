package org.ilis.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class admin implements Serializable{
	@Id @GeneratedValue
	private Long id;
	private String login,mdp;
	
	
	
	public admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public admin(String login, String mdp) {
		super();
		this.login = login;
		this.mdp = mdp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
