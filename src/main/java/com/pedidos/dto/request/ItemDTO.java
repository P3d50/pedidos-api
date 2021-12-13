package com.pedidos.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;

    @Size(min=2,max=100)
    private String descricao;

    private BigDecimal precoUnitario;

    private int qtd;


}
