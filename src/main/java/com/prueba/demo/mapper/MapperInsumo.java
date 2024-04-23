package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoInsumo;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Insumo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperInsumo {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperInsumo(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoInsumo toDto(Insumo c) {
        return modelMapper.map(c, DtoInsumo.class);
    }

    public Insumo toEntity(DtoInsumo dc) {
        return modelMapper.map(dc, Insumo.class);
    }
}
