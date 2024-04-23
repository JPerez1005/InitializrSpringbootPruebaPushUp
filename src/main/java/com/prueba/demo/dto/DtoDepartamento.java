package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoDepartamento {
    private Long id;
    private String nombre;
    private DtoPais pais;
}
