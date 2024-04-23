package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoColor;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperColor;
import com.prueba.demo.models.Color;
import com.prueba.demo.repositories.RepositoryColor;
import com.prueba.demo.service.ServiceColor;
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
public class ServiceImplColor implements ServiceColor<DtoColor>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryColor rc;
    
    @Autowired private MapperColor mc;
    
    @Override
    public List<DtoColor> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoColor.class);
    }

    @Override
    public ResponseEntity<Optional<DtoColor>> getById(Long id) {
        if(usi.findById(rc, DtoColor.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoColor.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoColor dto) {
        usi.save(rc, dto, Color.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoColor dto) {
        Optional<Color> optionalColor=rc.findById(id);
        if(optionalColor!=null){
            Color c=optionalColor.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Color> optionalColor=rc.findById(id);
        if(optionalColor!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
