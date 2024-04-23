package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoProveedor {
    private Long id;
    private String nit_proveedor;
    private String nombre;
    private DtoTipoPersona tipoPersona;
    private DtoMunicipio municipio;
}
