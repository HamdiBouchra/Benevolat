package org.ilis.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.ilis.entities.benevole;
import org.ilis.entities.entreprise;
import org.ilis.entities.loisir;
import org.ilis.entities.mission;
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
public class LoisirRestService {
	@Autowired
	private MissionRepository missionRepository;
	@Autowired
	private LoisirRepository loisirRepository;
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private SalarieRepository salarieRepository;
	 private MissionRestService missionService=new MissionRestService();
	
	@RequestMapping(value="/ListLoisir",method=RequestMethod.GET)
	public List<loisir> listLoisir()
	{
		return loisirRepository.findAll();
	}
	
	
	@RequestMapping(value="/ListLoisir/{id}",method=RequestMethod.GET)
	public List<loisir> listLoisirSalarie(@PathVariable Long id)
	{
		System.out.println("*******Heeeere*****");
		List<loisir> salarLoisir=new ArrayList<loisir>();
		List<loisir> resu=new ArrayList<loisir>();
		salarie s=salarieRepository.findOne(id);
		
		if(s.getLoisirs().isEmpty())
		{
			 for(loisir ll:loisirRepository.findAll())
	        	      salarLoisir.add(ll);
		}
		
		else
		{
			for(loisir l:s.getLoisirs())
			{
				//System.out.println("----> L1"+l.getDescript());
	           for(loisir ll:loisirRepository.findAll())
	           {
	        	   if((l.getDescript().equals(ll.getDescript())==false) && (salarLoisir.contains(ll)==false) && 
	        			   (s.getLoisirs().contains(ll)==false))
	        	   {
	        		 // System.out.println("----> L2"+ll.getDescript()); 
	        	      salarLoisir.add(ll);
	        	   }
	           }
			}
		}
		
		return salarLoisir;
	}
	
	
	@RequestMapping(value="/LoisirSalarieAdd/{id}",method=RequestMethod.POST)
	public salarie ajouter_loisir_salarie(@PathVariable Long id,@RequestBody  loisir l)
	{
		// Here i have to save the salary firstely ,than save entreprise
		salarie sal=salarieRepository.findOne(id);
		//System.out.println("SALARIE ==== > "+sal.getNom());
		if(l.getListSalar()==null)
		  l.setListSalar(new ArrayList<salarie>());
		l.getListSalar().add(sal);
		if(sal.getLoisirs()==null)
		   sal.setLoisirs(new ArrayList<loisir>());
		sal.getLoisirs().add(l);
		   loisirRepository.save(l);
		return salarieRepository.save(sal);
	}
	
	@RequestMapping(value="/affectSalMiss/{id}",method=RequestMethod.GET)
	public ArrayList<mission> affectationMissionSalar(@PathVariable Long id)
	{
		salarie sal=salarieRepository.findOne(id);
		ArrayList<mission> miss=new ArrayList<>();
		for(loisir l:sal.getLoisirs())
		{
			if(l.getDescript().equals("Membre d'un club informatique"))
			{
				for(mission m:missionRepository.findAll())
				{
					if((m.getDomaine().equals("Informatique")) && (missionService.missionSaturee(m)==false)
							&&(sal.getListmissions().contains(m)==false))
						miss.add(m);
				}
			}
			
			if(l.getDescript().equals("Croissant rouge"))
			{
				for(mission m:missionRepository.findAll())
				{
					if((m.getDomaine().equals("Sante")) && (missionService.missionSaturee(m)==false)
							&&(sal.getListmissions().contains(m)==false))
					{
						System.out.println("YESSSS");
						miss.add(m);
					}
				}
			}	
			
			if((l.getDescript().equals("Création des aeuvres")) || (l.getDescript().equals("Théâtre"))
					|| (l.getDescript().equals("Dessin")))
			{
				System.out.println("YESSSS1");
				for(mission m:missionRepository.findAll())
				{
					if((m.getDomaine().equals("Soutien scolair")) && (missionService.missionSaturee(m)==false)
							&&(sal.getListmissions().contains(m)==false))
					{
						miss.add(m);
					}
				}
			}
		}
		return miss;	
	}
	
	@RequestMapping(value="/salLoisirs/{id}",method=RequestMethod.GET)
	public ArrayList<loisir> LoisirSalarie(@PathVariable Long id)
	{
		salarie sal=salarieRepository.findOne(id);
		ArrayList<loisir> loisirs=new ArrayList<>();
		for(loisir l:sal.getLoisirs())
		{
			loisirs.add(l);
		}
		return loisirs;
	}
	
	@RequestMapping(value="/AssocierMissionSal/{id}",method=RequestMethod.POST)
	public salarie AssocierSalMiss(@PathVariable Long id,@RequestBody mission miss)
	{
		salarie sal=salarieRepository.findOne(id);
		if(sal.getListmissions()==null)
			sal.setListmissions(new ArrayList<mission>());
		sal.getListmissions().add(miss);
		if(miss.getListSalaries()==null)
			miss.setListSalaries(new ArrayList<salarie>());
		miss.getListSalaries().add(sal);
		
		missionRepository.save(miss);
		return salarieRepository.save(sal);	
	}
	
	
	
	
}
