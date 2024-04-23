package com.prueba.demo.repositories;

import com.prueba.demo.models.InsumoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryInsumoProveedor extends JpaRepository<InsumoProveedor, Long>{

}
