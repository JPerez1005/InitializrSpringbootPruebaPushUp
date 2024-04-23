package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoGenero;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Genero;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperGenero {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperGenero(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoGenero toDto(Genero c) {
        return modelMapper.map(c, DtoGenero.class);
    }

    public Genero toEntity(DtoGenero dc) {
        return modelMapper.map(dc, Genero.class);
    }
}
