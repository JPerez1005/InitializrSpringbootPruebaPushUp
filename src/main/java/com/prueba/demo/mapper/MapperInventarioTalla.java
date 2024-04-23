package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoInventarioTalla;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.InventarioTalla;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperInventarioTalla {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperInventarioTalla(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoInventarioTalla toDto(InventarioTalla c) {
        return modelMapper.map(c, DtoInventarioTalla.class);
    }

    public InventarioTalla toEntity(DtoInventarioTalla dc) {
        return modelMapper.map(dc, InventarioTalla.class);
    }
}
