package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoVenta;
import com.prueba.demo.models.Venta;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperVenta {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperVenta(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoVenta toDto(Venta c) {
        return modelMapper.map(c, DtoVenta.class);
    }

    public Venta toEntity(DtoVenta dc) {
        return modelMapper.map(dc, Venta.class);
    }
}
