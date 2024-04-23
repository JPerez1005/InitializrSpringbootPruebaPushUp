package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoDetalleVenta;
import com.prueba.demo.dto.DtoInsumoPrendas;
import com.prueba.demo.models.DetalleVenta;
import com.prueba.demo.models.InsumoPrendas;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperInsumoPrendas {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperInsumoPrendas(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoInsumoPrendas toDto(InsumoPrendas c) {
        return modelMapper.map(c, DtoInsumoPrendas.class);
    }

    public InsumoPrendas toEntity(DtoInsumoPrendas dc) {
        return modelMapper.map(dc, InsumoPrendas.class);
    }
}
