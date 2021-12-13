package com.pedidos.mapper;

import com.pedidos.dto.request.StatusDTO;
import com.pedidos.entity.Status;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatusMapper {

    StatusMapper INSTANCE = Mappers.getMapper(StatusMapper.class);

    Status toModel(StatusDTO statusDTO);
}
