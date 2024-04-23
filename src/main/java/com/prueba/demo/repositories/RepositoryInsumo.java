package com.prueba.demo.repositories;

import com.prueba.demo.models.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryInsumo extends JpaRepository<Insumo, Long>{

}
