package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoProveedor;
import com.prueba.demo.dto.DtoTipoPersona;
import com.prueba.demo.dto.DtoMunicipio;
import com.prueba.demo.mapper.MapperProveedor;
import com.prueba.demo.repositories.RepositoryProveedor;
import com.prueba.demo.service.ServiceProveedor;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Proveedor;
import com.prueba.demo.models.TipoPersona;
import com.prueba.demo.models.Municipio;
import com.prueba.demo.repositories.RepositoryTipoPersona;
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
public class ServiceImplProveedor implements ServiceProveedor<DtoProveedor>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryProveedor rc;
    
    @Autowired private RepositoryTipoPersona rm;
    
    @Autowired private RepositoryMunicipio rtp;
    
    @Autowired private MapperProveedor mc;
    
    @Override
    public List<DtoProveedor> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoProveedor.class);
    }

    @Override
    public ResponseEntity<Optional<DtoProveedor>> getById(Long id) {
        if(usi.findById(rc, DtoProveedor.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoProveedor.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoProveedor dto,Long idTipoPersona,Long idMunicipio) {
        TipoPersona m=usi.convertidorAEntidades(rm, DtoTipoPersona.class, idTipoPersona)
                .orElseThrow(()->new EntityNotFoundException
                ("TipoPersona no encontrado"));
        
        Municipio tp=usi.convertidorAEntidades(rtp, DtoTipoPersona.class, idTipoPersona)
                .orElseThrow(()->new EntityNotFoundException
                ("Municipio no encontrado"));
        
        Proveedor c = mc.toEntity(dto);
        c.setTipoPersona(m);
        c.setMunicipio(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoProveedor dto,Long idTipoPersona,Long idMunicipio) {
        TipoPersona m=usi.convertidorAEntidades(rm, DtoTipoPersona.class, idTipoPersona)
                .orElseThrow(()->new EntityNotFoundException
                ("TipoPersona no encontrado"));
        
        Municipio tp=usi.convertidorAEntidades(rtp, DtoMunicipio.class, idTipoPersona)
                .orElseThrow(()->new EntityNotFoundException
                ("Municipio no encontrado"));
        
        Proveedor c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"));
        
        c = mc.toEntity(dto);
        c.setTipoPersona(m);
        c.setMunicipio(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Proveedor> optionalProveedor=rc.findById(id);
        if(optionalProveedor!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
