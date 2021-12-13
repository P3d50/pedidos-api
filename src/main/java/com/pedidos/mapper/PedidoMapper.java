package com.pedidos.mapper;


import com.pedidos.dto.request.PedidoDTO;
import com.pedidos.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    Pedido toModel(PedidoDTO pedidoDTO);

    PedidoDTO toDTO(Pedido pedido);
}
