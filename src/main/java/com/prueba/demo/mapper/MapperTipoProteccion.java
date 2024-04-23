package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoTipoProteccion;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.TipoProteccion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperTipoProteccion {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperTipoProteccion(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoTipoProteccion toDto(TipoProteccion c) {
        return modelMapper.map(c, DtoTipoProteccion.class);
    }

    public TipoProteccion toEntity(DtoTipoProteccion dc) {
        return modelMapper.map(dc, TipoProteccion.class);
    }
}
