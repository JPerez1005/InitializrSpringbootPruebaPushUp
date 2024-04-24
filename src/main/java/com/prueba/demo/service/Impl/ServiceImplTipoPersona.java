package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoTipoPersona;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperTipoPersona;
import com.prueba.demo.models.TipoPersona;
import com.prueba.demo.repositories.RepositoryTipoPersona;
import com.prueba.demo.service.ServiceTipoPersona;
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
public class ServiceImplTipoPersona implements ServiceTipoPersona<DtoTipoPersona>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryTipoPersona rc;
    
    @Autowired private MapperTipoPersona mc;
    
    @Override
    public List<DtoTipoPersona> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoTipoPersona.class);
    }

    @Override
    public ResponseEntity<Optional<DtoTipoPersona>> getById(Long id) {
        if(usi.findById(rc, DtoTipoPersona.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoTipoPersona.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoTipoPersona dto) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        usi.save(rc, dto, TipoPersona.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoTipoPersona dto) {
        Optional<TipoPersona> optionalTipoPersona=rc.findById(id);
        if(optionalTipoPersona!=null){
            TipoPersona c=optionalTipoPersona.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<TipoPersona> optionalTipoPersona=rc.findById(id);
        if(optionalTipoPersona!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
