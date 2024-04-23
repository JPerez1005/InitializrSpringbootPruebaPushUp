package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoFormaPago;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.FormaPago;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperFormaPago {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperFormaPago(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoFormaPago toDto(FormaPago c) {
        return modelMapper.map(c, DtoFormaPago.class);
    }

    public FormaPago toEntity(DtoFormaPago dc) {
        return modelMapper.map(dc, FormaPago.class);
    }
}
