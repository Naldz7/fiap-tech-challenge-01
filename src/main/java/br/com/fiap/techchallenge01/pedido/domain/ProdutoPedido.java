package br.com.fiap.techchallenge01.pedido.domain;

import br.com.fiap.techchallenge01.core.utils.domain.DominioBase;
import br.com.fiap.techchallenge01.produto.domain.Produto;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
//@Builder
public class ProdutoPedido extends DominioBase {

    private String id;
    private Long quantidade;
    private String observacao;
    private Produto produto;

//    public JpaProdutoPedidoEntity toJpaProdutoPedidoEntity(){
//        return JpaProdutoPedidoEntity.builder()
//                .id(UUID.fromString(id))
//                .build();
//    }
//
//    public static ProdutoPedido fromJpaProdutoPedidoEntity(JpaProdutoPedidoEntity jpaProdutoPedidoEntity){
//        return ProdutoPedido.builder()
//                .id(String.valueOf(jpaProdutoPedidoEntity.getId()))
//                .build();
//    }
//
//    public static List<JpaProdutoPedidoEntity> fromProdutoPedidoList(List<ProdutoPedido> childClassDtoList){
//        return childClassDtoList.stream()
//                .map(ProdutoPedido::toJpaProdutoPedidoEntity)
//                .toList();
//    }
}