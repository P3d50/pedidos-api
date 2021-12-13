package com.pedidos.controller;
import com.pedidos.dto.request.PedidoDTO;
import com.pedidos.dto.request.StatusDTO;
import com.pedidos.dto.response.MessageResponseDTO;
import com.pedidos.exception.PedidoNotFoundException;
import com.pedidos.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PedidoController {

    private final PedidoService pedidoService;

    @RequestMapping(value="/pedido",method=RequestMethod.GET)
    public List<PedidoDTO> listAll(){
        return pedidoService.listAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value="/pedido",method=RequestMethod.POST)
    public MessageResponseDTO postPedido(@RequestBody @Valid  PedidoDTO pedidoDTO){
        return pedidoService.createPedido(pedidoDTO);
    }

    @RequestMapping(value="/pedido/{id}",method=RequestMethod.GET)
    public PedidoDTO findById(@PathVariable Long id) throws PedidoNotFoundException {
        return pedidoService.findById(id);
    }


    @RequestMapping(value="/pedido/{id}",method=RequestMethod.PUT)
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PedidoDTO pedidoDTO) throws PedidoNotFoundException {
        return pedidoService.updateById(id,pedidoDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value="/pedido/{id}",method=RequestMethod.DELETE)
    public void deleteById(@PathVariable Long id) throws PedidoNotFoundException {
        pedidoService.delete(id);
    }


    @RequestMapping(value="/status", method=RequestMethod.POST)
    public MessageResponseDTO defineStatus(@RequestBody @Valid StatusDTO statusDTO){

        return pedidoService.verificaPedido(statusDTO);
    }
}
