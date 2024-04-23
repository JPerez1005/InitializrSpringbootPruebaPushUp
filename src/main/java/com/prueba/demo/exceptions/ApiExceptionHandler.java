package com.prueba.demo.exceptions;

import com.prueba.demo.common.StandarizedApiExceptionResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author perez
 */
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandarizedApiExceptionResponse> handleUnknownHostException(Exception ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de conexion","erorr-1024",ex.getMessage());
        return new ResponseEntity(response, HttpStatus.PARTIAL_CONTENT);
    }
    
    @ExceptionHandler(EmptyDataException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> EmptyDataException(EmptyDataException ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de datos",ex.getCode(),ex.getMessage());
        return new ResponseEntity(response, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(NoAuthorizedException.class)
    public ResponseEntity<StandarizedApiExceptionResponse> NoAuthorizedException(NoAuthorizedException ex) {
        StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("No estás autorizado",ex.getCode(),ex.getMessage());
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }
}
