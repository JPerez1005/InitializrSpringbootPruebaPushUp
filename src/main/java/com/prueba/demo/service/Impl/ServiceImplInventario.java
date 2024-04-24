package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoInventario;
import com.prueba.demo.dto.DtoPrenda;
import com.prueba.demo.dto.DtoTalla;
import com.prueba.demo.mapper.MapperInventario;
import com.prueba.demo.repositories.RepositoryInventario;
import com.prueba.demo.service.ServiceInventario;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Inventario;
import com.prueba.demo.models.Prenda;
import com.prueba.demo.models.Talla;
import com.prueba.demo.repositories.RepositoryPrenda;
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
public class ServiceImplInventario implements ServiceInventario<DtoInventario>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryInventario rc;
    
    @Autowired private RepositoryPrenda rm;
    
    @Autowired private RepositoryTalla rtp;
    
    @Autowired private MapperInventario mc;
    
    @Override
    public List<DtoInventario> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoInventario.class);
    }

    @Override
    public ResponseEntity<Optional<DtoInventario>> getById(Long id) {
        if(usi.findById(rc, DtoInventario.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoInventario.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoInventario dto,Long idPrenda,Long idTalla) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Prenda m=usi.convertidorAEntidades(rm, DtoPrenda.class, idPrenda)
                .orElseThrow(()->new EntityNotFoundException
                ("Prenda no encontrado"));
        
        Talla tp=usi.convertidorAEntidades(rtp, DtoPrenda.class, idPrenda)
                .orElseThrow(()->new EntityNotFoundException
                ("Talla no encontrado"));
        
        Inventario c = mc.toEntity(dto);
        c.setPrenda(m);
        c.setTalla(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoInventario dto,Long idPrenda,Long idTalla) {
        Prenda m=usi.convertidorAEntidades(rm, DtoPrenda.class, idPrenda)
                .orElseThrow(()->new EntityNotFoundException
                ("Prenda no encontrado"));
        
        Talla tp=usi.convertidorAEntidades(rtp, DtoTalla.class, idPrenda)
                .orElseThrow(()->new EntityNotFoundException
                ("Talla no encontrado"));
        
        Inventario c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado"));
        
        c = mc.toEntity(dto);
        c.setPrenda(m);
        c.setTalla(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Inventario> optionalInventario=rc.findById(id);
        if(optionalInventario!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
