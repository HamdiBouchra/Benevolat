package org.ilis.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class loisir {

	@Id @GeneratedValue
	private Long id_lois;
	private String descript;
	
	@ManyToMany(mappedBy="loisirs")
	Collection<salarie> listSalar;

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Long getId_lois() {
		return id_lois;
	}

	public void setId_lois(Long id_lois) {
		this.id_lois = id_lois;
	}

	public Collection<salarie> getListSalar() {
		return listSalar;
	}

	public void setListSalar(Collection<salarie> listSalar) {
		this.listSalar = listSalar;
	}

	public loisir() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
