package com.prueba.demo.dto;

import java.util.Date;
import lombok.Data;

/**
 * @author perez
 */
@Data
public class DtoEmpleado {
    private Long id;
    private String nombre;
    private Date fecha_ingreso;
    private DtoMunicipio municipio;
    private DtoCargos cargos;
}
