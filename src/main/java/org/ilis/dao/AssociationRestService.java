package org.ilis.dao;

import java.util.ArrayList;
import java.util.List;

import org.ilis.entities.admin;
import org.ilis.entities.association;
import org.ilis.entities.mission;
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

@RestController
@CrossOrigin("*")
public class AssociationRestService {
	@Autowired
	private AssociationRepository associationRepository;
	@Autowired
	private MissionRepository missionRepository;
	
	private SendEmail email=new SendEmail();
	
	
	@RequestMapping(value="/ListAssociations",method=RequestMethod.GET)
	public List<association> listAssociation()
	{
		return associationRepository.findAll();
	}
	
	/*@RequestMapping(value="/associations",method=RequestMethod.GET)
	public Page<association> listAssociation(int page,int size)
	{
		return associationRepository.findAll(new PageRequest(page, size));
	}*/
		
	@RequestMapping(value="/chercherAssociations",method=RequestMethod.GET)
	public Page<association> ChercherAssoc(String mc,
			
		@RequestParam(name="page",defaultValue="0")int page,
		@RequestParam(name="size",defaultValue="2")int size)
	{
		if(mc=="")
		{
			System.out.println("********** ouii c null");
			return associationRepository.findAll(new PageRequest(page,4));
		}
		else
		{
			System.out.println("********** noon ce n'est pas NULL");
			return associationRepository.chercherAssociation("%"+mc+"%",new PageRequest(page,size));
		}
	}
	@RequestMapping(value="/AssociationEtatAtt",method=RequestMethod.GET)
	public Page<association> AssEtatAtt(String etat,
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="size",defaultValue="4")int size)
	{
		int eta=Integer.parseInt(etat);		
		return associationRepository.AssAtt(eta,new PageRequest(page,size));
	}
	
	
	@RequestMapping(value="/AssociationEtatVld",method=RequestMethod.GET)
	public List<association> AssEtatVld(String etat)
	{
		int eta=Integer.parseInt(etat);		
		return associationRepository.AssVld(eta);
	}
	
	
	@RequestMapping(value="/associations/{id}",method=RequestMethod.GET)
	public association getAssociation(@PathVariable ("id") Long id)
	{
		return associationRepository.findOne(id);
	}
	
	
	public association getAssociati(Long id)
	{
		return associationRepository.findOne(id);
	}
	
	@RequestMapping(value="/associationSave",method=RequestMethod.POST)
	public association save(@RequestBody association a)
	{
		System.out.println("nom = "+a.getNom()+" "+a.getLogin());
		return associationRepository.save(a);
	}
	
	@RequestMapping(value="/associationsModify/{id}",method=RequestMethod.PUT)
	public association update(@RequestBody association a,@PathVariable Long id)
	{
		association current_assoc=associationRepository.findOne(id);
		current_assoc.setId(id);

		current_assoc.setNom(a.getNom());
		current_assoc.setEmail(a.getEmail());
		current_assoc.setTel(a.getTel());
		current_assoc.setSigle(a.getSigle());
		current_assoc.setLogin(a.getLogin());
		current_assoc.setMdp(a.getMdp());
		current_assoc.setObjet_social(a.getObjet_social());
		current_assoc.setEtat(a.getEtat());
		return associationRepository.save(current_assoc);
	}
	
	@RequestMapping(value="/associations/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable ("id") Long id)
	{
		 associationRepository.delete(id);
	}
	
	/********************* authentification ********************/
	
	@RequestMapping(value="/auth",method=RequestMethod.POST)
	public boolean authentifier(@RequestBody  admin ad)
	{
		if(ad.getLogin().equals("admin") && ad.getMdp().equals("admin"))
			return true;
		return false;
	}
	
	@RequestMapping(value="/MissionsAssociationAdd",method=RequestMethod.POST)
	public void ajouter_missions_association(@RequestBody  association ass,@RequestBody  mission m)
	{
		ass.getMissions().add(m);
	}
	
	
	@RequestMapping(value="/authAsso",method=RequestMethod.POST)
	public boolean authentifierAssoc(@RequestBody  association ass)
	{
		List<association> listeAssocAtt=AssEtatVld("1");
		for(association asso:listeAssocAtt)
		{
			if((asso.getLogin().equals(ass.getLogin())) && (asso.getMdp().equals(ass.getMdp())))
				return true;
		}
		return false;
	}
	
	@RequestMapping(value="/AssByLogMdp",method=RequestMethod.GET)
	public association getAssociationByLogMdp(String log,String mdp)
	{
		return associationRepository.AssByLogMdp(log,mdp);
	}
	
	@RequestMapping(value="/ListMissions/{id}",method=RequestMethod.GET)
	public List<mission> listMissions(@PathVariable Long id)
	{
		association ass=associationRepository.findOne(id);
		
		return (List<mission>) ass.getMissions();
	}
	

	
	@RequestMapping(value="/Email",method=RequestMethod.POST)
	public void sendMail(@RequestBody association ass)
	{
		 List<String> a = new ArrayList<String>();
    	 a.add(ass.getEmail());
    	 System.out.println("mail ==== "+ass.getEmail());
	     try {
			email.sendMailWithAuth("smtp.gmail.com", "salwa.boudali.sb@gmail.com", "Saranghae","587", a, "Votre demande d'inscription s'est bien effectué ", "Réponse d'inscription");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/GetAssocMiss/{id}",method=RequestMethod.GET)
	public association getAssocMiss(@PathVariable Long id)
	{
		mission m=missionRepository.findOne(id);
		ArrayList<association> assocs=new ArrayList<association>();
		assocs=(ArrayList<association>) associationRepository.findAll();
		for(association ass:assocs)
		{
			if(ass.getMissions().contains(m))
				return ass;
		}
		return null;
	}
	
		
}
