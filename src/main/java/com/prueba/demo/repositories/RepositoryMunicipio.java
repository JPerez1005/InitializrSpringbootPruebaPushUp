package com.prueba.demo.repositories;

import com.prueba.demo.models.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryMunicipio extends JpaRepository<Municipio, Long>{

}
