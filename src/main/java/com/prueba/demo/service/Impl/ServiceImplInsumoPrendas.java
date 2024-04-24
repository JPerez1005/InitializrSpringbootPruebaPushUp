package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoInsumoPrendas;
import com.prueba.demo.dto.DtoInsumo;
import com.prueba.demo.dto.DtoPrenda;
import com.prueba.demo.mapper.MapperInsumoPrendas;
import com.prueba.demo.repositories.RepositoryInsumoPrendas;
import com.prueba.demo.service.ServiceInsumoPrendas;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.InsumoPrendas;
import com.prueba.demo.models.Insumo;
import com.prueba.demo.models.Prenda;
import com.prueba.demo.repositories.RepositoryInsumo;
import com.prueba.demo.repositories.RepositoryPrenda;
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
public class ServiceImplInsumoPrendas implements ServiceInsumoPrendas<DtoInsumoPrendas>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryInsumoPrendas rc;
    
    @Autowired private RepositoryInsumo rm;
    
    @Autowired private RepositoryPrenda rtp;
    
    @Autowired private MapperInsumoPrendas mc;
    
    @Override
    public List<DtoInsumoPrendas> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoInsumoPrendas.class);
    }

    @Override
    public ResponseEntity<Optional<DtoInsumoPrendas>> getById(Long id) {
        if(usi.findById(rc, DtoInsumoPrendas.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoInsumoPrendas.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoInsumoPrendas dto,Long idInsumo,Long idPrenda) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Insumo m=usi.convertidorAEntidades(rm, DtoInsumo.class, idInsumo)
                .orElseThrow(()->new EntityNotFoundException
                ("Insumo no encontrado"));
        
        Prenda tp=usi.convertidorAEntidades(rtp, DtoInsumo.class, idInsumo)
                .orElseThrow(()->new EntityNotFoundException
                ("Prenda no encontrado"));
        
        InsumoPrendas c = mc.toEntity(dto);
        c.setInsumo(m);
        c.setPrenda(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoInsumoPrendas dto,Long idInsumo,Long idPrenda) {
        Insumo m=usi.convertidorAEntidades(rm, DtoInsumo.class, idInsumo)
                .orElseThrow(()->new EntityNotFoundException
                ("Insumo no encontrado"));
        
        Prenda tp=usi.convertidorAEntidades(rtp, DtoPrenda.class, idInsumo)
                .orElseThrow(()->new EntityNotFoundException
                ("Prenda no encontrado"));
        
        InsumoPrendas c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("InsumoPrendas no encontrado"));
        
        c = mc.toEntity(dto);
        c.setInsumo(m);
        c.setPrenda(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<InsumoPrendas> optionalInsumoPrendas=rc.findById(id);
        if(optionalInsumoPrendas!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
