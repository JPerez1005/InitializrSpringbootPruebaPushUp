package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoTalla;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperTalla;
import com.prueba.demo.models.Talla;
import com.prueba.demo.repositories.RepositoryTalla;
import com.prueba.demo.service.ServiceTalla;
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
public class ServiceImplTalla implements ServiceTalla<DtoTalla>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryTalla rc;
    
    @Autowired private MapperTalla mc;
    
    @Override
    public List<DtoTalla> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoTalla.class);
    }

    @Override
    public ResponseEntity<Optional<DtoTalla>> getById(Long id) {
        if(usi.findById(rc, DtoTalla.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoTalla.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoTalla dto) {
        usi.save(rc, dto, Talla.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoTalla dto) {
        Optional<Talla> optionalTalla=rc.findById(id);
        if(optionalTalla!=null){
            Talla c=optionalTalla.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Talla> optionalTalla=rc.findById(id);
        if(optionalTalla!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
