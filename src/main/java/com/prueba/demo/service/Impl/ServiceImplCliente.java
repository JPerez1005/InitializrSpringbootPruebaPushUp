package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoCliente;
import com.prueba.demo.dto.DtoMunicipio;
import com.prueba.demo.dto.DtoTipoPersona;
import com.prueba.demo.mapper.MapperCliente;
import com.prueba.demo.repositories.RepositoryCliente;
import com.prueba.demo.service.ServiceCliente;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Cliente;
import com.prueba.demo.models.Municipio;
import com.prueba.demo.models.TipoPersona;
import com.prueba.demo.repositories.RepositoryMunicipio;
import com.prueba.demo.repositories.RepositoryTipoPersona;
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
public class ServiceImplCliente implements ServiceCliente<DtoCliente>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryCliente rc;
    
    @Autowired private RepositoryMunicipio rm;
    
    @Autowired private RepositoryTipoPersona rtp;
    
    @Autowired private MapperCliente mc;
    
    @Override
    public List<DtoCliente> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoCliente.class);
    }

    @Override
    public ResponseEntity<Optional<DtoCliente>> getById(Long id) {
        if(usi.findById(rc, DtoCliente.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoCliente.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoCliente dto,Long idmunicipio,Long idtipoPersona) {
        Municipio m=usi.convertidorAEntidades(rm, DtoMunicipio.class, idmunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("Municipio no encontrado"));
        
        TipoPersona tp=usi.convertidorAEntidades(rtp, DtoMunicipio.class, idmunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("TipoPersona no encontrado"));
        
        Cliente c = mc.toEntity(dto);
        c.setMunicipio(m);
        c.setTipoPersona(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoCliente dto,Long idmunicipio,Long idtipoPersona) {
        Municipio m=usi.convertidorAEntidades(rm, DtoMunicipio.class, idmunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("Municipio no encontrado"));
        
        TipoPersona tp=usi.convertidorAEntidades(rtp, DtoTipoPersona.class, idmunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("TipoPersona no encontrado"));
        
        Cliente c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        
        c = mc.toEntity(dto);
        c.setMunicipio(m);
        c.setTipoPersona(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Cliente> optionalCliente=rc.findById(id);
        if(optionalCliente!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
