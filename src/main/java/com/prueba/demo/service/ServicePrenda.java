package com.prueba.demo.service;

import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

/**
 * @author perez
 */
public interface ServicePrenda<Dto> {
    List<Dto> getAll() throws EmptyDataException,NoAuthorizedException;
    ResponseEntity<Optional<Dto>> getById(Long id);
    ResponseEntity<String> create(Dto dto,Long tipoProteccion,Long estado,Long genero);
    ResponseEntity<String> update(Long id, Dto dto,Long tipoProteccion,Long estado,Long genero);
    ResponseEntity<String> delete(Long id);
}
