package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoDetalleVenta {
    private Long id;
    private int cantidad;
    private double valor_unit;
    private DtoVenta venta;
    private DtoInventario inventario;
    private DtoTalla talla;
}
