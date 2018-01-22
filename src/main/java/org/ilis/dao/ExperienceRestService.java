package org.ilis.dao;

import java.util.List;

import org.ilis.entities.association;
import org.ilis.entities.benevole;
import org.ilis.entities.experience;
import org.ilis.entities.mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ExperienceRestService 
{
	@Autowired
	private MissionRepository missionRepository;
	@Autowired
	private AssociationRepository associationRepository;
	
	@Autowired
	private BenevoleRepository benevoleRepository;
	@Autowired
	private ExperienceRepository experienceRepository;
	
	
	@RequestMapping(value="/missionExperience/{id_b}/{id}",method=RequestMethod.POST)
	public mission save(@PathVariable ("id") Long id,@PathVariable ("id_b") Long idb,@RequestBody experience exp)
	{
		System.out.println("IDDDD"+idb);
		mission m=missionRepository.findOne(id);
		benevole b=benevoleRepository.findOne(idb);
		b.getExperiences().add(exp);
		exp.setMiss(m);
		exp.setB(b);
		experienceRepository.save(exp);
		benevoleRepository.save(b);
		return missionRepository.save(m);
	}
}
