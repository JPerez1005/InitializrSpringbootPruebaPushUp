package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoGenero;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceGenero;
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
@RequestMapping("/Genero")
public class ControllerGenero {
    
    @Autowired ServiceGenero sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarGenero(@RequestBody DtoGenero dc){
        ResponseEntity mensaje=sc.create(dc);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoGenero>> listarGenero() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoGenero> c=(List<DtoGenero>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoGenero> buscarGenero(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarGenero(
        @RequestBody DtoGenero dc
        ,@RequestParam Long id){
        ResponseEntity mensaje=sc.update(id, dc);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarGenero(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
