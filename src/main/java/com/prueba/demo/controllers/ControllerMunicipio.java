package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoMunicipio;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceMunicipio;
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
@RequestMapping("/Municipio")
public class ControllerMunicipio {
    
    @Autowired ServiceMunicipio sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarMunicipio(@RequestBody DtoMunicipio dc,@RequestParam Long idDepartamento){
        ResponseEntity mensaje=sc.create(dc,idDepartamento);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoMunicipio>> listarMunicipio() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoMunicipio> c=(List<DtoMunicipio>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoMunicipio> buscarMunicipio(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarMunicipio(
        @RequestBody DtoMunicipio dc
        ,@RequestParam Long idMunicipio
        ,@RequestParam Long idDepartamento){
        ResponseEntity mensaje=sc.update(idMunicipio, dc, idDepartamento);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarMunicipio(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
