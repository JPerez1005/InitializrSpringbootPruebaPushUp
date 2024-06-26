package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoEmpleado;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceEmpleado;
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
@RequestMapping("/Empleado")
public class ControllerEmpleado {
    
    @Autowired ServiceEmpleado sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarEmpleado(
        @RequestBody DtoEmpleado dc,
        @RequestParam Long idMunicipio,
        @RequestParam Long idCargos
        ){
        ResponseEntity mensaje=sc.create(dc,idMunicipio,idCargos);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoEmpleado>> listarEmpleado() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoEmpleado> c=(List<DtoEmpleado>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoEmpleado> buscarEmpleado(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarEmpleado(
        @RequestBody DtoEmpleado dc
        ,@RequestParam Long id
        ,@RequestParam Long idMunicipios
        ,@RequestParam Long idCargos
        ){
        ResponseEntity mensaje=sc.update(id, dc, idMunicipios,idCargos);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarEmpleado(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
