package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoEmpresa;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Empresa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperEmpresa {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperEmpresa(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoEmpresa toDto(Empresa c) {
        return modelMapper.map(c, DtoEmpresa.class);
    }

    public Empresa toEntity(DtoEmpresa dc) {
        return modelMapper.map(dc, Empresa.class);
    }
}
