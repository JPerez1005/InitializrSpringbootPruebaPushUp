package com.prueba.demo.repositories;

import com.prueba.demo.models.Cargos;
import com.prueba.demo.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryEstado extends JpaRepository<Estado, Long>{

}
