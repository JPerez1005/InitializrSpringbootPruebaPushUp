package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoMunicipio;
import com.prueba.demo.dto.DtoDepartamento;
import com.prueba.demo.mapper.MapperMunicipio;
import com.prueba.demo.repositories.RepositoryMunicipio;
import com.prueba.demo.service.ServiceMunicipio;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Municipio;
import com.prueba.demo.models.Departamento;
import com.prueba.demo.repositories.RepositoryDepartamento;
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
public class ServiceImplMunicipio implements ServiceMunicipio<DtoMunicipio>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryMunicipio rd;
    
    @Autowired private RepositoryDepartamento rp;
    
    @Autowired private MapperMunicipio md;
    
    @Override
    public List<DtoMunicipio> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rd, DtoMunicipio.class);
    }

    @Override
    public ResponseEntity<Optional<DtoMunicipio>> getById(Long id) {
        if(usi.findById(rd, DtoMunicipio.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rd, DtoMunicipio.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoMunicipio dto,Long idDepartamento) {
        Departamento p=usi.convertidorAEntidades(rp, DtoDepartamento.class, idDepartamento)
                .orElseThrow(()->new EntityNotFoundException
                ("Usuario no encontrado"));
        
        Municipio d = md.toEntity(dto);
            d.setDepartamento(p);
            rd.save(d);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoMunicipio dto,Long idDepartamento) {
        Departamento p=usi.convertidorAEntidades(rp, DtoDepartamento.class, idDepartamento)
                .orElseThrow(()->new EntityNotFoundException
                ("Usuario no encontrado"));
        
        Municipio d = rd.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Municipio no encontrado"));
        
        d = md.toEntity(dto);
            d.setDepartamento(p);
            rd.save(d);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Municipio> optionalMunicipio=rd.findById(id);
        if(optionalMunicipio!=null){
            rd.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
