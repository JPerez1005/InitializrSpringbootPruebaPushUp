package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoInsumoPrendas {
    private Long id;
    private int cantidad;
    private DtoInsumo insumo;
    private DtoPrenda prenda;
}
