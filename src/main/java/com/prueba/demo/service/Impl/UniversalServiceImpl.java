package com.prueba.demo.service.Impl;

import com.prueba.demo.exceptions.EmptyDataException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper; // Importa ModelMapper
import org.springframework.http.HttpStatus;

@Service
public class UniversalServiceImpl {
     private final ModelMapper modelMapper;

    public UniversalServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <Entity, Dto> List<Dto> getAll
        (JpaRepository<Entity, ?> repository, Class<Dto> dtoClass) 
        throws EmptyDataException{
        List<Entity> entities = repository.findAll();
        if(entities.isEmpty()){
            EmptyDataException e=new EmptyDataException("404 datos inexistentes",
                    "-Revisar UniversalServiceImpl || "
                            + "-Agregar mas datos || "
                            + "-verificar base de datos",HttpStatus.NOT_FOUND);
            throw e;
        }else{
            return entities.stream()
                .map(entity -> modelMapper.map(entity, dtoClass))
                .collect(Collectors.toList());
        }
    }
    

    
    public <Entity, Dto, ID> Optional<Dto> findById(JpaRepository<Entity, ID> repository, Class<Dto> dtoClass, ID id) {
        Optional<Entity> optionalEntity = repository.findById(id);
        return optionalEntity.map(entity -> modelMapper.map(entity, dtoClass));
    }
    
    public <Entity, Dto> Dto save(JpaRepository<Entity, ?> repository, Dto dto, Class<Entity> entityClass) {
        Entity entityToSave = modelMapper.map(dto, entityClass);
        Entity savedEntity = repository.save(entityToSave);
        return (Dto) modelMapper.map(savedEntity, dto.getClass());
    }
    
    public <Entity, Dto, ID> Optional<Entity> convertidorAEntidades(JpaRepository<Entity, ID> repository, Class<Dto> dtoClass, ID id){
        Optional<Entity> optionalEntity=repository.findById(id);
        optionalEntity.map(entity -> modelMapper.map(entity, dtoClass));
        return repository.findById(id);
    }
}
