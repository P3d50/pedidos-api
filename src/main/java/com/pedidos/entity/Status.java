package com.pedidos.entity;

import com.pedidos.enums.PedidoStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    PedidoStatus status;
    int itensAprovados;
    BigDecimal valorAprovado;
    Long pedido;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public int getItensAprovados() {
        return itensAprovados;
    }

    public void setItensAprovados(int itensAprovados) {
        this.itensAprovados = itensAprovados;
    }

    public BigDecimal getValorAprovado() {
        return valorAprovado;
    }

    public void setValorAprovado(BigDecimal valorAprovado) {
        this.valorAprovado = valorAprovado;
    }

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }
}
