package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoPrenda;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServicePrenda;
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
@RequestMapping("/Prenda")
public class ControllerPrenda {
    
    @Autowired ServicePrenda sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarPrenda(
        @RequestBody DtoPrenda dc,
        @RequestParam Long idTipoProteccion,
        @RequestParam Long idEstado,
        @RequestParam Long idGenero
        ){
        ResponseEntity mensaje=sc.create(dc,idTipoProteccion,idEstado,idGenero);
        return mensaje;
    }

    @PutMapping("/modificar")
    public ResponseEntity modificarPrenda(
        @RequestBody DtoPrenda dc
        ,@RequestParam Long id
        ,@RequestParam Long idTipoProteccion
        ,@RequestParam Long idEstado
        ,@RequestParam Long idGenero
        ){
        ResponseEntity mensaje=sc.update(id, dc, idTipoProteccion,idEstado,idGenero);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoPrenda>> listarPrenda() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoPrenda> c=(List<DtoPrenda>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoPrenda> buscarPrenda(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarPrenda(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
