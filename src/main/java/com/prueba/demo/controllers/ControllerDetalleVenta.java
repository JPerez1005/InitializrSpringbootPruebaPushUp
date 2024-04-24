package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceDetalleVenta;
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
@RequestMapping("/DetalleVenta")
public class ControllerDetalleVenta {
    
    @Autowired ServiceDetalleVenta sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarDetalleVenta(
        @RequestBody DtoDetalleVenta dc,
        @RequestParam Long idVenta,
        @RequestParam Long idInventario,
        @RequestParam Long idTalla
        ){
        ResponseEntity mensaje=sc.create(dc,idVenta,idInventario,idTalla);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoDetalleVenta>> listarDetalleVenta() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoDetalleVenta> c=(List<DtoDetalleVenta>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoDetalleVenta> buscarDetalleVenta(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarDetalleVenta(
        @RequestBody DtoDetalleVenta dc
        ,@RequestParam Long idDetalleDeVenta
        ,@RequestParam Long idVenta
        ,@RequestParam Long idInventario
        ,@RequestParam Long idTalla
        ){
        ResponseEntity mensaje=sc.update(idDetalleDeVenta, dc, idVenta,idInventario,idTalla);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarDetalleVenta(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
