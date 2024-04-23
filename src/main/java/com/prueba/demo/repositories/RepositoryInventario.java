package com.prueba.demo.repositories;

import com.prueba.demo.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryInventario extends JpaRepository<Inventario, Long>{

}
