package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoTipoProteccion;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperTipoProteccion;
import com.prueba.demo.models.TipoProteccion;
import com.prueba.demo.repositories.RepositoryTipoProteccion;
import com.prueba.demo.service.ServiceTipoProteccion;
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
public class ServiceImplTipoProteccion implements ServiceTipoProteccion<DtoTipoProteccion>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryTipoProteccion rc;
    
    @Autowired private MapperTipoProteccion mc;
    
    @Override
    public List<DtoTipoProteccion> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoTipoProteccion.class);
    }

    @Override
    public ResponseEntity<Optional<DtoTipoProteccion>> getById(Long id) {
        if(usi.findById(rc, DtoTipoProteccion.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoTipoProteccion.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoTipoProteccion dto) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        usi.save(rc, dto, TipoProteccion.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoTipoProteccion dto) {
        Optional<TipoProteccion> optionalTipoProteccion=rc.findById(id);
        if(optionalTipoProteccion!=null){
            TipoProteccion c=optionalTipoProteccion.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<TipoProteccion> optionalTipoProteccion=rc.findById(id);
        if(optionalTipoProteccion!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
