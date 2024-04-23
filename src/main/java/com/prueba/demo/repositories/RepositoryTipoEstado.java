package com.prueba.demo.repositories;

import com.prueba.demo.models.TipoEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryTipoEstado extends JpaRepository<TipoEstado, Long>{

}
