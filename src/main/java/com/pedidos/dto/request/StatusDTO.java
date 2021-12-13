package com.pedidos.dto.request;

import com.pedidos.enums.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {

    private PedidoStatus status;
    private int itensAprovados;
    private BigDecimal valorAprovado;


    @Pattern(regexp = "[^\\d]",message = "CODIGO_PEDIDO_INVALIDO")
    private Long pedido;


}
