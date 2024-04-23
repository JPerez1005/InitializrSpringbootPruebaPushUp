package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoOrden;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Orden;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperOrden {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperOrden(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoOrden toDto(Orden c) {
        return modelMapper.map(c, DtoOrden.class);
    }

    public Orden toEntity(DtoOrden dc) {
        return modelMapper.map(dc, Orden.class);
    }
}
