package org.ilis.dao;


import java.util.List;

import org.ilis.entities.association;
import org.ilis.entities.entreprise;
import org.ilis.entities.mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntrepriseRepository extends JpaRepository<entreprise, Long>{
	@Query ("select a from entreprise a where a.login =:x and a.mdp=:y")
	public entreprise EntrepByLogMdp(@Param ("x") String log,@Param ("y") String mdp);	
}
