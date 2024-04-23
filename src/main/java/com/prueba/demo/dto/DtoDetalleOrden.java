package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoDetalleOrden {
    private Long id;
    private int cantidad_producir;
    private int cantidad_producida;
    private DtoOrden orden;
    private DtoPrenda prenda;
    private DtoColor color;
    private DtoEstado estado;
}
