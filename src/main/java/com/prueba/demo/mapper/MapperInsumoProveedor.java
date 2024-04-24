package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoInsumoPrendas;
import com.prueba.demo.dto.DtoInsumoProveedor;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.InsumoPrendas;
import com.prueba.demo.models.InsumoProveedor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperInsumoProveedor {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperInsumoProveedor(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoInsumoProveedor toDto(InsumoProveedor c) {
        return modelMapper.map(c, DtoInsumoProveedor.class);
    }

    public InsumoProveedor toEntity(DtoInsumoProveedor dc) {
        return modelMapper.map(dc, InsumoProveedor.class);
    }
}
