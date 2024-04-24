package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoInsumoProveedor;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceInsumoProveedor;
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
@RequestMapping("/InsumoProveedor")
public class ControllerInsumoProveedor {
    
    @Autowired ServiceInsumoProveedor sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarInsumoProveedor(
        @RequestBody DtoInsumoProveedor dc,
        @RequestParam Long id,
        @RequestParam Long id2
        ){
        ResponseEntity mensaje=sc.create(dc,id,id2);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoInsumoProveedor>> listarInsumoProveedor() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoInsumoProveedor> c=(List<DtoInsumoProveedor>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoInsumoProveedor> buscarInsumoProveedor(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarInsumoProveedor(
        @RequestBody DtoInsumoProveedor dc
        ,@RequestParam Long id
        ,@RequestParam Long id2
        ,@RequestParam Long id3
        ){
        ResponseEntity mensaje=sc.update(id, dc, id2,id3);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarInsumoProveedor(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
