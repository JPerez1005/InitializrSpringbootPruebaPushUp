package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoEmpleado;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.Empleado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperEmpleado {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperEmpleado(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoEmpleado toDto(Empleado c) {
        return modelMapper.map(c, DtoEmpleado.class);
    }

    public Empleado toEntity(DtoEmpleado dc) {
        return modelMapper.map(dc, Empleado.class);
    }
}
