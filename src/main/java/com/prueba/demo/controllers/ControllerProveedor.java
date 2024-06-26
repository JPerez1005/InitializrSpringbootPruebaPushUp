package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoProveedor;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceProveedor;
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
@RequestMapping("/Proveedor")
public class ControllerProveedor {
    
    @Autowired ServiceProveedor sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarProveedor(
        @RequestBody DtoProveedor dc,
        @RequestParam Long idMunicipio,
        @RequestParam Long idTipoPersona
        ){
        ResponseEntity mensaje=sc.create(dc,idMunicipio,idTipoPersona);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoProveedor>> listarProveedor() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoProveedor> c=(List<DtoProveedor>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoProveedor> buscarProveedor(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarProveedor(
        @RequestBody DtoProveedor dc
        ,@RequestParam Long id
        ,@RequestParam Long idMunicipio
        ,@RequestParam Long idTipoPersona
        ){
        ResponseEntity mensaje=sc.update(id, dc, idMunicipio,idTipoPersona);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarProveedor(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
