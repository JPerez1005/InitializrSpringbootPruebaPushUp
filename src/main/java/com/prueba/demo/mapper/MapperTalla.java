package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoTalla;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Talla;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperTalla {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperTalla(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoTalla toDto(Talla c) {
        return modelMapper.map(c, DtoTalla.class);
    }

    public Talla toEntity(DtoTalla dc) {
        return modelMapper.map(dc, Talla.class);
    }
}
