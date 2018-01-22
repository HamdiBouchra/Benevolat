package org.ilis.dao;

import java.util.List;

import org.ilis.entities.entreprise;
import org.ilis.entities.salarie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SalarieRestService {
	@Autowired
	private AssociationRepository associationRepository;
	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private SalarieRepository salarieRepository;
	
	
	@RequestMapping(value="/ListSalarie",method=RequestMethod.GET)
	public List<salarie> listSalarie()
	{
		return salarieRepository.findAll();
	}
	
	@RequestMapping(value="/salaries/{id}",method=RequestMethod.GET)
	public salarie getSalarie(@PathVariable ("id") Long id)
	{
		return salarieRepository.findOne(id);
	}
	
	
	@RequestMapping(value="/salarieAdd/{id}",method=RequestMethod.POST)
	public entreprise ajouter_salarie_entreprise(@PathVariable Long id,@RequestBody  salarie s)
	{
		// Here i have to save the salary firstely ,than save entreprise
		System.out.println("1");
		entreprise e=entrepriseRepository.findOne(id);	
		s.setEntr(e);
		salarieRepository.save(s);
		e.getSalaries().add(s);

		return entrepriseRepository.save(e);
	}
}
