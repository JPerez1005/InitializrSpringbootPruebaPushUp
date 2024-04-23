package com.prueba.demo.repositories;

import com.prueba.demo.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryProveedor extends JpaRepository<Proveedor, Long>{

}
