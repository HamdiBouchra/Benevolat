package org.ilis.dao;


import java.util.List;

import org.ilis.entities.association;
import org.ilis.entities.entreprise;
import org.ilis.entities.mission;
import org.ilis.entities.salarie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SalarieRepository extends JpaRepository<salarie, Long>{
	
}
