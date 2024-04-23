package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoCargos;
import com.prueba.demo.models.Cargos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperCargos {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperCargos(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoCargos toDto(Cargos c) {
        return modelMapper.map(c, DtoCargos.class);
    }

    public Cargos toEntity(DtoCargos dc) {
        return modelMapper.map(dc, Cargos.class);
    }
}
