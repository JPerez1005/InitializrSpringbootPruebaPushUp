package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoOrden;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceOrden;
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
@RequestMapping("/Orden")
public class ControllerOrden {
    
    @Autowired ServiceOrden sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarOrden(
        @RequestBody DtoOrden dc,
        @RequestParam Long idEmpleado,
        @RequestParam Long idEstado,
        @RequestParam Long idCliente
        ){
        ResponseEntity mensaje=sc.create(dc,idEmpleado,idEstado,idCliente);
        return mensaje;
    }

    @PutMapping("/modificar")
    public ResponseEntity modificarOrden(
        @RequestBody DtoOrden dc
        ,@RequestParam Long id
        ,@RequestParam Long idEmpleado
        ,@RequestParam Long idEstado
        ,@RequestParam Long idCliente
        ){
        ResponseEntity mensaje=sc.update(id, dc, idEmpleado,idEstado,idCliente);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoOrden>> listarOrden() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoOrden> c=(List<DtoOrden>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoOrden> buscarOrden(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarOrden(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
