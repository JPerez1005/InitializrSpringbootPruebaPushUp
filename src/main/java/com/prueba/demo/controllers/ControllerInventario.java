package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoInventario;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceInventario;
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
@RequestMapping("/Inventario")
public class ControllerInventario {
    
    @Autowired ServiceInventario sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarInventario(
        @RequestBody DtoInventario dc,
        @RequestParam Long idPrenda,
        @RequestParam Long idTalla
        ){
        ResponseEntity mensaje=sc.create(dc,idPrenda,idTalla);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoInventario>> listarInventario() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoInventario> c=(List<DtoInventario>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoInventario> buscarInventario(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarInventario(
        @RequestBody DtoInventario dc
        ,@RequestParam Long idInventario
        ,@RequestParam Long idPrenda
        ,@RequestParam Long idTalla
        ){
        ResponseEntity mensaje=sc.update(idInventario, dc, idPrenda,idTalla);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarInventario(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
