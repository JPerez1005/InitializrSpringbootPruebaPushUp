package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoInsumo;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperInsumo;
import com.prueba.demo.models.Insumo;
import com.prueba.demo.repositories.RepositoryInsumo;
import com.prueba.demo.service.ServiceInsumo;
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
public class ServiceImplInsumo implements ServiceInsumo<DtoInsumo>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryInsumo rc;
    
    @Autowired private MapperInsumo mc;
    
    @Override
    public List<DtoInsumo> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoInsumo.class);
    }

    @Override
    public ResponseEntity<Optional<DtoInsumo>> getById(Long id) {
        if(usi.findById(rc, DtoInsumo.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoInsumo.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoInsumo dto) {
        usi.save(rc, dto, Insumo.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoInsumo dto) {
        Optional<Insumo> optionalInsumo=rc.findById(id);
        if(optionalInsumo!=null){
            Insumo c=optionalInsumo.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Insumo> optionalInsumo=rc.findById(id);
        if(optionalInsumo!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
