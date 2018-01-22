package org.ilis.dao;

import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ilis.entities.association;
import org.ilis.entities.benevole;
import org.ilis.entities.experience;
import org.ilis.entities.mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
public class MissionRestService {
	@Autowired
	private MissionRepository missionRepository;
	@Autowired
	private AssociationRepository associationRepository;
	
	@Autowired
	private BenevoleRepository benevoleRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	
	@RequestMapping(value="/ListMissions",method=RequestMethod.GET)
	public List<mission> listAssociation()
	{
		return missionRepository.findAll();
	}
	
	@RequestMapping(value="/chercherMissions",method=RequestMethod.GET)
	public Page<mission> ChercherAssoc(String mc,
			
		@RequestParam(name="page",defaultValue="0")int page,
		@RequestParam(name="size",defaultValue="3")int size)
	{
		if(mc=="")
		{
			System.out.println("********** ouii c null");
			return missionRepository.findAll(new PageRequest(page,4));
		}
		else
		{
			System.out.println("********** noon ce n'est pas NULL");
			return missionRepository.chercherMission("%"+mc+"%",new PageRequest(page,size));
		}
	}
	
	@RequestMapping(value="/missions/{id}",method=RequestMethod.GET)
	public mission getMission(@PathVariable ("id") Long id)
	{
		return missionRepository.findOne(id);
	}
	
	@RequestMapping(value="/missionSave/{id}",method=RequestMethod.POST)
	public mission save(@PathVariable Long id,@RequestBody mission m)
	{
		System.out.println("********** ENTER **************");
		association ass=associationRepository.findOne(id);
		System.out.println("*******nom "+ass.getNom()+"**************");
		ass.getMissions().add(m);
		for(mission ms:ass.getMissions())
			System.out.println("***** Mission ==== "+ms.getDescription());
		m.setNbrParticip(0);
		/*m.setAssociation(ass);
		for(mission ms:ass.getMissions())
			System.out.println("***** Mission ==== "+ms.getDescription());*/
		System.out.println("nom = "+m.getNom()+" "+m.getDescription());
		return missionRepository.save(m);
	}

	
	
	
	@RequestMapping(value="/missionModify/{id}",method=RequestMethod.PUT)
	public mission update(@RequestBody mission m,@PathVariable Long id)
	{
		mission current_miss=missionRepository.findOne(id);
		current_miss.setId_m(id);

		current_miss.setNom(m.getNom());
		current_miss.setDate(m.getDate());
		current_miss.setDescription(m.getDescription());
		current_miss.setDomaine(m.getDomaine());
		current_miss.setImag(m.getImag());
		current_miss.setLieu(m.getLieu());
		current_miss.setNbrParticip(m.getNbrParticip());
		current_miss.setDomaine(m.getDomaine());
		return missionRepository.saveAndFlush(current_miss);
	}
	
	/*@RequestMapping(value="/deleteMission/{id_ass}/{id_miss}",method=RequestMethod.DELETE)
	public void delete(@PathVariable ("id_ass") Long id,@PathVariable ("id_miss") Long idM)
	{
		 association ass=associationRepository.findOne(id);
		 for(mission ms:ass.getMissions())
		  {
			if(ms.getId_m().equals(idM))
				missionRepository.delete(idM);
		  }
	}	*/
	
	@RequestMapping(value="/deleteMission/{id_miss}",method=RequestMethod.DELETE)
	public void deleteM(@PathVariable ("id_miss") Long idM)
	{
		 
				missionRepository.delete(idM);
	}	
	
	
	
	@RequestMapping(value="/ToutesMissions",method=RequestMethod.GET)
	public Page<mission> ToutesMissions(
			@RequestParam(name="page",defaultValue="0")int page,
	        @RequestParam(name="size",defaultValue="3")int size)
	{
		return missionRepository.AllMiss(new PageRequest(page,size));
	}
	
	@RequestMapping(value="/MissionBenevoles/{id}",method=RequestMethod.GET)
	public List<benevole> MissionBenevoles(@PathVariable ("id") Long idM)
	{
		mission m=missionRepository.findOne(idM);
		for(benevole b:m.getBenevoles())
			System.out.println("=====> "+b.getNom());
		return (List<benevole>) m.getBenevoles();
	}
	
	
	@RequestMapping(value="/accepterBenevoleMiss/{id_miss}",method=RequestMethod.PUT)
	public benevole accepterBenevole(@PathVariable ("id_miss") Long idM,@RequestBody benevole b)
	{
		b.setEstBenevole(1);
		benevoleRepository.save(b);
		mission m=missionRepository.findOne(idM);
		System.out.println("Mission est ["+m.getId_m()+"]");
		for(mission ms:b.getMissions())
		{
			if(ms.getId_m().equals(m.getId_m()))
			{
				m.setNbrParticip(m.getNbrParticip()+1);
				System.out.println("===============>"+m.getNbrParticip());
				missionRepository.save(m);
			}
		}
		return benevoleRepository.save(b);
	}
	
	
	@RequestMapping(value="/refuserBenevoleMiss/{id_miss}",method=RequestMethod.PUT)
	public benevole refuserBenevole(@PathVariable ("id_miss") Long idM,@RequestBody benevole b)
	{
		mission m=missionRepository.findOne(idM);
		benevole bene=benevoleRepository.findOne(b.getId_b());
		//System.out.println("Mission est ["+m.getId_m()+"]");
		//System.out.println("Mission est ["+b.getId_b()+"]");
		for(mission ms:bene.getMissions())
		{
			//System.out.println("avant --->"+ms.getNom());
			if(ms.getId_m()==m.getId_m())
			{
				 bene.getMissions().remove(ms);
				 ms.getBenevoles().remove(bene);
				 missionRepository.save(ms);
				 return benevoleRepository.save(bene);
			}
			//System.out.println("avant --->"+ms.getNom());
		}
		
		//b.getMissions().remove(missionRepository.findOne(idM));
		 missionRepository.save(m);
		 return benevoleRepository.save(bene);

		/*m.getBenevoles().remove(b);
		missionRepository.save(m);*/
		
	}
	
	
	@RequestMapping(value="/missionEstSaturee",method=RequestMethod.GET)
	public List<mission> estSaturee()
	{
		List<mission> resu=new ArrayList<mission>();
		List<mission> missions=(List<mission>) missionRepository.findAll();
		for(mission m:missions)
		{
			if(m.getNbrParticip() == m.getNbrParticipFix())
				resu.add(m);
		}
		for(mission m:resu)
		{
			System.out.println("Resu 2 === "+m.getNom());
		}
		return resu;
	}
	
	@RequestMapping(value="/MesMissionsParticiper/{id_b}",method=RequestMethod.GET)
	public List<mission> MyMissionParticip(@PathVariable ("id_b") Long id)
	{
		boolean Exist=false;
		benevole b=benevoleRepository.findOne(id);
		List<mission> resu=new ArrayList<mission>();
		List<mission> missions=(List<mission>) b.getMissions();
		if(b.getEstBenevole() == 1)
		{
			for(mission m:missions)
			{
						resu.add(m);
			}
		}
		
		return resu;
	}
	
	
	public boolean missionSaturee(mission m)
	{
		if((m.getNbrParticip()==m.getNbrParticipFix() && m.getNbrParticip()!=0) || (m.getNbrParticip() > m.getNbrParticipFix()) )
			return true;
		return false;
	}
	
	@RequestMapping(value="/DomainesMissions",method=RequestMethod.GET)
	public Map<String, Long> ListDomaineMission()
	{
		//ArrayList<String> domaines=new ArrayList<String>();
		 List<Object[]> l = em.createQuery(
			        "SELECT m.domaine, COUNT(m)"
			        + "FROM mission m"
			        + " GROUP BY m.domaine")
			        .getResultList();
		 Map<String, Long> resultMap = new HashMap<String, Long>(l.size());
		 for (Object[] result : l)
		   resultMap.put((String)result[0], (Long)result[1]);
		 for(String c:resultMap.keySet())
		 {
			 System.out.println("*******mission---> "+c.toString());
		 }
		 for(Long c:resultMap.values())
		 {
			 System.out.println("*******Number---> "+c);
		 }
		return resultMap;
	}
	
	
	@RequestMapping(value="/VillesMissions",method=RequestMethod.GET)
	public Map<String, Long> ListVilleMission()
	{
		//ArrayList<String> domaines=new ArrayList<String>();
		 List<Object[]> l = em.createQuery(
			        "SELECT m.lieu, COUNT(m)"
			        + "FROM mission m"
			        + " GROUP BY m.lieu")
			        .getResultList();
		 Map<String, Long> resultMap = new HashMap<String, Long>(l.size());
		 for (Object[] result : l)
		   resultMap.put((String)result[0], (Long)result[1]);
		 for(String c:resultMap.keySet())
		 {
			 System.out.println("*******ville---> "+c.toString());
		 }
		 for(Long c:resultMap.values())
		 {
			 System.out.println("*******Number---> "+c);
		 }
		return resultMap;
	}
	
	@RequestMapping(value="/BenevoleSexe",method=RequestMethod.GET)
	public Map<String, Long> sexeBenevole()
	{
		//ArrayList<String> domaines=new ArrayList<String>();
		 List<Object[]> l = em.createQuery(
			        "SELECT m.sexe, COUNT(m)"
			        + "FROM benevole m"
			        + " GROUP BY m.sexe")
			        .getResultList();
		 Map<String, Long> resultMap = new HashMap<String, Long>(l.size());
		 for (Object[] result : l)
		   resultMap.put((String)result[0], (Long)result[1]);
		 for(String c:resultMap.keySet())
		 {
			 System.out.println("*******sexe---> "+c.toString());
		 }
		 for(Long c:resultMap.values())
		 {
			 System.out.println("*******Number---> "+c);
		 }
		return resultMap;
	}
	
	
	
	
	public int getAge(Date dateOfBirth) {
	    int age = 0;
	    Calendar born = Calendar.getInstance();
	    Calendar now = Calendar.getInstance();
	    if(dateOfBirth!= null) {
	        now.setTime(new Date());
	        born.setTime(dateOfBirth);  
	        if(born.after(now)) {
	            throw new IllegalArgumentException("Can't be born in the future");
	        }
	        age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);             
	        if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
	            age-=1;
	        }
	    }  
	    return age;
	}
	
	
	
	
	
	
	
	@RequestMapping(value="/BenevoleAge",method=RequestMethod.GET)
	public Map<Long, Long> ageBenevole()
	{
		 List<Object[]> l = em.createQuery(
			        "SELECT m.date, COUNT(m)"
			        + "FROM benevole m"
			        + " GROUP BY m.date")
			        .getResultList();
		 Map<Date, Long> resultMap = new HashMap<Date, Long>(l.size());
		 Map<Long, Long> resultMapAge = new HashMap<Long, Long>(l.size());
		 for (Object[] result : l)
		   resultMap.put((Date)result[0], (Long)result[1]);
		 for(Date c:resultMap.keySet())
		 { 
			long ag=this.getAge(c);
			resultMapAge.put(ag,resultMap.get(c));
			 
		 }
		 for(Long c:resultMapAge.keySet())
		 {
			 System.out.println("*******Age---> "+c);
		 }
		return resultMapAge;
	}
	
	
	
}
