package com.prueba.demo.controllers;

import com.prueba.demo.exceptions.EmptyDataException;
import com.prueba.demo.exceptions.NoAuthorizedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author perez
*/

@RestController
public class UniversalControllerImplementation {
    // Método listar que acepta cualquier tipo de servicio con un método getAll()
    public <T> ResponseEntity<List<T>> listar(Object service) 
            throws EmptyDataException, NoAuthorizedException {
        try {
            // Usa la reflexión para obtener el método getAll() del servicio
            Method getAllMethod = service.getClass().getMethod("getAll");
            
            // Invoca el método getAll() del servicio
            List<T> c = (List<T>) getAllMethod.invoke(service);
            
            // Retorna los registros en una ResponseEntity
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new IllegalArgumentException("El servicio no tiene un método getAll() válido.", e);
        }
    }
    
    /* 
         ____                      _ _            
        / ___|___  _ __  ___ _   _| | |_ __ _ ___ 
       | |   / _ \| '_ \/ __| | | | | __/ _` / __|
       | |__| (_) | | | \__ \ |_| | | || (_| \__ \
        \____\___/|_| |_|___/\__,_|_|\__\__,_|___/
    
                    __..--''``---....___   _..._    __
      /// //_.-'    .-/";  `        ``<._  ``.''_ `. / // /
     ///_.-' _..--.'_    \                    `( ) ) // //
     / (_..-' // (< _     ;_..__               ; `' / ///
      / // // //  `-._,_)' // / ``--...____..-' /// / //
    */
    
    //Consulta para listar fechas
    public <T> ResponseEntity<List<T>> listarFechas
        (Object service,
        Class<T> dtoClass,
        Function<T, String> fechaExtractor,
        int anio,
        String mes) 
        throws EmptyDataException, NoAuthorizedException {
        // Llama a listar y obtiene la respuesta
        ResponseEntity<List<T>> responseEntity = listar(service);

        // Obtiene la lista de la respuesta
        List<T> c = responseEntity.getBody();

        List<T> listFechas = c.stream()
            .filter(elemento -> {
                // Usa el extractor de fecha para obtener la fecha del elemento
                LocalDate fechaVenta = LocalDate.parse(fechaExtractor.apply(elemento));
                Month mesEnum;
                try {
                    mesEnum = Month.valueOf(mes.toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("El mes proporcionado es inválido. Use un nombre de mes válido en inglés (por ejemplo, 'JANUARY').");
                }
                return fechaVenta.getMonth() == mesEnum && fechaVenta.getYear() == anio;
            })
            .collect(Collectors.toList());
        // Devuelve la lista filtrada
        return new ResponseEntity<>(listFechas, HttpStatus.OK);
    }

        
}