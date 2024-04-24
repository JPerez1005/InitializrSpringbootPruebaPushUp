package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoPrenda;
import com.prueba.demo.dto.DtoEstado;
import com.prueba.demo.dto.DtoTipoProteccion;
import com.prueba.demo.dto.DtoGenero;
import com.prueba.demo.mapper.MapperPrenda;
import com.prueba.demo.repositories.RepositoryPrenda;
import com.prueba.demo.service.ServicePrenda;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Prenda;
import com.prueba.demo.models.Estado;
import com.prueba.demo.models.TipoProteccion;
import com.prueba.demo.models.Genero;
import com.prueba.demo.repositories.RepositoryEstado;
import com.prueba.demo.repositories.RepositoryTipoProteccion;
import com.prueba.demo.repositories.RepositoryGenero;

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
public class ServiceImplPrenda implements ServicePrenda<DtoPrenda>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryPrenda rc;
    
    @Autowired private RepositoryEstado rv;
    
    @Autowired private RepositoryTipoProteccion ri;

    @Autowired private RepositoryGenero rt;
    
    @Autowired private MapperPrenda mc;
    
    @Override
    public List<DtoPrenda> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoPrenda.class);
    }

    @Override
    public ResponseEntity<Optional<DtoPrenda>> getById(Long id) {
        if(usi.findById(rc, DtoPrenda.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoPrenda.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(
        DtoPrenda dto,
        Long idEstado,
        Long idTipoProteccion,
        Long idGenero) {
        Estado m=usi.convertidorAEntidades(rv, DtoEstado.class, idEstado)
                .orElseThrow(()->new EntityNotFoundException
                ("Estado no encontrada"));
        
        TipoProteccion tp=usi.convertidorAEntidades(ri, DtoEstado.class, idEstado)
                .orElseThrow(()->new EntityNotFoundException
                ("TipoProteccion no encontrado"));
        
        Genero t=usi.convertidorAEntidades(rt, DtoGenero.class, idGenero)
                .orElseThrow(()->new EntityNotFoundException
                ("Genero no encontrada"));

        Prenda c = mc.toEntity(dto);
        c.setEstado(m);
        c.setTipoProteccion(tp);
        c.setGenero(t);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(
        Long id,
        DtoPrenda dto,
        Long idEstado,
        Long idTipoProteccion,
        Long idGenero) {
        Estado m=usi.convertidorAEntidades(rv, DtoEstado.class, idEstado)
                .orElseThrow(()->new EntityNotFoundException
                ("Estado no encontrado"));
        
        TipoProteccion tp=usi.convertidorAEntidades(ri, DtoTipoProteccion.class, idEstado)
                .orElseThrow(()->new EntityNotFoundException
                ("TipoProteccion no encontrado"));

        Genero t=usi.convertidorAEntidades(rt, DtoGenero.class, idGenero)
                .orElseThrow(()->new EntityNotFoundException
                ("Genero no encontrada"));
        
        Prenda c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenda no encontrado"));
        
        c = mc.toEntity(dto);
        c.setEstado(m);
        c.setTipoProteccion(tp);
        c.setGenero(t);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Prenda> optionalPrenda=rc.findById(id);
        if(optionalPrenda!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
