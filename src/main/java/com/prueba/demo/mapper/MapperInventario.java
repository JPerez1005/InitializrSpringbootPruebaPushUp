package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoInventario;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Inventario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperInventario {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperInventario(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoInventario toDto(Inventario c) {
        return modelMapper.map(c, DtoInventario.class);
    }

    public Inventario toEntity(DtoInventario dc) {
        return modelMapper.map(dc, Inventario.class);
    }
}
