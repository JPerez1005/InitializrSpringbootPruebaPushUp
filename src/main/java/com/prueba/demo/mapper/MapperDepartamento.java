package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoCargos;
import com.prueba.demo.dto.DtoCliente;
import com.prueba.demo.dto.DtoColor;
import com.prueba.demo.dto.DtoDepartamento;
import com.prueba.demo.models.Cargos;
import com.prueba.demo.models.Cliente;
import com.prueba.demo.models.Color;
import com.prueba.demo.models.Departamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperDepartamento {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperDepartamento(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoDepartamento toDto(Departamento c) {
        return modelMapper.map(c, DtoDepartamento.class);
    }

    public Departamento toEntity(DtoDepartamento dc) {
        return modelMapper.map(dc, Departamento.class);
    }
}
