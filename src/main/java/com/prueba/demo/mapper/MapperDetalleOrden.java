package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDepartamento;
import com.prueba.demo.dto.DtoDetalleOrden;
import com.prueba.demo.models.Departamento;
import com.prueba.demo.models.DetalleOrden;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperDetalleOrden {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperDetalleOrden(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoDetalleOrden toDto(DetalleOrden c) {
        return modelMapper.map(c, DtoDetalleOrden.class);
    }

    public DetalleOrden toEntity(DtoDetalleOrden dc) {
        return modelMapper.map(dc, DetalleOrden.class);
    }
}
