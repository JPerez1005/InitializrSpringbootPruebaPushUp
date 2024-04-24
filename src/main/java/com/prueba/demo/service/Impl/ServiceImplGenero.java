package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoGenero;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperGenero;
import com.prueba.demo.models.Genero;
import com.prueba.demo.repositories.RepositoryGenero;
import com.prueba.demo.service.ServiceGenero;
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
public class ServiceImplGenero implements ServiceGenero<DtoGenero>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryGenero rc;
    
    @Autowired private MapperGenero mc;
    
    @Override
    public List<DtoGenero> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoGenero.class);
    }

    @Override
    public ResponseEntity<Optional<DtoGenero>> getById(Long id) {
        if(usi.findById(rc, DtoGenero.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoGenero.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoGenero dto) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        usi.save(rc, dto, Genero.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoGenero dto) {
        Optional<Genero> optionalGenero=rc.findById(id);
        if(optionalGenero!=null){
            Genero c=optionalGenero.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Genero> optionalGenero=rc.findById(id);
        if(optionalGenero!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
