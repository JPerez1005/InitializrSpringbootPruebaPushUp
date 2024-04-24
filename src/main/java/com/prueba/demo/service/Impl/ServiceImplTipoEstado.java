package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoTipoEstado;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperTipoEstado;
import com.prueba.demo.models.TipoEstado;
import com.prueba.demo.repositories.RepositoryTipoEstado;
import com.prueba.demo.service.ServiceTipoEstado;
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
public class ServiceImplTipoEstado implements ServiceTipoEstado<DtoTipoEstado>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryTipoEstado rc;
    
    @Autowired private MapperTipoEstado mc;
    
    @Override
    public List<DtoTipoEstado> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoTipoEstado.class);
    }

    @Override
    public ResponseEntity<Optional<DtoTipoEstado>> getById(Long id) {
        if(usi.findById(rc, DtoTipoEstado.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoTipoEstado.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoTipoEstado dto) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        usi.save(rc, dto, TipoEstado.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoTipoEstado dto) {
        Optional<TipoEstado> optionalTipoEstado=rc.findById(id);
        if(optionalTipoEstado!=null){
            TipoEstado c=optionalTipoEstado.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<TipoEstado> optionalTipoEstado=rc.findById(id);
        if(optionalTipoEstado!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
