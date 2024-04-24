package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoPais;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperPais;
import com.prueba.demo.models.Pais;
import com.prueba.demo.repositories.RepositoryPais;
import com.prueba.demo.service.ServicePais;
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
public class ServiceImplPais implements ServicePais<DtoPais>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryPais rc;
    
    @Autowired private MapperPais mc;
    
    @Override
    public List<DtoPais> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoPais.class);
    }

    @Override
    public ResponseEntity<Optional<DtoPais>> getById(Long id) {
        if(usi.findById(rc, DtoPais.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoPais.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoPais dto) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        usi.save(rc, dto, Pais.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoPais dto) {
        Optional<Pais> optionalPais=rc.findById(id);
        if(optionalPais!=null){
            Pais c=optionalPais.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Pais> optionalPais=rc.findById(id);
        if(optionalPais!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
