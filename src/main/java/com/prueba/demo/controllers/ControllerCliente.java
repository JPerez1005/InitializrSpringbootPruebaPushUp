package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoCliente;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceCliente;
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
@RequestMapping("/Cliente")
public class ControllerCliente {
    
    @Autowired ServiceCliente sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarCliente(
        @RequestBody DtoCliente dc,
        @RequestParam Long idMunicipio,
        @RequestParam Long idtipoPersona
        ){
        ResponseEntity mensaje=sc.create(dc,idMunicipio,idtipoPersona);
        return mensaje;
    }

    @PutMapping("/modificar")
    public ResponseEntity modificarCliente(
        @RequestBody DtoCliente dc
        ,@RequestParam Long idCliente
        ,@RequestParam Long idMunicipio
        ,@RequestParam Long idtipoPersona
        ){
        ResponseEntity mensaje=sc.update(idCliente, dc, idMunicipio,idtipoPersona);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoCliente>> listarCliente() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoCliente> c=(List<DtoCliente>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoCliente> buscarCliente(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarCliente(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
