package com.prueba.demo.repositories;

import com.prueba.demo.models.Cliente;
import com.prueba.demo.models.Color;
import com.prueba.demo.models.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryDepartamento extends JpaRepository<Departamento, Long>{

}
