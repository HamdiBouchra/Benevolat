package org.ilis.dao;

import org.ilis.entities.association;
import org.ilis.entities.benevole;
import org.ilis.entities.mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BenevoleRepository extends JpaRepository<benevole, Long>{
	
	@Query ("select a from benevole a where a.login =:x and a.mdp=:y")
	public benevole benByLogMdp(@Param ("x") String log,@Param ("y") String mdp);

}
