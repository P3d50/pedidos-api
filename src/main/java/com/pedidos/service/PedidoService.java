package com.pedidos.service;


import com.pedidos.dto.request.PedidoDTO;
import com.pedidos.dto.request.StatusDTO;
import com.pedidos.dto.response.MessageResponseDTO;
import com.pedidos.entity.Item;
import com.pedidos.entity.Pedido;
import com.pedidos.entity.Status;
import com.pedidos.enums.PedidoStatus;
import com.pedidos.exception.PedidoNotFoundException;
import com.pedidos.mapper.PedidoMapper;
import com.pedidos.mapper.StatusMapper;
import com.pedidos.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private final PedidoMapper pedidoMapper = PedidoMapper.INSTANCE.INSTANCE;

    private final StatusMapper statusMapper = StatusMapper.INSTANCE.INSTANCE;

    public MessageResponseDTO createPedido(PedidoDTO pedidoDTO) {
        Pedido pedidoToCreate = pedidoMapper.toModel(pedidoDTO);

        Pedido createdPedido = pedidoRepository.save(pedidoToCreate);
        return MessageResponseDTO.builder()
                .message((createdPedido!=null)?"Created pedido with id: "+createdPedido.getId():"pedido null")
                .build();
    }

    public List<PedidoDTO> listAll() {

        List<Pedido> allPedido = pedidoRepository.findAll();
        return allPedido.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());

    }

    public PedidoDTO findById(Long id) throws PedidoNotFoundException {
        verifyIfExists(id);
        return pedidoMapper.toDTO(pedidoRepository.findById(id).get());
    }


    public void delete(Long id) throws PedidoNotFoundException {
        verifyIfExists(id);
        pedidoRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PedidoDTO pedidoDTO) throws PedidoNotFoundException {
        verifyIfExists(id);

        Pedido pedidoToUpdate = pedidoMapper.toModel(pedidoDTO);

        Pedido updatedPedido = pedidoRepository.save(pedidoToUpdate);
        return createMessageResponse(updatedPedido.getId(), "Updated pedido with id:");
    }

    private void verifyIfExists(Long id) throws PedidoNotFoundException {
        pedidoRepository.findById(id).orElseThrow(()-> new PedidoNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO
                .builder()
                .message(message + id)
                .build();
    }

    public MessageResponseDTO verificaPedido(StatusDTO statusDTO)  {

        String message = null;
        Status status = statusMapper.toModel(statusDTO);
        Pedido pedido = null;
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            if(exists(status)&&status.getStatus()== PedidoStatus.APROVADO) {
                pedido = this.findById(status.getPedido());
                BigDecimal orderValue =  BigDecimal.ZERO;
                int orderItemQtd = 0;

                for (Item i : pedido.getItens()) {
                    orderValue=orderValue.add(i.getPrecoUnitario().multiply(new BigDecimal(i.getQtd())));
                    orderItemQtd += i.getQtd();
                }
                if (orderValue.compareTo(status.getValorAprovado()) ==0&& orderItemQtd == status.getItensAprovados()) {
                    jsonArray.add(PedidoStatus.APROVADO);
                } else {

                    if(status.getValorAprovado().compareTo(orderValue)<0){
                        jsonArray.add(PedidoStatus.APROVADO_VALOR_A_MENOR);
                    }else if(status.getValorAprovado().compareTo(orderValue)>0){
                        jsonArray.add(PedidoStatus.APROVADO_VALOR_A_MAIOR);
                    }

                    if(new BigDecimal(status.getItensAprovados()).compareTo(new BigDecimal(orderItemQtd))<0){
                        jsonArray.add(PedidoStatus.APROVADO_QTD_A_MENOR);
                    }else if(new BigDecimal(status.getItensAprovados()).compareTo(new BigDecimal(orderItemQtd))>0){
                        jsonArray.add(PedidoStatus.APROVADO_QTD_A_MAIOR);
                    }
                }

            }else if(exists(status)&&status.getStatus()==PedidoStatus.REPROVADO){
                jsonArray.add( PedidoStatus.REPROVADO);
            }else if(!exists(status)){
                jsonArray.add( PedidoStatus.CODIGO_PEDIDO_INVALIDO);
            }
        } catch (PedidoNotFoundException e) {
            e.printStackTrace();
        }
        json.put("status",jsonArray);
        json.put("pedido",status.getPedido());
        message = json.toJSONString();
        return MessageResponseDTO.builder().message(message).build();
    }

    private boolean exists(Status status) {
        boolean exists = false;
        try {
            this.findById(status.getPedido());
            exists = true;
        } catch (PedidoNotFoundException e) {
            e.printStackTrace();
        }
        return exists;
    }


    /*
    * String str = "a12.334tyz.78x";
        str = str.replaceAll("[^\\d]", "");
*/

}
