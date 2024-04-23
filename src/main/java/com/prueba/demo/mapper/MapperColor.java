package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoCargos;
import com.prueba.demo.dto.DtoCliente;
import com.prueba.demo.dto.DtoColor;
import com.prueba.demo.models.Cargos;
import com.prueba.demo.models.Cliente;
import com.prueba.demo.models.Color;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperColor {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperColor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoColor toDto(Color c) {
        return modelMapper.map(c, DtoColor.class);
    }

    public Color toEntity(DtoColor dc) {
        return modelMapper.map(dc, Color.class);
    }
}
