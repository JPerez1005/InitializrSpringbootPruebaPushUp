package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoPrenda {
    private Long id;
    private String nombre;
    private double ValorUnitCop;
    private double ValorUnitUsd;
    private String codigo;
    private DtoEstado estado;
    private DtoTipoProteccion tipoProteccion;
    private DtoGenero genero;
}
