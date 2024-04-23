package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoInsumoProveedor {
    private Long id;
    private DtoProveedor proveedor;
    private DtoInsumo insumo;
}
