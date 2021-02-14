package com.example.hrapp.hrapp.MapStruct;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import java.util.List;


@MapperConfig
public interface BaseMapper<D, E> {

    E mapToEntity(D dto);

    D mapToDto(E entity);

    List<E> mapToEntity(List<D> dtoList);

    List<D> mapToDto(List<E> entityList);
}

