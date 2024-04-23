package com.prueba.demo.repositories;

import com.prueba.demo.models.TipoPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryTipoPersona extends JpaRepository<TipoPersona, Long>{

}
