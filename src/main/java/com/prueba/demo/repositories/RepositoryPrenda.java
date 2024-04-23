package com.prueba.demo.repositories;

import com.prueba.demo.models.Prenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryPrenda extends JpaRepository<Prenda, Long>{

}
