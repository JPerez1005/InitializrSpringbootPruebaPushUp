package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoOrden;
import com.prueba.demo.dto.DtoEmpleado;
import com.prueba.demo.dto.DtoEstado;
import com.prueba.demo.dto.DtoCliente;
import com.prueba.demo.mapper.MapperOrden;
import com.prueba.demo.repositories.RepositoryOrden;
import com.prueba.demo.service.ServiceOrden;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Orden;
import com.prueba.demo.models.Empleado;
import com.prueba.demo.models.Estado;
import com.prueba.demo.models.Cliente;
import com.prueba.demo.repositories.RepositoryEmpleado;
import com.prueba.demo.repositories.RepositoryEstado;
import com.prueba.demo.repositories.RepositoryCliente;

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
public class ServiceImplOrden implements ServiceOrden<DtoOrden>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryOrden rc;
    
    @Autowired private RepositoryEmpleado rv;
    
    @Autowired private RepositoryEstado ri;

    @Autowired private RepositoryCliente rt;
    
    @Autowired private MapperOrden mc;
    
    @Override
    public List<DtoOrden> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoOrden.class);
    }

    @Override
    public ResponseEntity<Optional<DtoOrden>> getById(Long id) {
        if(usi.findById(rc, DtoOrden.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoOrden.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(
        DtoOrden dto,
        Long idEmpleado,
        Long idEstado,
        Long idCliente) {
        
        if(dto==null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        Empleado m=usi.convertidorAEntidades(rv, DtoEmpleado.class, idEmpleado)
                .orElseThrow(()->new EntityNotFoundException
                ("Empleado no encontrada"));
        
        Estado tp=usi.convertidorAEntidades(ri, DtoEmpleado.class, idEmpleado)
                .orElseThrow(()->new EntityNotFoundException
                ("Estado no encontrado"));
        
        Cliente t=usi.convertidorAEntidades(rt, DtoCliente.class, idCliente)
                .orElseThrow(()->new EntityNotFoundException
                ("Cliente no encontrada"));

        Orden c = mc.toEntity(dto);
        c.setEmpleado(m);
        c.setEstado(tp);
        c.setCliente(t);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(
        Long id,
        DtoOrden dto,
        Long idEmpleado,
        Long idEstado,
        Long idCliente) {
        Empleado m=usi.convertidorAEntidades(rv, DtoEmpleado.class, idEmpleado)
                .orElseThrow(()->new EntityNotFoundException
                ("Empleado no encontrado"));
        
        Estado tp=usi.convertidorAEntidades(ri, DtoEstado.class, idEmpleado)
                .orElseThrow(()->new EntityNotFoundException
                ("Estado no encontrado"));

        Cliente t=usi.convertidorAEntidades(rt, DtoCliente.class, idCliente)
                .orElseThrow(()->new EntityNotFoundException
                ("Cliente no encontrada"));
        
        Orden c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrado"));
        
        c = mc.toEntity(dto);
        c.setEmpleado(m);
        c.setEstado(tp);
        c.setCliente(t);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Orden> optionalOrden=rc.findById(id);
        if(optionalOrden!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
