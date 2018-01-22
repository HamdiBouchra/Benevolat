package org.ilis.dao;

import java.util.ArrayList;
import java.util.List;

import org.ilis.entities.association;
import org.ilis.entities.benevole;
import org.ilis.entities.mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class BenevoleRestService {

	@Autowired
	private BenevoleRepository benevoleRepository;
	
	@Autowired
	private MissionRepository missionRepository;
	
	private SendEmail email=new SendEmail();
	
	@RequestMapping(value="/benevoleSave",method=RequestMethod.POST)
	public benevole save(@RequestBody benevole a)
	{
		a.setEstBenevole(0);
		return benevoleRepository.save(a);
	}
	
	@RequestMapping(value="/ListBenevoles",method=RequestMethod.GET)
	public List<benevole> listBenevole()
	{
		return benevoleRepository.findAll();
	}
	
	@RequestMapping(value="/authben",method=RequestMethod.POST)
	public boolean authentifierBenevole(@RequestBody  benevole b)
	{
		System.out.println("avant");
		List<benevole> listeben=listBenevole();
		for(benevole bnn:listeben)
			System.out.println("******** => "+bnn.getNom());
		System.out.println("apres");
		for(benevole bn:listeben)
		{
			if((bn.getLogin().equals(b.getLogin())) && (bn.getMdp().equals(b.getMdp())))
				return true;
		}
		return false;
	}
	
	@RequestMapping(value="/BenByLogMdp",method=RequestMethod.GET)
	public benevole getAssociationByLogMdp(String log,String mdp)
	{
		return benevoleRepository.benByLogMdp(log, mdp);
	}
	
	
	@RequestMapping(value="/benevole/{id}",method=RequestMethod.GET)
	public benevole getAssociation(@PathVariable ("id") Long id)
	{
		return benevoleRepository.findOne(id);
	}
	
	@RequestMapping(value="/EmailBenevole",method=RequestMethod.POST)
	public void sendMail(@RequestBody benevole bnv)
	{
		 List<String> a = new ArrayList<String>();
    	 a.add(bnv.getEmail());
    	 System.out.println("mail ==== "+bnv.getEmail());
	     try {
			email.sendMailWithAuth("smtp.gmail.com", "salwa.boudali.sb@gmail.com", "Saranghae","587", a, "confirmation de participation ", "Veuillez "
					+ "confirmer votre participation à la mission proposée");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/missionBenevoleParticiper/{id}",method=RequestMethod.POST)
	public mission participer(@PathVariable Long id,@RequestBody mission m)
	{
	    System.out.println("********** ENTER **************");
		benevole b=benevoleRepository.findOne(id);
		System.out.println("*******nom "+b.getEmail()+"**************");
		if(b.getMissions()==null)
			b.setMissions(new ArrayList<mission>());
		b.getMissions().add(m);
		if(m.getBenevoles()==null)
			m.setBenevoles(new ArrayList<benevole>());
		m.getBenevoles().add(b);
		missionRepository.save(m);
		benevoleRepository.save(b);
		
		for(benevole bnv:m.getBenevoles())
			System.out.println("***** Mission ==== "+bnv.getNom());
		this.sendMail(b);
		return m;
	}
	
	@RequestMapping(value="/estBenevMiss/{id}",method=RequestMethod.GET)
	public List<mission> MissionBenevole(@PathVariable Long id)
	{
		List<mission> resu=new ArrayList<mission>();
		benevole b=benevoleRepository.findOne(id);
		List<mission> missions=(List<mission>) b.getMissions();
		List<mission> missions1=missionRepository.findAll();
		for(mission m:missions1)
		{
			for(mission ms:missions)
			{
				if(ms.getId_m()==m.getId_m())
					resu.add(ms);
			}
		}
		for(mission m:resu)
		{
			System.out.println("Resu : "+m.getNom());
		}
		return resu;
	}
	
	
	@RequestMapping(value="/bnvAcceptMission/{id}",method=RequestMethod.GET)
	public List<benevole> benevoleMissionAccepte(@PathVariable ("id") Long id)
	{
		mission m=missionRepository.findOne(id);
		List<benevole> benevoles=(List<benevole>) m.getBenevoles();
		List<benevole> resu=new ArrayList<benevole>();
		if(benevoles != null)
		{
			for(benevole b:benevoles)
			{
				System.out.println("AVANT");
				if(b.getEstBenevole() == 1)
					resu.add(b);	
				System.out.println("APRES");
			}
			for(benevole b:benevoles)
			{
				System.out.println("BENEVOLE =====> "+b.getNom());
			}
		}	
		return resu;
	}
	
	
	
}
