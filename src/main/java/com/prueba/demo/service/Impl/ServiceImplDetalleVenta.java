package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoVenta;
import com.prueba.demo.dto.DtoInventario;
import com.prueba.demo.dto.DtoTalla;
import com.prueba.demo.mapper.MapperDetalleVenta;
import com.prueba.demo.repositories.RepositoryDetalleVenta;
import com.prueba.demo.service.ServiceDetalleVenta;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Venta;
import com.prueba.demo.models.Inventario;
import com.prueba.demo.models.Talla;
import com.prueba.demo.repositories.RepositoryVenta;
import com.prueba.demo.repositories.RepositoryInventario;
import com.prueba.demo.repositories.RepositoryTalla;

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
public class ServiceImplDetalleVenta implements ServiceDetalleVenta<DtoDetalleVenta>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryDetalleVenta rc;
    
    @Autowired private RepositoryVenta rv;
    
    @Autowired private RepositoryInventario ri;

    @Autowired private RepositoryTalla rt;
    
    @Autowired private MapperDetalleVenta mc;
    
    @Override
    public List<DtoDetalleVenta> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoDetalleVenta.class);
    }

    @Override
    public ResponseEntity<Optional<DtoDetalleVenta>> getById(Long id) {
        if(usi.findById(rc, DtoDetalleVenta.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoDetalleVenta.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(
        DtoDetalleVenta dto,
        Long idVenta,
        Long idInventario,
        Long idTalla) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Venta m=usi.convertidorAEntidades(rv, DtoVenta.class, idVenta)
                .orElseThrow(()->new EntityNotFoundException
                ("Venta no encontrada"));
        
        Inventario tp=usi.convertidorAEntidades(ri, DtoVenta.class, idVenta)
                .orElseThrow(()->new EntityNotFoundException
                ("Inventario no encontrado"));
        
        Talla t=usi.convertidorAEntidades(rt, DtoTalla.class, idTalla)
                .orElseThrow(()->new EntityNotFoundException
                ("Talla no encontrada"));

        DetalleVenta c = mc.toEntity(dto);
        c.setVenta(m);
        c.setInventario(tp);
        c.setTalla(t);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(
        Long id,
        DtoDetalleVenta dto,
        Long idVenta,
        Long idInventario,
        Long idTalla) {
        Venta m=usi.convertidorAEntidades(rv, DtoVenta.class, idVenta)
                .orElseThrow(()->new EntityNotFoundException
                ("Venta no encontrado"));
        
        Inventario tp=usi.convertidorAEntidades(ri, DtoInventario.class, idVenta)
                .orElseThrow(()->new EntityNotFoundException
                ("Inventario no encontrado"));

        Talla t=usi.convertidorAEntidades(rt, DtoTalla.class, idTalla)
                .orElseThrow(()->new EntityNotFoundException
                ("Talla no encontrada"));
        
        DetalleVenta c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DetalleVenta no encontrado"));
        
        c = mc.toEntity(dto);
        c.setVenta(m);
        c.setInventario(tp);
        c.setTalla(t);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<DetalleVenta> optionalDetalleVenta=rc.findById(id);
        if(optionalDetalleVenta!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
