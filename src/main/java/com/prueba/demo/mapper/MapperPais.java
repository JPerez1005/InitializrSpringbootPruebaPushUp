package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoPais;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Pais;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperPais {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperPais(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoPais toDto(Pais c) {
        return modelMapper.map(c, DtoPais.class);
    }

    public Pais toEntity(DtoPais dc) {
        return modelMapper.map(dc, Pais.class);
    }
}
