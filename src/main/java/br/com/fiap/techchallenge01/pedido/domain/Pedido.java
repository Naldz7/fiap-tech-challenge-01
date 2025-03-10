package br.com.fiap.techchallenge01.pedido.domain;

import br.com.fiap.techchallenge01.cliente.domain.Cliente;
import br.com.fiap.techchallenge01.core.utils.domain.DominioBase;
//import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
//@Builder
public class Pedido extends DominioBase {

    private String id;
    private String codigo;
    private String status;
    private BigDecimal preco;
    private String codigoPagamento;
    private String observacao;
    private Cliente cliente;
    private List<ProdutoPedido> produtos;

//    public JpaPedidoEntity toJpaPedidoEntity(){
//        JpaPedidoEntity jpaPedidoEntity = JpaPedidoEntity.builder()
//                .id(UUID.fromString(id))
//                .produtos(ProdutoPedido.fromProdutoPedidoList(produtos))
//                .build();
//        jpaPedidoEntity.getProdutos().forEach(child -> child.setPedido(jpaPedidoEntity));
//        return jpaPedidoEntity;
//    }
//
//    public static Pedido fromJpaPedidoEntity(JpaPedidoEntity jpaPedidoEntity){
//        return Pedido.builder()
//                .id(String.valueOf(jpaPedidoEntity.getId()))
//                .produtos(jpaPedidoEntity.getProdutos().stream().map(ProdutoPedido::fromJpaProdutoPedidoEntity).toList())
//                .build();
//    }
}