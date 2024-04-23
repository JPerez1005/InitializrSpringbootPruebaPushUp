package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoTipoPersona;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.TipoPersona;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperTipoPersona {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperTipoPersona(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoTipoPersona toDto(TipoPersona c) {
        return modelMapper.map(c, DtoTipoPersona.class);
    }

    public TipoPersona toEntity(DtoTipoPersona dc) {
        return modelMapper.map(dc, TipoPersona.class);
    }
}
