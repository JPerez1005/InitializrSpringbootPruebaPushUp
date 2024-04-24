package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoVenta;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceVenta;
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
@RequestMapping("/Venta")
public class ControllerVenta {
    
    @Autowired ServiceVenta sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarVenta(
        @RequestBody DtoVenta dc,
        @RequestParam Long idFormaPago,
        @RequestParam Long idEmpleado,
        @RequestParam Long idCliente
        ){
        ResponseEntity mensaje=sc.create(dc,idFormaPago,idEmpleado,idCliente);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoVenta>> listarVenta() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoVenta> c=(List<DtoVenta>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoVenta> buscarVenta(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarVenta(
        @RequestBody DtoVenta dc
        ,@RequestParam Long idVenta
        ,@RequestParam Long idFormaPago
        ,@RequestParam Long idEmpleado
        ,@RequestParam Long idcliente
        ){
        ResponseEntity mensaje=sc.update(idVenta, dc, idFormaPago,idEmpleado,idcliente);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarVenta(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
