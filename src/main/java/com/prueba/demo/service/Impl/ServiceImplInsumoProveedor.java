package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoInsumoProveedor;
import com.prueba.demo.dto.DtoProveedor;
import com.prueba.demo.dto.DtoInsumo;
import com.prueba.demo.mapper.MapperInsumoProveedor;
import com.prueba.demo.repositories.RepositoryInsumoProveedor;
import com.prueba.demo.service.ServiceInsumoProveedor;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.InsumoProveedor;
import com.prueba.demo.models.Proveedor;
import com.prueba.demo.models.Insumo;
import com.prueba.demo.repositories.RepositoryProveedor;
import com.prueba.demo.repositories.RepositoryInsumo;
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
public class ServiceImplInsumoProveedor implements ServiceInsumoProveedor<DtoInsumoProveedor>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryInsumoProveedor rc;
    
    @Autowired private RepositoryProveedor rm;
    
    @Autowired private RepositoryInsumo rtp;
    
    @Autowired private MapperInsumoProveedor mc;
    
    @Override
    public List<DtoInsumoProveedor> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoInsumoProveedor.class);
    }

    @Override
    public ResponseEntity<Optional<DtoInsumoProveedor>> getById(Long id) {
        if(usi.findById(rc, DtoInsumoProveedor.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoInsumoProveedor.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoInsumoProveedor dto,Long idProveedor,Long idInsumo) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Proveedor m=usi.convertidorAEntidades(rm, DtoProveedor.class, idProveedor)
                .orElseThrow(()->new EntityNotFoundException
                ("Proveedor no encontrado"));
        
        Insumo tp=usi.convertidorAEntidades(rtp, DtoProveedor.class, idProveedor)
                .orElseThrow(()->new EntityNotFoundException
                ("Insumo no encontrado"));
        
        InsumoProveedor c = mc.toEntity(dto);
        c.setProveedor(m);
        c.setInsumo(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoInsumoProveedor dto,Long idProveedor,Long idInsumo) {
        Proveedor m=usi.convertidorAEntidades(rm, DtoProveedor.class, idProveedor)
                .orElseThrow(()->new EntityNotFoundException
                ("Proveedor no encontrado"));
        
        Insumo tp=usi.convertidorAEntidades(rtp, DtoInsumo.class, idProveedor)
                .orElseThrow(()->new EntityNotFoundException
                ("Insumo no encontrado"));
        
        InsumoProveedor c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InsumoProveedor no encontrado"));
        
        c = mc.toEntity(dto);
        c.setProveedor(m);
        c.setInsumo(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<InsumoProveedor> optionalInsumoProveedor=rc.findById(id);
        if(optionalInsumoProveedor!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
