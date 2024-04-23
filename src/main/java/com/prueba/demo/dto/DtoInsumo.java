package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoInsumo {
    private Long id;
    private String nombre;
    private double valor_unit;
    private double stock_min;
    private double stock_max;
}
