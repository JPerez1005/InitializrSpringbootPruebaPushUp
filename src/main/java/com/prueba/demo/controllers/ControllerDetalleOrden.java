package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoDetalleOrden;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceDetalleOrden;
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
@RequestMapping("/DetalleOrden")
public class ControllerDetalleOrden {
    
    @Autowired ServiceDetalleOrden sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarDetalleOrden(
        @RequestBody DtoDetalleOrden dc,
        @RequestParam Long id,
        @RequestParam Long id2,
        @RequestParam Long id3,
        @RequestParam Long id4
        ){
        ResponseEntity mensaje=sc.create(dc,id,id2,id3,id4);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoDetalleOrden>> listarDetalleOrden() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoDetalleOrden> c=(List<DtoDetalleOrden>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoDetalleOrden> buscarDetalleOrden(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarDetalleOrden(
        @RequestBody DtoDetalleOrden dc
        ,@RequestParam Long id
        ,@RequestParam Long id2
        ,@RequestParam Long id3
        ,@RequestParam Long id4
        ,@RequestParam Long id5
        ){
        ResponseEntity mensaje=sc.update(id, dc, id2,id3,id4,id5);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarDetalleOrden(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
