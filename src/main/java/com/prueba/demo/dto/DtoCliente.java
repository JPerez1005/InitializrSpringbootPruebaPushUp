package com.prueba.demo.dto;

import java.util.Date;
import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoCliente {
    private Long id;
    private String nombre;
    private String id_cliente;
    private Date fecha_registro;
    private DtoMunicipio municipio;
    private DtoTipoPersona tipoPersona;
}
