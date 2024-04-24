package com.prueba.demo.controllers;

import com.prueba.demo.dto.DtoInsumoPrendas;
import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import com.prueba.demo.service.ServiceInsumoPrendas;
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
@RequestMapping("/InsumoPrendas")
public class ControllerInsumoPrendas {
    
    @Autowired ServiceInsumoPrendas sc;
    
    @PostMapping("/agregar")
    public ResponseEntity guardarInsumoPrendas(
        @RequestBody DtoInsumoPrendas dc,
        @RequestParam Long idInsumo,
        @RequestParam Long idPrenda
        ){
        ResponseEntity mensaje=sc.create(dc,idInsumo,idPrenda);
        return mensaje;
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<DtoInsumoPrendas>> listarInsumoPrendas() 
            throws EmptyDataException,NoAuthorizedException{
        List<DtoInsumoPrendas> c=(List<DtoInsumoPrendas>) sc.getAll();
        return new ResponseEntity<>(c,HttpStatus.OK);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<DtoInsumoPrendas> buscarInsumoPrendas(@PathVariable Long id) 
            throws EmptyDataException,NoAuthorizedException{
        return sc.getById(id);
    }
    
    @PutMapping("/modificar")
    public ResponseEntity modificarInsumoPrendas(
        @RequestBody DtoInsumoPrendas dc
        ,@RequestParam Long idInsumoYPrendas
        ,@RequestParam Long idInsumo
        ,@RequestParam Long idPrenda
        ){
        ResponseEntity mensaje=sc.update(idInsumoYPrendas, dc, idInsumo,idPrenda);
        return mensaje;
    }
    
    @DeleteMapping("/eliminar")
    public ResponseEntity eliminarInsumoPrendas(
        @RequestParam Long id){
        ResponseEntity mensaje=sc.delete(id);
        return mensaje;
    }
    
}
