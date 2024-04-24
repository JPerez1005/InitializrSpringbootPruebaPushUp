package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoDetalleOrden;
import com.prueba.demo.dto.DtoEstado;
import com.prueba.demo.dto.DtoOrden;
import com.prueba.demo.dto.DtoPrenda;
import com.prueba.demo.dto.DtoColor;
import com.prueba.demo.mapper.MapperDetalleOrden;
import com.prueba.demo.repositories.RepositoryDetalleOrden;
import com.prueba.demo.repositories.RepositoryEstado;
import com.prueba.demo.service.ServiceDetalleOrden;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.DetalleOrden;
import com.prueba.demo.models.Estado;
import com.prueba.demo.models.Orden;
import com.prueba.demo.models.Prenda;
import com.prueba.demo.models.Color;
import com.prueba.demo.repositories.RepositoryOrden;
import com.prueba.demo.repositories.RepositoryPrenda;
import com.prueba.demo.repositories.RepositoryColor;

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
public class ServiceImplDetalleOrden implements ServiceDetalleOrden<DtoDetalleOrden>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryDetalleOrden rc;
    
    @Autowired private RepositoryOrden rv;
    
    @Autowired private RepositoryPrenda ri;

    @Autowired private RepositoryColor rt;

    @Autowired private RepositoryEstado re;
    
    @Autowired private MapperDetalleOrden mc;
    
    @Override
    public List<DtoDetalleOrden> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoDetalleOrden.class);
    }

    @Override
    public ResponseEntity<Optional<DtoDetalleOrden>> getById(Long id) {
        if(usi.findById(rc, DtoDetalleOrden.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoDetalleOrden.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(
        DtoDetalleOrden dto,
        Long idOrden,
        Long idPrenda,
        Long idColor,
        Long idEstado) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Orden m=usi.convertidorAEntidades(rv, DtoOrden.class, idOrden)
                .orElseThrow(()->new EntityNotFoundException
                ("Orden no encontrado"));
        
        Prenda tp=usi.convertidorAEntidades(ri, DtoOrden.class, idOrden)
                .orElseThrow(()->new EntityNotFoundException
                ("Prenda no encontrada"));
        
        Color t=usi.convertidorAEntidades(rt, DtoColor.class, idColor)
                .orElseThrow(()->new EntityNotFoundException
                ("Color no encontrado"));

        Estado e=usi.convertidorAEntidades(re, DtoEstado.class, idEstado)
                .orElseThrow(()->new EntityNotFoundException
                ("Estado no encontrado"));

        DetalleOrden c = mc.toEntity(dto);
        c.setOrden(m);
        c.setPrenda(tp);
        c.setColor(t);
        c.setEstado(e);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(
        Long id,
        DtoDetalleOrden dto,
        Long idOrden,
        Long idPrenda,
        Long idColor,
        Long idEstado) {
        Orden m=usi.convertidorAEntidades(rv, DtoOrden.class, idOrden)
                .orElseThrow(()->new EntityNotFoundException
                ("Orden no encontrado"));
        
        Prenda tp=usi.convertidorAEntidades(ri, DtoPrenda.class, idOrden)
                .orElseThrow(()->new EntityNotFoundException
                ("Prenda no encontrado"));

        Color t=usi.convertidorAEntidades(rt, DtoColor.class, idColor)
                .orElseThrow(()->new EntityNotFoundException
                ("Color no encontrada"));

        Estado e=usi.convertidorAEntidades(re, DtoEstado.class, idEstado)
                .orElseThrow(()->new EntityNotFoundException
                ("Estado no encontrado"));

        DetalleOrden c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DetalleOrden no encontrado"));
        
        c = mc.toEntity(dto);
        c.setOrden(m);
        c.setPrenda(tp);
        c.setColor(t);
        c.setEstado(e);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<DetalleOrden> optionalDetalleOrden=rc.findById(id);
        if(optionalDetalleOrden!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
