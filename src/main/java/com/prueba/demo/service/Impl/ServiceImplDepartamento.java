package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoDepartamento;
import com.prueba.demo.dto.DtoPais;
import com.prueba.demo.mapper.MapperDepartamento;
import com.prueba.demo.repositories.RepositoryDepartamento;
import com.prueba.demo.service.ServiceDepartamento;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Departamento;
import com.prueba.demo.models.Pais;
import com.prueba.demo.repositories.RepositoryPais;
import jakarta.persistence.EntityNotFoundException;
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
public class ServiceImplDepartamento implements ServiceDepartamento<DtoDepartamento>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryDepartamento rd;
    
    @Autowired private RepositoryPais rp;
    
    @Autowired private MapperDepartamento md;
    
    @Override
    public List<DtoDepartamento> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rd, DtoDepartamento.class);
    }

    @Override
    public ResponseEntity<Optional<DtoDepartamento>> getById(Long id) {
        if(usi.findById(rd, DtoDepartamento.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rd, DtoDepartamento.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoDepartamento dto,Long idpais) {
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Pais p=usi.convertidorAEntidades(rp, DtoPais.class, idpais)
                .orElseThrow(()->new EntityNotFoundException
                ("Usuario no encontrado"));
        
        Departamento d = md.toEntity(dto);
            d.setPais(p);
            rd.save(d);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoDepartamento dto,Long idpais) {
        Pais p=usi.convertidorAEntidades(rp, DtoPais.class, idpais)
                .orElseThrow(()->new EntityNotFoundException
                ("Usuario no encontrado"));
        
        Departamento d = rd.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Departamento no encontrado"));
        
        d = md.toEntity(dto);
            d.setPais(p);
            rd.save(d);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Departamento> optionalDepartamento=rd.findById(id);
        if(optionalDepartamento!=null){
            rd.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
