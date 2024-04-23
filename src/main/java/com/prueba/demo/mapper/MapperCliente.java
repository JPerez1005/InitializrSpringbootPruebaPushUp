package com.prueba.demo.mapper;

import com.prueba.demo.dto.DtoCargos;
import com.prueba.demo.dto.DtoCliente;
import com.prueba.demo.models.Cargos;
import com.prueba.demo.models.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author perez
 */

@Component
public class MapperCliente {
    private final ModelMapper modelMapper;

    @Autowired
    public MapperCliente(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoCliente toDto(Cliente c) {
        return modelMapper.map(c, DtoCliente.class);
    }

    public Cliente toEntity(DtoCliente dc) {
        return modelMapper.map(dc, Cliente.class);
    }
}
