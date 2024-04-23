package com.prueba.demo.repositories;

import com.prueba.demo.models.Cargos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryCargos extends JpaRepository<Cargos, Long>{

}
