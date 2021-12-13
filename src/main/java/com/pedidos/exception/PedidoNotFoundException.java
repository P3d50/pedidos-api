package com.pedidos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends Exception{

    public PedidoNotFoundException(long id){
        super("Pedido not found with ID:"+id);
    }

    public PedidoNotFoundException(String str){
        super("Pedido not found: "+str);
    }
}
