package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoPrenda;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Prenda;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperPrenda {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperPrenda(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoPrenda toDto(Prenda c) {
        return modelMapper.map(c, DtoPrenda.class);
    }

    public Prenda toEntity(DtoPrenda dc) {
        return modelMapper.map(dc, Prenda.class);
    }
}
