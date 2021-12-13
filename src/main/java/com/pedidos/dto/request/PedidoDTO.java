package com.pedidos.dto.request;


import com.pedidos.entity.Pedido;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO extends Pedido {

    private Long id;

    @Valid
    @NotEmpty
    private List<ItemDTO> itens;

}
