package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoFormaPago;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.mapper.MapperFormaPago;
import com.prueba.demo.models.FormaPago;
import com.prueba.demo.repositories.RepositoryFormaPago;
import com.prueba.demo.service.ServiceFormaPago;
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
public class ServiceImplFormaPago implements ServiceFormaPago<DtoFormaPago>{

    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryFormaPago rc;
    
    @Autowired private MapperFormaPago mc;
    
    @Override
    public List<DtoFormaPago> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoFormaPago.class);
    }

    @Override
    public ResponseEntity<Optional<DtoFormaPago>> getById(Long id) {
        if(usi.findById(rc, DtoFormaPago.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoFormaPago.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoFormaPago dto) {
        usi.save(rc, dto, FormaPago.class);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoFormaPago dto) {
        Optional<FormaPago> optionalFormaPago=rc.findById(id);
        if(optionalFormaPago!=null){
            FormaPago c=optionalFormaPago.get();
            c=mc.toEntity(dto);
            c=rc.save(c);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_MODIFIED);
        }
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<FormaPago> optionalFormaPago=rc.findById(id);
        if(optionalFormaPago!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }

}
