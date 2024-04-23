package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoTipoEstado;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.TipoEstado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperTipoEstado {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperTipoEstado(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoTipoEstado toDto(TipoEstado c) {
        return modelMapper.map(c, DtoTipoEstado.class);
    }

    public TipoEstado toEntity(DtoTipoEstado dc) {
        return modelMapper.map(dc, TipoEstado.class);
    }
}
