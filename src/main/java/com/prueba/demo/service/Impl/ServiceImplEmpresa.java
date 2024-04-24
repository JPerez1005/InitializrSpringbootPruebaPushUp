package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoEmpresa;
import com.prueba.demo.dto.DtoMunicipio;
import com.prueba.demo.mapper.MapperEmpresa;
import com.prueba.demo.repositories.RepositoryEmpresa;
import com.prueba.demo.service.ServiceEmpresa;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Empresa;
import com.prueba.demo.models.Municipio;
import com.prueba.demo.repositories.RepositoryMunicipio;
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
public class ServiceImplEmpresa implements ServiceEmpresa<DtoEmpresa>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryEmpresa rd;
    
    @Autowired private RepositoryMunicipio rp;
    
    @Autowired private MapperEmpresa md;
    
    @Override
    public List<DtoEmpresa> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rd, DtoEmpresa.class);
    }

    @Override
    public ResponseEntity<Optional<DtoEmpresa>> getById(Long id) {
        if(usi.findById(rd, DtoEmpresa.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rd, DtoEmpresa.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoEmpresa dto,Long idMunicipio) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Municipio p=usi.convertidorAEntidades(rp, DtoMunicipio.class, idMunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("Usuario no encontrado"));
        
        Empresa d = md.toEntity(dto);
            d.setMunicipio(p);
            rd.save(d);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoEmpresa dto,Long idMunicipio) {
        Municipio p=usi.convertidorAEntidades(rp, DtoMunicipio.class, idMunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("Usuario no encontrado"));
        
        Empresa d = rd.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrado"));
        
        d = md.toEntity(dto);
            d.setMunicipio(p);
            rd.save(d);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Empresa> optionalEmpresa=rd.findById(id);
        if(optionalEmpresa!=null){
            rd.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
