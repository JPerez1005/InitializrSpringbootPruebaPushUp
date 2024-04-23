package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoEstado;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperEstado {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperEstado(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoEstado toDto(Estado c) {
        return modelMapper.map(c, DtoEstado.class);
    }

    public Estado toEntity(DtoEstado dc) {
        return modelMapper.map(dc, Estado.class);
    }
}
