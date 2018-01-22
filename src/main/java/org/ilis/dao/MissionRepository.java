package org.ilis.dao;

import org.ilis.entities.association;
import org.ilis.entities.mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MissionRepository extends JpaRepository<mission, Long>{

	@Query ("select m from mission m where m.nom like :x")
	public Page<mission> chercherMission(@Param ("x")String mc,Pageable pageable);
	
	@Query ("select m from mission m")
	public Page<mission> AllMiss(Pageable pageable);
}
