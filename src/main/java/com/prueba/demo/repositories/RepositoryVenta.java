package com.prueba.demo.repositories;

import com.prueba.demo.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryVenta extends JpaRepository<Venta, Long>{

}
