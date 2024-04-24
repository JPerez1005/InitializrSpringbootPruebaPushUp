package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoVenta;
import com.prueba.demo.dto.DtoFormaPago;
import com.prueba.demo.dto.DtoEmpleado;
import com.prueba.demo.dto.DtoCliente;
import com.prueba.demo.mapper.MapperVenta;
import com.prueba.demo.repositories.RepositoryVenta;
import com.prueba.demo.service.ServiceVenta;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Venta;
import com.prueba.demo.models.FormaPago;
import com.prueba.demo.models.Empleado;
import com.prueba.demo.models.Cliente;
import com.prueba.demo.repositories.RepositoryFormaPago;
import com.prueba.demo.repositories.RepositoryEmpleado;
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
public class ServiceImplVenta implements ServiceVenta<DtoVenta>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryVenta rc;
    
    @Autowired private RepositoryFormaPago rv;
    
    @Autowired private RepositoryEmpleado ri;

    @Autowired private RepositoryCliente rt;
    
    @Autowired private MapperVenta mc;
    
    @Override
    public List<DtoVenta> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoVenta.class);
    }

    @Override
    public ResponseEntity<Optional<DtoVenta>> getById(Long id) {
        if(usi.findById(rc, DtoVenta.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoVenta.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(
        DtoVenta dto,
        Long idFormaPago,
        Long idEmpleado,
        Long idCliente) {
        FormaPago m=usi.convertidorAEntidades(rv, DtoFormaPago.class, idFormaPago)
                .orElseThrow(()->new EntityNotFoundException
                ("FormaPago no encontrada"));
        
        Empleado tp=usi.convertidorAEntidades(ri, DtoFormaPago.class, idFormaPago)
                .orElseThrow(()->new EntityNotFoundException
                ("Empleado no encontrado"));
        
        Cliente t=usi.convertidorAEntidades(rt, DtoCliente.class, idCliente)
                .orElseThrow(()->new EntityNotFoundException
                ("Cliente no encontrada"));

        Venta c = mc.toEntity(dto);
        c.setFormaPago(m);
        c.setEmpleado(tp);
        c.setCliente(t);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(
        Long id,
        DtoVenta dto,
        Long idFormaPago,
        Long idEmpleado,
        Long idCliente) {
        FormaPago m=usi.convertidorAEntidades(rv, DtoFormaPago.class, idFormaPago)
                .orElseThrow(()->new EntityNotFoundException
                ("FormaPago no encontrado"));
        
        Empleado tp=usi.convertidorAEntidades(ri, DtoEmpleado.class, idFormaPago)
                .orElseThrow(()->new EntityNotFoundException
                ("Empleado no encontrado"));

        Cliente t=usi.convertidorAEntidades(rt, DtoCliente.class, idCliente)
                .orElseThrow(()->new EntityNotFoundException
                ("Cliente no encontrada"));
        
        Venta c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrado"));
        
        c = mc.toEntity(dto);
        c.setFormaPago(m);
        c.setEmpleado(tp);
        c.setCliente(t);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Venta> optionalVenta=rc.findById(id);
        if(optionalVenta!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
