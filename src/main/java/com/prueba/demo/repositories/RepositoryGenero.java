package com.prueba.demo.repositories;

import com.prueba.demo.models.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryGenero extends JpaRepository<Genero, Long>{

}
