package com.prueba.demo.repositories;

import com.prueba.demo.models.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryPais extends JpaRepository<Pais, Long>{

}
