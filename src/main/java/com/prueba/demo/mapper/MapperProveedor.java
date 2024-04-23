package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoProveedor;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Proveedor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperProveedor {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperProveedor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoProveedor toDto(Proveedor c) {
        return modelMapper.map(c, DtoProveedor.class);
    }

    public Proveedor toEntity(DtoProveedor dc) {
        return modelMapper.map(dc, Proveedor.class);
    }
}
