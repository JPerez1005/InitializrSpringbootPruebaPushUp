package com.prueba.demo.repositories;

import com.prueba.demo.models.TipoProteccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryTipoProteccion extends JpaRepository<TipoProteccion, Long>{

}
