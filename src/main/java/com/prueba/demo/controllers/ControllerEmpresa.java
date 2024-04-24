package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoEmpresa;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceEmpresa;
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
@RequestMapping("/Empresa")
public class ControllerEmpresa {
    
    @Autowired ServiceEmpresa sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarEmpresa(@RequestBody DtoEmpresa dc,@RequestParam Long idMunicipio){
        ResponseEntity mensaje=sc.create(dc,idMunicipio);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoEmpresa>> listarEmpresa() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoEmpresa> c=(List<DtoEmpresa>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoEmpresa> buscarEmpresa(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarEmpresa(
        @RequestBody DtoEmpresa dc
        ,@RequestParam Long idEmpresa
        ,@RequestParam Long idMunicipio){
        ResponseEntity mensaje=sc.update(idEmpresa, dc, idMunicipio);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarEmpresa(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
