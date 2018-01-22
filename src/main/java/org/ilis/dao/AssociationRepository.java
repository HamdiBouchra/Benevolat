package org.ilis.dao;


import java.util.List;

import org.ilis.entities.association;
import org.ilis.entities.mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssociationRepository extends JpaRepository<association, Long>{
	@Query ("select a from association a where a.nom like :x")
	public Page<association> chercherAssociation(@Param ("x")String mc,Pageable pageable);
	
	@Query ("select a from association a where a.etat =:x")
	public Page<association> AssAtt(@Param ("x") int et,Pageable pageable);
	
	/*@Query ("select a from admin a where a.login =:x and a.mdp=:y")
	public Page<association> AssAtt(@Param ("x") String lg,@Param ("x") String mdp);*/
	
	/*@Query ("select a from association_missions a where a.association_id =:x.id")
	public Page<mission> missions(@Param ("x") association a,Pageable pageable);*/
	
	
	@Query ("select a from association a where a.etat =:x")
	public List<association> AssVld(@Param ("x") int et);
	
	
	
	
	@Query ("select a from association a where a.login =:x and a.mdp=:y")
	public association AssByLogMdp(@Param ("x") String log,@Param ("y") String mdp);	
}
