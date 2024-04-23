package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoTipoProteccion;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceTipoProteccion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
 */
@RestController
@RequestMapping("/TipoProteccion")
public class ControllerTipoProteccion {
    
    @Autowired ServiceTipoProteccion sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarTipoProteccion(@RequestBody DtoTipoProteccion dc){
        ResponseEntity mensaje=sc.create(dc);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoTipoProteccion>> listarTipoProteccion() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoTipoProteccion> c=(List<DtoTipoProteccion>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoTipoProteccion> buscarTipoProteccion(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarTipoProteccion(
        @RequestBody DtoTipoProteccion dc
        ,@RequestParam Long id){
        ResponseEntity mensaje=sc.update(id, dc);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarTipoProteccion(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
