package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoInventario {
    private Long id;
    private String CodInv;
    private double ValorVtaCop;
    private double ValorVtaUsd;
    private DtoPrenda prenda;
    private DtoTalla talla;
}
