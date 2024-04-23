package com.prueba.demo.repositories;

import com.prueba.demo.models.Cargos;
import com.prueba.demo.models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryDetalleVenta extends JpaRepository<DetalleVenta, Long>{

}
