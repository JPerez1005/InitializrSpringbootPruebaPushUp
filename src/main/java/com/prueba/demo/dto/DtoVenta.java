package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoVenta {
    private Long id;
    private String fecha;
    private DtoFormaPago formaPago;
    private DtoEmpleado empleado;
    private DtoCliente cliente;
}
