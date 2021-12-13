package com.pedidos.entity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "pedido_table")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
    private List<Item> itens;



}
