package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoMunicipio {
    private Long id;
    private String nombre;
    private DtoDepartamento departamento;
}
