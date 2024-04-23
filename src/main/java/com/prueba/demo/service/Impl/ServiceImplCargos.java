package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoCargos;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperCargos;
import com.prueba.demo.models.Cargos;
import com.prueba.demo.repositories.RepositoryCargos;
import com.prueba.demo.service.ServiceCargos;
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
public class ServiceImplCargos implements ServiceCargos<DtoCargos>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryCargos rc;
    
    @Autowired private MapperCargos mc;
    
    @Override
    public List<DtoCargos> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoCargos.class);
    }

    @Override
    public ResponseEntity<Optional<DtoCargos>> getById(Long id) {
        if(usi.findById(rc, DtoCargos.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoCargos.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoCargos dto) {
        usi.save(rc, dto, Cargos.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoCargos dto) {
        Optional<Cargos> optionalCargos=rc.findById(id);
        if(optionalCargos!=null){
            Cargos c=optionalCargos.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Cargos> optionalCargos=rc.findById(id);
        if(optionalCargos!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
