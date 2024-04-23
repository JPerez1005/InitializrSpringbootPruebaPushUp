package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.models.DetalleVenta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperDetalleVenta {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperDetalleVenta(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoDetalleVenta toDto(DetalleVenta c) {
        return modelMapper.map(c, DtoDetalleVenta.class);
    }

    public DetalleVenta toEntity(DtoDetalleVenta dc) {
        return modelMapper.map(dc, DetalleVenta.class);
    }
}
