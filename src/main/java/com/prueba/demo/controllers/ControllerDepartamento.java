package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoDepartamento;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceDepartamento;
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
@RequestMapping("/Departamento")
public class ControllerDepartamento {
    
    @Autowired ServiceDepartamento sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarDepartamento(@RequestBody DtoDepartamento dc,
    @RequestParam Long idPais){
        ResponseEntity mensaje=sc.create(dc,idPais);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoDepartamento>> listarDepartamento() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoDepartamento> c=(List<DtoDepartamento>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoDepartamento> buscarDepartamento(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarDepartamento(
        @RequestBody DtoDepartamento dc
        ,@RequestParam Long idDepartamento
        ,@RequestParam Long idPais){
        ResponseEntity mensaje=sc.update(idDepartamento, dc, idPais);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarDepartamento(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
