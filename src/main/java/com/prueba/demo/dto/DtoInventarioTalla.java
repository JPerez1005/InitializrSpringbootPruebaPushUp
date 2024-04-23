package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoInventarioTalla {
    private Long id;
    private DtoInventario inventario;
    private DtoTalla talla;
}
