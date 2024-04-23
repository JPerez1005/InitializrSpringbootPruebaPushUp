package com.prueba.demo.repositories;

import com.prueba.demo.models.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author perez
 */
@Repository
public interface RepositoryFormaPago extends JpaRepository<FormaPago, Long>{

}
