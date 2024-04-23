package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoOrden {
    private Long id;
    private String fecha;
    private DtoEmpleado empleado;
    private DtoEstado estado;
    private DtoCliente cliente;
}
