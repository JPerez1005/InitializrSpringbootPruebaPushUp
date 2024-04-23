package com.prueba.demo.repositories;

import com.prueba.demo.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryCliente extends JpaRepository<Cliente, Long>{

}
