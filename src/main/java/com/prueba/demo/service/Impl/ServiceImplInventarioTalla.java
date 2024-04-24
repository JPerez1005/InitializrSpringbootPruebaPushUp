package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoInventarioTalla;
import com.prueba.demo.dto.DtoInventario;
import com.prueba.demo.dto.DtoTalla;
import com.prueba.demo.mapper.MapperInventarioTalla;
import com.prueba.demo.repositories.RepositoryInventarioTalla;
import com.prueba.demo.service.ServiceInventarioTalla;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.InventarioTalla;
import com.prueba.demo.models.Inventario;
import com.prueba.demo.models.Talla;
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
public class ServiceImplInventarioTalla implements ServiceInventarioTalla<DtoInventarioTalla>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryInventarioTalla rc;
    
    @Autowired private RepositoryInventario rm;
    
    @Autowired private RepositoryTalla rtp;
    
    @Autowired private MapperInventarioTalla mc;
    
    @Override
    public List<DtoInventarioTalla> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoInventarioTalla.class);
    }

    @Override
    public ResponseEntity<Optional<DtoInventarioTalla>> getById(Long id) {
        if(usi.findById(rc, DtoInventarioTalla.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoInventarioTalla.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoInventarioTalla dto,Long idInventario,Long idTalla) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Inventario m=usi.convertidorAEntidades(rm, DtoInventario.class, idInventario)
                .orElseThrow(()->new EntityNotFoundException
                ("Inventario no encontrado"));
        
        Talla tp=usi.convertidorAEntidades(rtp, DtoInventario.class, idInventario)
                .orElseThrow(()->new EntityNotFoundException
                ("Talla no encontrado"));
        
        InventarioTalla c = mc.toEntity(dto);
        c.setInventario(m);
        c.setTalla(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoInventarioTalla dto,Long idInventario,Long idTalla) {
        Inventario m=usi.convertidorAEntidades(rm, DtoInventario.class, idInventario)
                .orElseThrow(()->new EntityNotFoundException
                ("Inventario no encontrado"));
        
        Talla tp=usi.convertidorAEntidades(rtp, DtoTalla.class, idInventario)
                .orElseThrow(()->new EntityNotFoundException
                ("Talla no encontrado"));
        
        InventarioTalla c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InventarioTalla no encontrado"));
        
        c = mc.toEntity(dto);
        c.setInventario(m);
        c.setTalla(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<InventarioTalla> optionalInventarioTalla=rc.findById(id);
        if(optionalInventarioTalla!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
