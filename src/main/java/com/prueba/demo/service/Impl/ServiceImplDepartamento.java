package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoCliente;
import com.prueba.demo.mapper.MapperCliente;
import com.prueba.demo.repositories.RepositoryCliente;
import com.prueba.demo.service.ServiceCliente;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Cliente;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author perez
 */

@Service
public class ServiceImplCliente implements ServiceCliente<DtoCliente>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryCliente rc;
    
    @Autowired private MapperCliente mc;
    
    @Override
    public List<DtoCliente> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoCliente.class);
    }

    @Override
    public ResponseEntity<Optional<DtoCliente>> getById(Long id) {
        if(usi.findById(rc, DtoCliente.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoCliente.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoCliente dto,Long idmunicipio,Long idtipoPersona) {
        return null;
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoCliente dto,Long idmunicipio,Long idtipoPersona) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Cliente> optionalCliente=rc.findById(id);
        if(optionalCliente!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
