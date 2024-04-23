package com.prueba.demo.dto;

import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoEmpresa {
    private Long id;
    private String nit;
    private String razonSocial;
    private String representanteLegal;
    private String fechaCreacion;
    private DtoMunicipio municipio;
}
