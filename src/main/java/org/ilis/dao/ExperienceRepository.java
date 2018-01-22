package org.ilis.dao;

import org.ilis.entities.benevole;
import org.ilis.entities.experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ExperienceRepository  extends JpaRepository<experience, Long> {

}
