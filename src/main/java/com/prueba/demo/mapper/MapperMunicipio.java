package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoMunicipio;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Municipio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperMunicipio {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperMunicipio(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoMunicipio toDto(Municipio c) {
        return modelMapper.map(c, DtoMunicipio.class);
    }

    public Municipio toEntity(DtoMunicipio dc) {
        return modelMapper.map(dc, Municipio.class);
    }
}
