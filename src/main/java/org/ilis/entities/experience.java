package org.ilis.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class experience implements Serializable {
	@Id @GeneratedValue
	private Long id_exp;
	private String satisfaction;
	private String benefique;
	private String remarque;
	private String estDejaBenevole;
	private String prb;
	
	@ManyToOne
	benevole b;
	
	@ManyToOne
	mission miss;
	
	public mission getMiss() {
		return miss;
	}
	public void setMiss(mission miss) {
		this.miss = miss;
	}
	public benevole getB() {
		return b;
	}
	public void setB(benevole b) {
		this.b = b;
	}
	public experience() {
		super();
		// TODO Auto-generated constructor stub
	}
	public experience(String satisfaction, String benefique, String remarque,
			String estDejaBenevole, String prb) {
		super();
		this.satisfaction = satisfaction;
		this.benefique = benefique;
		this.remarque = remarque;
		this.estDejaBenevole = estDejaBenevole;
		this.prb = prb;
	}
	public Long getId_exp() {
		return id_exp;
	}
	public void setId_exp(Long id_exp) {
		this.id_exp = id_exp;
	}
	public String getSatisfaction() {
		return satisfaction;
	}
	public void setSatisfaction(String satisfaction) {
		this.satisfaction = satisfaction;
	}
	public String getBenefique() {
		return benefique;
	}
	public void setBenefique(String benefique) {
		this.benefique = benefique;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public String getEstDejaBenevole() {
		return estDejaBenevole;
	}
	public void setEstDejaBenevole(String estDejaBenevole) {
		this.estDejaBenevole = estDejaBenevole;
	}
	public String getPrb() {
		return prb;
	}
	public void setPrb(String prb) {
		this.prb = prb;
	}

	
	

}
