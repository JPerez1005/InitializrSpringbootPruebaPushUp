package com.prueba.demo.service.Impl;

import com.prueba.demo.dto.DtoEmpleado;
import com.prueba.demo.dto.DtoMunicipio;
import com.prueba.demo.dto.DtoCargos;
import com.prueba.demo.mapper.MapperEmpleado;
import com.prueba.demo.repositories.RepositoryEmpleado;
import com.prueba.demo.service.ServiceEmpleado;
import com.prueba.demo.exceptions.Constantes;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.ExceptionUtil;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.models.Empleado;
import com.prueba.demo.models.Municipio;
import com.prueba.demo.models.Cargos;
import com.prueba.demo.repositories.RepositoryMunicipio;
import com.prueba.demo.repositories.RepositoryCargos;
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
public class ServiceImplEmpleado implements ServiceEmpleado<DtoEmpleado>{
    @Autowired private UniversalServiceImpl usi;
    
    @Autowired private RepositoryEmpleado rc;
    
    @Autowired private RepositoryMunicipio rm;
    
    @Autowired private RepositoryCargos rtp;
    
    @Autowired private MapperEmpleado mc;
    
    @Override
    public List<DtoEmpleado> getAll()
    throws EmptyDataException, NoAuthorizedException {
        return usi.getAll(rc, DtoEmpleado.class);
    }

    @Override
    public ResponseEntity<Optional<DtoEmpleado>> getById(Long id) {
        if(usi.findById(rc, DtoEmpleado.class, id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(usi.findById(rc, DtoEmpleado.class, id),HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> create(DtoEmpleado dto,Long idmunicipio,Long idCargos) {
        Municipio m=usi.convertidorAEntidades(rm, DtoMunicipio.class, idmunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("Municipio no encontrado"));
        
        Cargos tp=usi.convertidorAEntidades(rtp, DtoMunicipio.class, idmunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("Cargos no encontrado"));
        
        Empleado c = mc.toEntity(dto);
        c.setMunicipio(m);
        c.setCargos(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> update(Long id, DtoEmpleado dto,Long idmunicipio,Long idCargos) {
        Municipio m=usi.convertidorAEntidades(rm, DtoMunicipio.class, idmunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("Municipio no encontrado"));
        
        Cargos tp=usi.convertidorAEntidades(rtp, DtoCargos.class, idmunicipio)
                .orElseThrow(()->new EntityNotFoundException
                ("Cargos no encontrado"));
        
        Empleado c = rc.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empleado no encontrado"));
        
        c = mc.toEntity(dto);
        c.setMunicipio(m);
        c.setCargos(tp);
        rc.save(c);
        return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        Optional<Empleado> optionalEmpleado=rc.findById(id);
        if(optionalEmpleado!=null){
            rc.deleteById(id);
            return ExceptionUtil.getResponseEntity(Constantes.OK, HttpStatus.GONE);
        }else{
            return ExceptionUtil.getResponseEntity(Constantes.INVALID_DATA, HttpStatus.NOT_FOUND);
        }
    }
}
