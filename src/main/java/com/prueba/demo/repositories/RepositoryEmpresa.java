package com.prueba.demo.repositories;

import com.prueba.demo.models.Cargos;
import com.prueba.demo.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryEmpresa extends JpaRepository<Empresa, Long>{

}
