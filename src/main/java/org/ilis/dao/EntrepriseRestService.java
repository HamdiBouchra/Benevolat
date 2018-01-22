package org.ilis.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ilis.entities.admin;
import org.ilis.entities.association;
import org.ilis.entities.benevole;
import org.ilis.entities.entreprise;
import org.ilis.entities.mission;
import org.ilis.entities.salarie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@CrossOrigin("*")
public class EntrepriseRestService {
	@Autowired
	private AssociationRepository associationRepository;
	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	
	
	private SendEmail email=new SendEmail();
	
	
	@RequestMapping(value="/ListEntreprises",method=RequestMethod.GET)
	public List<entreprise> listEntreprise()
	{
		return entrepriseRepository.findAll();
	}
	
	
	@RequestMapping(value="/entreprises/{id}",method=RequestMethod.GET)
	public entreprise getEntreprise(@PathVariable ("id") Long id)
	{
		return entrepriseRepository.findOne(id);
	}
	
	

	
	@RequestMapping(value="/entrepriseSave",method=RequestMethod.POST)
	public entreprise save(@RequestBody entreprise entrep)
	{
		return entrepriseRepository.save(entrep);
	}
	
	
	/********************* authentification ********************/
	
	@RequestMapping(value="/authEntrep",method=RequestMethod.POST)
	public boolean authentifier(@RequestBody  entreprise e)
	{
		
		List<entreprise> listeEntrep=listEntreprise();
		for(entreprise entrep:listeEntrep)
		{
			if((e.getLogin().equals(entrep.getLogin())) && (e.getMdp().equals(entrep.getMdp())))
			{
				System.out.println("***** YEEEES *****");
				return true;
			}			  
		}
		return false;
	}
	
	
	@RequestMapping(value="/EntrepByLogMdp",method=RequestMethod.GET)
	public entreprise getEntrepriseByLogMdp(String log,String mdp)
	{

		entreprise es=entrepriseRepository.EntrepByLogMdp(log,mdp);
	    System.out.println("L'entreprise connectée =====>"+es.getNom());
		return es;
	}
	
	

	
		
}
